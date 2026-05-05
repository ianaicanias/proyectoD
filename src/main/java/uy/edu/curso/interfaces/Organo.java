/*
 * Interfaz: Organo.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz representa un órgano disponible para trasplante dentro
 * del sistema BioQueue. Define la información relevante asociada al órgano,
 * incluyendo su identificador, tipo, compatibilidad sanguínea y el donante
 * del cual proviene.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que representa un órgano en el sistema, @see OrganoImpl.
 * Proporciona métodos para acceder y modificar la información asociada al órgano,
 * utilizada en los procesos de asignación y compatibilidad de trasplantes.
 */
public interface Organo {

    /**
     * Retorna el identificador único del órgano.
     *
     * @return Identificador del órgano.
     */
    long getIdentificador();

    /**
     * Indica si el órgano proviene de un donante menor de 18 años.
     *
     * @return {@code true} si el donante era menor de 18 años, {@code false} en caso contrario.
     */
    boolean getEsInfantil();

    /**
     * Retorna el donante al que pertenece el órgano.
     *
     * @return Donante del órgano.
     */
    Donante getDonanteDelOrgano();

    /**
     * Retorna el nombre del órgano.
     *
     * @return Nombre del órgano.
     */
    String getNombre();

    /**
     * Retorna el tipo de sangre del órgano.
     *
     * @return Tipo de sangre del órgano.
     */
    String getTipoDeSangre();

    /* Métodos pensados para prevenir posibles equivocaciones por parte del usuario. */

    /**
     * Actualiza el nombre del órgano.
     *
     * @param nombre Nuevo nombre del órgano.
     */
    void setNombre(String nombre);

    /**
     * Actualiza el tipo de sangre del órgano.
     *
     * @param tipoDeSangre Nuevo tipo de sangre del órgano.
     */
    void setTipoDeSangre(String tipoDeSangre);

}