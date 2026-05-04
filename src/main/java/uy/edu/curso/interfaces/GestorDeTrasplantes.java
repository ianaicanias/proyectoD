/*
 * Interfaz: GestorDeTrasplantes.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define las operaciones relacionadas con la gestión de
 * trasplantes dentro del sistema BioQueue. Permite asignar órganos a receptores
 * compatibles según criterios médicos y de prioridad, así como mantener un
 * historial de los trasplantes realizados.
 */
package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Interfaz que define el comportamiento de un gestor de trasplantes.
 * Proporciona métodos para la asignación de órganos a receptores y la
 * administración del historial de trasplantes realizados en el sistema.
 */
public interface GestorDeTrasplantes {

    /**
     * Retorna la lista de trasplantes realizados en el sistema.
     *
     * @return Lista enlazada con todos los trasplantes realizados.
     */
    TDAListaEnlazada<Trasplante> getListaDeTrasplantesRealizados();

    /**
     * Intenta asignar un órgano al receptor compatible de mayor prioridad en la
     * lista de espera. La compatibilidad se determina por tipo de sangre y tipo
     * de órgano. Si se realiza el trasplante, el receptor es removido de ambas
     * listas y el órgano es removido de los disponibles.
     *
     * @param organo                      Órgano a asignar.
     * @param listaDeReceptores           Registro general de receptores.
     * @param listaDePrioridadDeReceptores Lista de receptores en espera ordenada por prioridad.
     * @param listaDeOrganosDisponibles   Lista de órganos disponibles para trasplante.
     * @return {@code true} si se realizó el trasplante, {@code false} si no se encontró receptor compatible.
     */
    boolean asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles);
    
    /**
     * Busca un trasplante en el historial por su identificador único.
     *
     * @param identificadorDelTrasplante Identificador del trasplante a buscar.
     * @return El trasplante encontrado, o {@code null} si no existe.
     */
    Trasplante buscarTrasplante(long identificadorDelTrasplante);

    /**
     * Retorna una cadena con el historial de todos los trasplantes realizados,
     * incluyendo identificador, órgano, donante y receptor de cada trasplante.
     *
     * @return Historial de trasplantes realizados en formato texto.
     */
    String listarTrasplantesRealizados();

}