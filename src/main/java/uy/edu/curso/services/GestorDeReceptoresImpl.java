/*
 * Clase: GestorDeReceptoresImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta del gestor de receptores
 * en el sistema BioQueue. Implementa la interfaz GestorDeReceptores y administra
 * dos listas: el registro general de receptores y la lista de prioridad de receptores
 * en espera de trasplante, ordenada por puntaje de prioridad y edad como desempate.
 */
package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.ReceptorImpl;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Implementación concreta del gestor de receptores del sistema BioQueue.
 * Implementa @see GestorDeReceptores y administra dos listas: el registro
 * general de todos los receptores del sistema, y la lista de prioridad
 * que mantiene ordenados a los receptores en espera de trasplante según
 * su puntaje de prioridad, usando la edad como criterio de desempate.
 */
public class GestorDeReceptoresImpl implements GestorDeReceptores {

    /**
     * Lista enlazada que almacena el registro general de todos los receptores
     * registrados en el sistema.
     */
    private final TDAListaEnlazada<Receptor> listaDeReceptores;

    /**
     * Lista enlazada que almacena los receptores en espera activa de trasplante,
     * ordenada por puntaje de prioridad de mayor a menor. En caso de empate,
     * se prioriza al receptor de menor edad.
     */
    private final TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores;

    /**
     * Constructor de la clase GestorDeReceptoresImpl. Inicializa ambas listas vacías.
     */
    public GestorDeReceptoresImpl() {
        this.listaDeReceptores = new ListaEnlazada<>();
        this.listaDePrioridadDeReceptores = new ListaEnlazada<>();
    }

    /**
     * Retorna el registro general de todos los receptores del sistema.
     *
     * @return Lista enlazada con todos los receptores registrados.
     */
    @Override
    public TDAListaEnlazada<Receptor> getListaDeReceptores() {
        return this.listaDeReceptores;
    }

    /**
     * Retorna la lista de receptores en espera activa de trasplante,
     * ordenada por prioridad.
     *
     * @return Lista enlazada de receptores ordenada por prioridad.
     */
    @Override
    public TDAListaEnlazada<Receptor> getListaDePrioridadDeReceptores() {
        return this.listaDePrioridadDeReceptores;
    }

    /**
     * Registra un nuevo receptor en el sistema. Si ya existe un receptor con
     * la misma cédula de identidad, no se registra y retorna {@code null}.
     *
     * @param cedulaDeIdentidad      Cédula de identidad única del receptor.
     * @param nombre                 Nombre completo del receptor.
     * @param tipoDeOrganoNecesitado Tipo de órgano que el receptor necesita.
     * @param tipoDeSangre           Tipo de sangre del receptor.
     * @param edad                   Edad del receptor en años.
     * @param puntajeDePrioridad     Puntaje de prioridad clínica del receptor.
     * @return El receptor creado, o {@code null} si ya existe un receptor con esa cédula.
     */
    @Override
    public Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        if (this.buscarReceptor(cedulaDeIdentidad) != null) {
            return null;
        }
        Receptor nuevoReceptor = new ReceptorImpl(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado,
                tipoDeSangre, edad, puntajeDePrioridad);

        this.listaDeReceptores.agregar(nuevoReceptor);

        return nuevoReceptor;
    }

    /**
     * Inserta un receptor en la lista de prioridad manteniendo el orden
     * de mayor a menor puntaje. En caso de igual puntaje, se ubica antes
     * al receptor de menor edad.
     *
     * @param nuevoReceptor Receptor a insertar en la lista de prioridad.
     */
    @Override
    public void insertarReceptorEnLaListaDePrioridad(Receptor nuevoReceptor) {
        int tamañoDeLaListaDePrioridad = this.listaDePrioridadDeReceptores.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDePrioridad) {
            Receptor receptorActual = this.listaDePrioridadDeReceptores.obtener(i);
            boolean mayorPrioridad = nuevoReceptor.getPuntajeDePrioridad() > receptorActual.getPuntajeDePrioridad();
            boolean igualPrioridadConDesempate = (nuevoReceptor.getPuntajeDePrioridad() == receptorActual.getPuntajeDePrioridad())
                    && (nuevoReceptor.getEdad() < receptorActual.getEdad());

            if (mayorPrioridad || igualPrioridadConDesempate) {
                this.listaDePrioridadDeReceptores.agregar(i, nuevoReceptor);
                return;
            }
            i++;
        }
        this.listaDePrioridadDeReceptores.agregar(nuevoReceptor);
    }

    /**
     * Busca un receptor en el sistema por su cédula de identidad.
     *
     * @param cedulaDeIdentidadDelReceptor Cédula de identidad del receptor a buscar.
     * @return El receptor encontrado, o {@code null} si no existe.
     */
    @Override
    public Receptor buscarReceptor(String cedulaDeIdentidadDelReceptor) {
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeReceptores) {
            Receptor receptorEncontrado = this.listaDeReceptores.obtener(i);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadDelReceptor)) {
                return receptorEncontrado;
            }
            i++;
        }

        return null;
    }

    /**
     * Elimina un receptor del sistema por su cédula de identidad,
     * removiéndolo tanto del registro general como de la lista de prioridad.
     * Si no existe un receptor con esa cédula, no realiza ninguna acción.
     *
     * @param cedulaDeIdentidadDelReceptor Cédula de identidad del receptor a eliminar.
     */
    @Override
    public void eliminarReceptor(String cedulaDeIdentidadDelReceptor) {
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int tamañoDeLaListaDePrioridad = this.listaDePrioridadDeReceptores.tamaño();
        int i = 0;
        int j = 0;

        while (i < tamañoDeLaListaDeReceptores) {
            Receptor receptorEncontrado = this.listaDeReceptores.obtener(i);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadDelReceptor)) {
                this.listaDeReceptores.remover(i);
                break;
            }
            i++;
        }
        while (j < tamañoDeLaListaDePrioridad) {
            Receptor receptorEncontrado = this.listaDePrioridadDeReceptores.obtener(j);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadDelReceptor)) {
                this.listaDePrioridadDeReceptores.remover(j);
                break;
            }
            j++;
        }
    }

    /**
     * Retorna una cadena con el listado de todos los receptores registrados,
     * incluyendo nombre, cédula, tipo de órgano, tipo de sangre y edad.
     *
     * @return Listado de receptores en formato texto.
     */
    @Override
    public String listarReceptores() {
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("----------------------- DATOS DE RECEPTORES -----------------------\n");
        while (i < tamañoDeLaListaDeReceptores) {
            StringBuilder renglon = new StringBuilder();
            Receptor receptor = this.listaDeReceptores.obtener(i);

            renglon.append(receptor.getNombre());
            renglon.append(", ");
            renglon.append(receptor.getCedulaDeIdentidad());
            renglon.append(", ");
            renglon.append(receptor.getTipoDeOrgano());
            renglon.append(", ");
            renglon.append(receptor.getTipoDeSangre());
            renglon.append(", ");
            renglon.append(receptor.getEdad());
            renglon.append(".");
            pagina.append(renglon.toString());
            pagina.append("\n");
            i++;
        }

        return pagina.toString();
    }

}