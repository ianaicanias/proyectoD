/*
 * Clase: BioQueueFacade.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la fachada principal del sistema BioQueue, implementando
 * el patrón de diseño Facade y Singleton. Centraliza el acceso a todos los gestores
 * del sistema, coordinando el registro de donantes, receptores y órganos, así como
 * la asignación automática de trasplantes al receptor compatible de mayor prioridad.
 */
package uy.edu.curso.facade;

import uy.edu.curso.interfaces.ConsultorDeCompatibilidadSanguinea;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.interfaces.GestorDeOrganos;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.GestorDeTrasplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.services.ConsultorDeCompatibilidadSanguineaImpl;
import uy.edu.curso.services.GestorDeDonantesImpl;
import uy.edu.curso.services.GestorDeOrganosImpl;
import uy.edu.curso.services.GestorDeReceptoresImpl;
import uy.edu.curso.services.GestorDeTrasplantesImpl;
import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Fachada principal del sistema BioQueue.
 * Implementa los patrones Facade y Singleton, actuando como único punto de entrada
 * al sistema y coordinando la interacción entre los gestores de receptores, donantes,
 * órganos y trasplantes.
 */
public class BioQueueFacade {

    /**
     * Única instancia de la fachada en el sistema.
     */
    private static BioQueueFacade instancia;

    /**
     * Gestor encargado de administrar los receptores del sistema.
     */
    private final GestorDeReceptores gestorDeReceptores;

    /**
     * Gestor encargado de administrar los donantes del sistema.
     */
    private final GestorDeDonantes gestorDeDonantes;

    /**
     * Gestor encargado de administrar los órganos disponibles del sistema.
     */
    private final GestorDeOrganos gestorDeOrganos;

    /**
     * Gestor encargado de administrar los trasplantes realizados del sistema.
     */
    private final GestorDeTrasplantes gestorDeTrasplantes;

    /**
     * Constructor privado de BioQueueFacade. Inicializa todos los gestores
     * del sistema e inyecta el consultor de compatibilidad sanguínea al gestor de trasplantes.
     */
    private BioQueueFacade() {
        this.gestorDeReceptores = new GestorDeReceptoresImpl();
        this.gestorDeDonantes = new GestorDeDonantesImpl();
        this.gestorDeOrganos = new GestorDeOrganosImpl();
        
        ConsultorDeCompatibilidadSanguinea consultorDeCompatibilidadSanguinea = new ConsultorDeCompatibilidadSanguineaImpl();
        this.gestorDeTrasplantes = new GestorDeTrasplantesImpl(consultorDeCompatibilidadSanguinea);
    }

    /**
     * Retorna la única instancia de BioQueueFacade, creándola si no existe.
     *
     * @return Instancia única de BioQueueFacade.
     */
    public static BioQueueFacade getInstancia() {
        if (instancia == null) {
            instancia = new BioQueueFacade();
        }

        return instancia;
    }

