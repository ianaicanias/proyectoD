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


public class BioQueueFacade {

    private static BioQueueFacade instancia;
    private final GestorDeReceptores gestorDeReceptores;
    private final GestorDeDonantes gestorDeDonantes;
    private final GestorDeOrganos gestorDeOrganos;
    private final GestorDeTrasplantes gestorDeTrasplantes;

    private BioQueueFacade() {
        this.gestorDeReceptores = new GestorDeReceptoresImpl();
        this.gestorDeDonantes = new GestorDeDonantesImpl();
        this.gestorDeOrganos = new GestorDeOrganosImpl();
        
        ConsultorDeCompatibilidadSanguinea consultorDeCompatibilidadSanguinea = new ConsultorDeCompatibilidadSanguineaImpl();
        this.gestorDeTrasplantes = new GestorDeTrasplantesImpl(consultorDeCompatibilidadSanguinea);
    }

    public static BioQueueFacade getInstancia() {
        if (instancia == null) {
            instancia = new BioQueueFacade();
        }

        return instancia;
    }

    public Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        return this.gestorDeReceptores.registrarReceptor(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado, 
                tipoDeSangre, edad, puntajeDePrioridad);
    }

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

    public Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, String tipoDeSangre, byte edad) {
        Donante nuevoDonante = this.gestorDeDonantes.registrarDonante(cedulaDeIdentidad, nombre, tipoDeOrganoDonado, tipoDeSangre, edad);
        
        if (nuevoDonante != null) {
            this.registrarOrgano(tipoDeOrganoDonado, nuevoDonante);
        }

        return nuevoDonante;
    }

    private void registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano) {
        Organo nuevoOrgano = this.gestorDeOrganos.registrarOrgano(nombreDelOrgano, donanteDelOrgano);
        TDAListaEnlazada<Receptor> listaDeReceptores = this.gestorDeReceptores.getListaDeReceptores();
        TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores = this.gestorDeReceptores.getListaDePrioridadDeReceptores();
        TDAListaEnlazada<Organo> listaDeOrganosDisponibles = this.gestorDeOrganos.getListaDeOrganosDisponibles();

        this.gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, listaDeReceptores, 
                listaDePrioridadDeReceptores, listaDeOrganosDisponibles);
    }

    public Receptor buscarReceptor(String cedulaDeIdentidad) {
        return this.gestorDeReceptores.buscarReceptor(cedulaDeIdentidad);
    }

    public Donante buscarDonante(String cedulaDeIdentidad) {
        return this.gestorDeDonantes.buscarDonante(cedulaDeIdentidad);
    }

    public Organo buscarOrganoPorIdentificador(long identificadorDelOrgano) {
        return this.gestorDeOrganos.buscarOrganoPorIdentificador(identificadorDelOrgano);
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano) {
        return this.gestorDeOrganos.buscarOrganosPorNombre(nombreDelOrgano);
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre) {
        return this.gestorDeOrganos.buscarOrganosPorTipoDeSangre(tipoDeSangre);
    }

    public Trasplante buscarTrasplante(long identificadorDelTrasplante) {
        return this.gestorDeTrasplantes.buscarTrasplante(identificadorDelTrasplante);
    }

    public void eliminarReceptor(String cedulaDelReceptor) {
        this.gestorDeReceptores.eliminarReceptor(cedulaDelReceptor);
    }

    public void eliminarDonante(String cedulaDelDonante) {
        this.gestorDeDonantes.eliminarDonante(cedulaDelDonante);
    }

    public void eliminarOrgano(long identificadorDelOrgano) {
        this.gestorDeOrganos.eliminarOrgano(identificadorDelOrgano);
    }

    public String listarReceptores() {
        return this.gestorDeReceptores.listarReceptores();
    }

    public String listarDonantes() {
        return this.gestorDeDonantes.listarDonantes();
    }

    public String listarOrganosDisponibles() {
        return this.gestorDeOrganos.listarOrganosDisponibles();
    }

    public String listarTrasplantesRealizados() {
        return this.gestorDeTrasplantes.listarTrasplantesRealizados();
    }

    public int getCantidadDeReceptores() {
        return this.gestorDeReceptores.getListaDeReceptores().tamaño();
    }

    public int getCantidadDeDonantes() {
        return this.gestorDeDonantes.getListaDeDonantes().tamaño();
    }

    public int getCantidadDeTrasplantes() {
        return this.gestorDeTrasplantes.getListaDeTrasplantesRealizados().tamaño();
    }

    /* Únicamente para el uso de Tests, en su versión oficial debe ser eliminado. */
    
    public static void resetearInstancia() {
        instancia = null;
    }

}