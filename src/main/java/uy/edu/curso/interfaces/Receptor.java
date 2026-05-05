/*
 * Interfaz: Receptor.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz representa a un receptor de órganos dentro del sistema
 * BioQueue. Define la información personal y médica necesaria para evaluar la
 * compatibilidad con órganos disponibles, así como un puntaje de prioridad que
 * determina su posición en la lista de espera para trasplantes.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que representa a un receptor en el sistema, @see ReceptorImpl.
 * Proporciona métodos para acceder y modificar la información del receptor,
 * utilizada en los procesos de asignación de órganos según compatibilidad
 * y prioridad.
 */
public interface Receptor {

    /**
     * Retorna el puntaje de prioridad del receptor.
     *
     * @return Puntaje de prioridad del receptor.
     */
    byte getPuntajeDePrioridad();

    /**
     * Actualiza el puntaje de prioridad del receptor.
     *
     * @param puntajeDePrioridad Nuevo puntaje de prioridad del receptor.
     */
    void setPuntajeDePrioridad(byte puntajeDePrioridad);

    /**
     * Retorna la cédula de identidad del receptor.
     *
     * @return Cédula de identidad del receptor.
     */
    String getCedulaDeIdentidad();

    /**
     * Retorna el nombre del receptor.
     * 
     * @return Nombre del receptor.
     */
    String getNombre();

    /**
     * Retorna el tipo de sangre del receptor.
     * 
     * @return Tipo de sangre del receptor.
     */
    String getTipoDeSangre();

    /**
     * Retorna el tipo de órgano solicitado por el donante.
     *
     * @return Tipo de órgano solicitado.
     */
    String getTipoDeOrgano();

    /**
     * Retorna la edad del receptor.
     * 
     * @return Edad del receptor.
     */
    byte getEdad();

    /**
     * Actualiza el nombre del receptor.
     *
     * @param nombre Nuevo nombre del receptor.
     */
    void setNombre(String nombre);

    /**
     * Actualiza la edad del receptor.
     * 
     * @param edad Nueva edad del receptor.
     */
    void setEdad(byte edad);

    /* Métodos pensados para prevenir posibles equivocaciones por parte del usuario. */

    /**
     * Actualiza el tipo de sangre del receptor.
     * 
     * @param tipoDeSangre Nuevo tipo de sangre del receptor.
     */
    void setTipoDeSangre(String tipoDeSangre);

    /**
     * Actualiza el tipo de órgano asociado al receptor.
     *
     * @param tipoDeOrgano Nuevo tipo de órgano solicitado por el receptor.
     */
    void setTipoDeOrgano(String tipoDeOrgano);

}