/*
 * Interfaz: Donante.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define la estructura y comportamiento de un donante
 * dentro del sistema BioQueue. Representa a un individuo que puede donar un órgano,
 * incluyendo su información personal y médica relevante para el proceso de
 * compatibilidad y asignación.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que representa a un donante de órganos en el sistema.
 * Define los métodos necesarios para acceder y modificar la información
 * básica y médica del donante, @see DonanteImpl, utilizada en los procesos de 
 * compatibilidad y asignación de órganos.
 */
public interface Donante {

    /**
     * Obtiene la cédula de identidad del donante.
     *
     * @return Cédula de identidad del donante.
     */
    String getCedulaDeIdentidad();

    /**
     * Obtiene el nombre del donante.
     * 
     * @return Nombre del donante.
     */
    String getNombre();

    /**
     * Obtiene el tipo de sangre del donante.
     * 
     * @return Tipo de sangre del donante.
     */
    String getTipoDeSangre();

    /**
     * Obtiene el tipo de órgano que el donante dona.
     * 
     * @return Tipo de órgano a donar por parte del donante.
     */
    String getTipoDeOrgano();

    /**
     * Obtiene la edad del donante.
     * 
     * @return Edad del donante.
     */
    byte getEdad();

    /**
     * Establece el nombre del donante.
     *
     * @param nombre Nuevo nombre del donante.
     */
    void setNombre(String nombre);

    /**
     * Establece la edad del donante.
     * 
     * @param edad Nueva edad del donante.
     */
    void setEdad(byte edad);

    /* Métodos pensados para prevenir posibles equivocaciones por parte del usuario. */

    /**
     * Establece el tipo de sangre del donante.
     * 
     * @param tipoDeSangre Nuevo tipo de sangre del donante.
     */
    void setTipoDeSangre(String tipoDeSangre);

    /**
     * Establece el tipo de órgano que dona el donante.
     * 
     * @param tipoDeOrgano Nuevo tipo de órgano donado por parte del donante.
     */
    void setTipoDeOrgano(String tipoDeOrgano);

}