    /**
     * Registra un nuevo receptor en el sistema.
     *
     * @param cedulaDeIdentidad      Cédula de identidad única del receptor.
     * @param nombre                 Nombre completo del receptor.
     * @param tipoDeOrganoNecesitado Tipo de órgano que el receptor necesita.
     * @param tipoDeSangre           Tipo de sangre del receptor.
     * @param edad                   Edad del receptor en años.
     * @param puntajeDePrioridad     Puntaje de prioridad clínica del receptor.
     * @return El receptor creado, o {@code null} si ya existe un receptor con esa cédula.
     */
    public Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        return this.gestorDeReceptores.registrarReceptor(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado, 
                tipoDeSangre, edad, puntajeDePrioridad);
    }

    /**
     * Inserta un receptor en la lista de prioridad e intenta asignarle un órgano
     * compatible disponible. Si se encuentra un órgano compatible, se realiza
     * el trasplante automáticamente.
     *
     * @param receptor Receptor a insertar en la lista de prioridad.
     */
    public void insertarReceptorEnLaListaDePrioridad(Receptor receptor) {
        this.gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(receptor);
        TDAListaEnlazada<Organo> listaDeOrganosDisponibles = this.gestorDeOrganos.getListaDeOrganosDisponibles();
        int i = 0;
        
        while (i < listaDeOrganosDisponibles.tamaño()) {
            TDAListaEnlazada<Receptor> listaDeReceptoresGeneral = this.gestorDeReceptores.getListaDeReceptores();
            TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores = this.gestorDeReceptores.getListaDePrioridadDeReceptores();
            Organo organoDisponible = listaDeOrganosDisponibles.obtener(i);

            boolean fueTrasplantado = this.gestorDeTrasplantes.asignarOrganoAReceptor(organoDisponible, listaDeReceptoresGeneral, 
                    listaDePrioridadDeReceptores, listaDeOrganosDisponibles);

            if (fueTrasplantado) {
                break;
            }
            i++;
        }
    }

    /**
     * Registra un nuevo donante en el sistema y su órgano donado. Al registrar
     * el órgano, intenta automáticamente asignarlo al receptor compatible
     * de mayor prioridad.
     *
     * @param cedulaDeIdentidad  Cédula de identidad única del donante.
     * @param nombre             Nombre completo del donante.
     * @param tipoDeOrganoDonado Tipo de órgano que el donante ofrece.
     * @param tipoDeSangre       Tipo de sangre del donante.
     * @param edad               Edad del donante en años.
     * @return El donante creado, o {@code null} si ya existe un donante con esa cédula.
     */
    public Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, String tipoDeSangre, byte edad) {
        Donante nuevoDonante = this.gestorDeDonantes.registrarDonante(cedulaDeIdentidad, nombre, tipoDeOrganoDonado, tipoDeSangre, edad);
        
        if (nuevoDonante != null) {
            this.registrarOrgano(tipoDeOrganoDonado, nuevoDonante);
        }

        return nuevoDonante;
    }

    /**
     * Registra el órgano del donante e intenta asignarlo al receptor compatible
     * de mayor prioridad en la lista de espera.
     *
     * @param nombreDelOrgano   Nombre del órgano a registrar.
     * @param donanteDelOrgano  Donante al que pertenece el órgano.
     */
    private void registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano) {
        Organo nuevoOrgano = this.gestorDeOrganos.registrarOrgano(nombreDelOrgano, donanteDelOrgano);
        TDAListaEnlazada<Receptor> listaDeReceptores = this.gestorDeReceptores.getListaDeReceptores();
        TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores = this.gestorDeReceptores.getListaDePrioridadDeReceptores();
        TDAListaEnlazada<Organo> listaDeOrganosDisponibles = this.gestorDeOrganos.getListaDeOrganosDisponibles();

        this.gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, listaDeReceptores, 
                listaDePrioridadDeReceptores, listaDeOrganosDisponibles);
    }

    /**
     * Busca un receptor por su cédula de identidad.
     *
     * @param cedulaDeIdentidad Cédula de identidad del receptor a buscar.
     * @return El receptor encontrado, o {@code null} si no existe.
     */
    public Receptor buscarReceptor(String cedulaDeIdentidad) {
        return this.gestorDeReceptores.buscarReceptor(cedulaDeIdentidad);
    }

    /**
     * Busca un donante por su cédula de identidad.
     * 
     * @param cedulaDeIdentidad Cédula de identidad del donante a buscar.
     * @return El donante encontrado, o {@code null} si no existe.
     */
    public Donante buscarDonante(String cedulaDeIdentidad) {
        return this.gestorDeDonantes.buscarDonante(cedulaDeIdentidad);
    }

    /**
     * Busca un órgano por su identificador único.
     *
     * @param identificadorDelOrgano Identificador del órgano a buscar.
     * @return El órgano encontrado, o {@code null} si no existe.
     */
    public Organo buscarOrganoPorIdentificador(long identificadorDelOrgano) {
        return this.gestorDeOrganos.buscarOrganoPorIdentificador(identificadorDelOrgano);
    }

    /**
     * Busca todos los órganos disponibles con el nombre indicado.
     *
     * @param nombreDelOrgano Nombre del órgano a buscar.
     * @return Lista con los órganos encontrados.
     */
    public TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano) {
        return this.gestorDeOrganos.buscarOrganosPorNombre(nombreDelOrgano);
    }

    /**
     * Busca todos los órganos disponibles con el tipo de sangre indicado.
     *
     * @param tipoDeSangre Tipo de sangre a buscar.
     * @return Lista con los órganos encontrados.
     */
    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre) {
        return this.gestorDeOrganos.buscarOrganosPorTipoDeSangre(tipoDeSangre);
    }

    /**
     * Busca un trasplante por su identificador único.
     *
     * @param identificadorDelTrasplante Identificador del trasplante a buscar.
     * @return El trasplante encontrado, o {@code null} si no existe.
     */
    public Trasplante buscarTrasplante(long identificadorDelTrasplante) {
        return this.gestorDeTrasplantes.buscarTrasplante(identificadorDelTrasplante);
    }

    /**
     * Elimina un receptor del sistema por su cédula de identidad.
     *
     * @param cedulaDelReceptor Cédula de identidad del receptor a eliminar.
     */
    public void eliminarReceptor(String cedulaDelReceptor) {
        this.gestorDeReceptores.eliminarReceptor(cedulaDelReceptor);
    }

    /**
     * Elimina un donante del sistema por su cédula de identidad.
     * 
     * @param cedulaDelDonante Cédula de identidad del donante a eliminar.
     */
    public void eliminarDonante(String cedulaDelDonante) {
        this.gestorDeDonantes.eliminarDonante(cedulaDelDonante);
    }

    /**
     * Elimina un órgano del sistema por su identificador único.
     *
     * @param identificadorDelOrgano Identificador del órgano a eliminar.
     */
    public void eliminarOrgano(long identificadorDelOrgano) {
        this.gestorDeOrganos.eliminarOrgano(identificadorDelOrgano);
    }

    /**
     * Retorna una cadena con el listado de todos los receptores registrados.
     *
     * @return Listado de receptores en formato texto.
     */
    public String listarReceptores() {
        return this.gestorDeReceptores.listarReceptores();
    }

    /**
     * Retorna una cadena con el listado de todos los donantes registrados.
     *
     * @return Listado de donantes en formato texto.
     */
    public String listarDonantes() {
        return this.gestorDeDonantes.listarDonantes();
    }

    /**
     * Retorna una cadena con el listado de todos los órganos disponibles.
     *
     * @return Listado de órganos disponibles en formato texto.
     */
    public String listarOrganosDisponibles() {
        return this.gestorDeOrganos.listarOrganosDisponibles();
    }

    /**
     * Retorna una cadena con el listado de todos los trasplantes realizados.
     *
     * @return Listado de trasplantes realizados en formato texto.
     */
    public String listarTrasplantesRealizados() {
        return this.gestorDeTrasplantes.listarTrasplantesRealizados();
    }

    /**
     * Retorna la cantidad de receptores registrados en el sistema.
     *
     * @return Cantidad de receptores.
     */
    public int getCantidadDeReceptores() {
        return this.gestorDeReceptores.getListaDeReceptores().tamaño();
    }

    /**
     * Retorna la cantidad de donantes registrados en el sistema.
     *
     * @return Cantidad de donantes.
     */
    public int getCantidadDeDonantes() {
        return this.gestorDeDonantes.getListaDeDonantes().tamaño();
    }

    /**
     * Retorna la cantidad de trasplantes realizados en el sistema.
     *
     * @return Cantidad de trasplantes realizados.
     */
    public int getCantidadDeTrasplantes() {
        return this.gestorDeTrasplantes.getListaDeTrasplantesRealizados().tamaño();
    }

    /* Únicamente para el uso de Tests, en su versión final debería de ser eliminado. */
    
    /**
     * Resetea la instancia del Singleton. Utilizado exclusivamente para pruebas unitarias.
     * Este método debe ser eliminado en la versión oficial del sistema.
     */
    public static void resetearInstancia() {
        instancia = null;
    }

}