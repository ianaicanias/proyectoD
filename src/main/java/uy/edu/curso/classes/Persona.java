/*
 * Clase: Persona.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase abstracta representa a una persona dentro del sistema BioQueue.
 * Sirve como base para DonanteImpl y ReceptorImpl, centralizando los atributos y
 * comportamientos comunes a toda persona registrada en el sistema, como la cédula
 * de identidad, nombre, tipo de sangre, tipo de órgano y edad.
 */
package uy.edu.curso.classes;

/**
 * Clase abstracta que representa a una persona dentro del sistema BioQueue.
 * Sirve como base para las clases @see DonanteImpl y @see ReceptorImpl,
 * centralizando los atributos y comportamientos comunes a toda persona
 * registrada en el sistema.
 */
public abstract class Persona {

    /**
     * Cédula de identidad de la persona. Inmutable una vez asignada.
     */
    protected final String cedulaDeIdentidad;

    /**
     * Nombre de la persona.
     */
    protected String nombre;

    /**
     * Tipo de sangre de la persona.
     */
    protected String tipoDeSangre;

    /**
     * Tipo de órgano que solicitará o donará la persona dependiendo de si es receptora o donante.
     */
    protected String tipoDeOrgano;
    
    /**
     * Edad de la persona.
     */
    protected byte edad;

    /**
     * Constructor de la clase Persona.
     * 
     * @param cedulaDeIdentidad Cédula de identidad única de la persona.
     * @param nombre Nombre de la persona.
     * @param tipoDeSangre Tipo de sangre de la persona.
     * @param tipoDeOrgano Tipo de órgano que la persona solicita o dona, dependiendo de si es receptora o donante.
     * @param edad Edad de la persona.
     */
    public Persona(String cedulaDeIdentidad, String nombre, String tipoDeSangre, String tipoDeOrgano, byte edad) {
        this.cedulaDeIdentidad = cedulaDeIdentidad;
        this.nombre = nombre;
        this.tipoDeSangre = tipoDeSangre;
        this.tipoDeOrgano = tipoDeOrgano;
        this.edad = edad;
    }

    /**
     * Retorna la cédula de identidad de la persona.
     *
     * @return Cédula de identidad de la persona.
     */
    public String getCedulaDeIdentidad() {
        return this.cedulaDeIdentidad;
    }

    /**
     * Retorna el nombre de la persona.
     * 
     * @return Nombre de la persona.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna el tipo de sangre de la persona.
     * 
     * @return Tipo de sangre de la persona.
     */
    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    /**
     * Retorna el tipo de órgano asociado a la persona.
     *
     * @return Tipo de órgano (solicitado o donado).
     */
    public String getTipoDeOrgano() {
        return this.tipoDeOrgano;
    }

    /**
     * Retorna la edad de la persona.
     * 
     * @return Edad de la persona.
     */
    public byte getEdad() {
        return this.edad;
    }

    /**
     * Actualiza el nombre de la persona.
     *
     * @param nombre Nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Actualiza la edad de la persona.
     * 
     * @param edad Nueva edad de la persona.
     */
    public void setEdad(byte edad) {
        this.edad = edad;
    }

    /* Métodos pensados para prevenir posibles equivocaciones por parte del usuario. */

    /**
     * Actualiza el tipo de sangre de la persona.
     * 
     * @param tipoDeSangre Nuevo tipo de sangre de la persona.
     */
    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    /**
     * Actualiza el tipo de órgano asociado a la persona.
     *
     * @param tipoDeOrgano Nuevo tipo de órgano solicitado o donado.
     */
    public void setTipoDeOrgano(String tipoDeOrgano) {
        this.tipoDeOrgano = tipoDeOrgano;
    }
    
}