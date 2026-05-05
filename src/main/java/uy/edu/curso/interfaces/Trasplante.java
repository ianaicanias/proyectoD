/*
 * Interfaz: Trasplante.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz representa un trasplante realizado dentro del sistema
 * BioQueue. Un trasplante vincula un órgano, su donante y el receptor al cual
 * fue asignado, constituyendo un registro histórico del proceso de asignación.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que representa un trasplante en el sistema, @see TrasplanteImpl.
 * Proporciona métodos para acceder a la información asociada a un trasplante
 * realizado, incluyendo el órgano trasplantado, su donante y el receptor.
 */
public interface Trasplante {

    /**
     * Retorna el identificador único del trasplante.
     *
     * @return Identificador del trasplante.
     */
    long getIdentificador();

    /**
     * Retorna el receptor del trasplante.
     *
     * @return Receptor que recibió el órgano.
     */
    Receptor getReceptorDelTrasplante();

    /**
     * Retorna el órgano trasplantado.
     *
     * @return Órgano trasplantado al receptor.
     */
    Organo getOrganoTrasplantado();

    /**
     * Retorna el donante del órgano trasplantado.
     *
     * @return Donante del órgano.
     */
    Donante getDonanteDelOrganoDelTrasplante();

}