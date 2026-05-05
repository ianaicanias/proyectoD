/*
 * Interfaz: GestorDeOrganos.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define las operaciones de gestión de órganos dentro
 * del sistema BioQueue. Permite registrar, buscar, eliminar y listar órganos
 * disponibles, así como realizar búsquedas según distintos criterios relevantes
 * para los procesos de asignación y compatibilidad.
 */
package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Interfaz que define el comportamiento de un gestor de órganos, @see GestorDeOrganosImpl.
 * Proporciona métodos para la administración de órganos disponibles en el sistema,
 * incluyendo su registro, búsqueda por distintos criterios, eliminación y listado.
 */
public interface GestorDeOrganos {

    /**
     * Retorna la lista de órganos disponibles para trasplante.
     *
     * @return Lista enlazada con todos los órganos disponibles.
     */
    TDAListaEnlazada<Organo> getListaDeOrganosDisponibles();

    /**
     * Registra un nuevo órgano en el sistema asociado a un donante.
     *
     * @param nombreDelOrgano  Nombre del órgano a registrar.
     * @param donanteDelOrgano Donante al que pertenece el órgano.
     * @return El órgano creado y registrado en el sistema.
     */
    Organo registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano);

    /**
     * Busca un órgano en el sistema por su identificador único.
     *
     * @param identificadorDelOrgano Identificador único del órgano a buscar.
     * @return El órgano encontrado, o {@code null} si no existe.
     */
    Organo buscarOrganoPorIdentificador(long identificadorDelOrgano);

    /**
     * Busca todos los órganos disponibles con el nombre indicado.
     * La comparación no distingue entre mayúsculas y minúsculas.
     *
     * @param nombreDelOrgano Nombre del órgano a buscar.
     * @return Lista con los órganos que coinciden con el nombre indicado.
     */
    TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano);

    /**
     * Busca todos los órganos disponibles con el tipo de sangre indicado.
     * La comparación no distingue entre mayúsculas y minúsculas.
     *
     * @param tipoDeSangreDelOrgano Tipo de sangre a buscar.
     * @return Lista con los órganos que coinciden con el tipo de sangre indicado.
     */
    TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangreDelOrgano);

    /**
     * Elimina un órgano del sistema según su identificador.
     *
     * @param identificadorDelOrgano Identificador único del órgano.
     */
    void eliminarOrgano(long identificadorDelOrgano);

    /**
     * Retorna una cadena con el listado de todos los órganos disponibles,
     * incluyendo identificador, nombre, tipo de sangre, cédula del donante
     * e indicador de origen infantil.
     *
     * @return Listado de órganos disponibles en formato texto.
     */
    String listarOrganosDisponibles();

}