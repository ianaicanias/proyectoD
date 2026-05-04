/*
 * Interfaz: GestorDeReceptores.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define las operaciones de gestión de receptores dentro
 * del sistema BioQueue. Permite registrar, buscar, eliminar y listar receptores,
 * así como gestionar una lista de prioridad utilizada para la asignación de órganos
 * en función de criterios médicos y de urgencia.
 */
package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Interfaz que define el comportamiento de un gestor de receptores.
 * Proporciona métodos para la administración de receptores en el sistema,
 * incluyendo su registro, búsqueda, eliminación y manejo de una lista de
 * prioridad basada en el puntaje asignado a cada receptor.
 */
public interface GestorDeReceptores {

    /**
     * Retorna el registro general de todos los receptores del sistema.
     *
     * @return Lista enlazada con todos los receptores registrados.
     */
    TDAListaEnlazada<Receptor> getListaDeReceptores();

    /**
     * Retorna la lista de receptores en espera activa de trasplante,
     * ordenada por prioridad.
     *
     * @return Lista enlazada de receptores ordenada por prioridad.
     */
    TDAListaEnlazada<Receptor> getListaDePrioridadDeReceptores();

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
    Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad);

    /**
     * Inserta un receptor en la lista de prioridad manteniendo el orden
     * de mayor a menor puntaje. En caso de igual puntaje, se ubica antes
     * al receptor de menor edad.
     *
     * @param nuevoReceptor Receptor a insertar en la lista de prioridad.
     */
    void insertarReceptorEnLaListaDePrioridad(Receptor receptor);

    /**
     * Busca un receptor en el sistema por su cédula de identidad.
     *
     * @param cedulaDeIdentidadReceptor Cédula de identidad del receptor a buscar.
     * @return El receptor encontrado, o {@code null} si no existe.
     */
    Receptor buscarReceptor(String cedulaDeIdentidad);

    /**
     * Elimina un receptor del sistema por su cédula de identidad,
     * removiéndolo tanto del registro general como de la lista de prioridad.
     * Si no existe un receptor con esa cédula, no realiza ninguna acción.
     *
     * @param cedulaDeIdentidadReceptor Cédula de identidad del receptor a eliminar.
     */
    void eliminarReceptor(String cedulaDeIdentidad);

    /**
     * Retorna una cadena con el listado de todos los receptores registrados,
     * incluyendo nombre, cédula, tipo de órgano, tipo de sangre y edad.
     *
     * @return Listado de receptores en formato texto.
     */
    String listarReceptores();

}