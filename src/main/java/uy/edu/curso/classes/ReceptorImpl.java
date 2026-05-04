/*
 * Clase: ReceptorImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta de un receptor de órganos
 * en el sistema BioQueue. Extiende Persona e implementa la interfaz Receptor,
 * agregando el puntaje de prioridad que determina el orden de atención en la
 * lista de espera de trasplantes.
 */
package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Receptor;


/**
 * Implementación concreta de un receptor de órganos en el sistema BioQueue.
 * Extiende @see Persona e implementa @see Receptor, heredando los atributos
 * comunes de una persona y agregando el puntaje de prioridad que determina
 * el orden de atención en la lista de espera.
 */
public class ReceptorImpl extends Persona implements Receptor {

    /**
     * Puntaje de prioridad del receptor, determinado por su gravedad clínica.
     * A mayor puntaje, mayor prioridad en la lista de espera.
     */
    private byte puntajeDePrioridad;

    /**
     * Constructor de la clase ReceptorImpl.
     *
     * @param cedulaDeIdentidad      Cédula de identidad única del receptor.
     * @param nombre                 Nombre completo del receptor.
     * @param tipoDeOrganoNecesitado Tipo de órgano que el receptor necesita.
     * @param tipoDeSangre           Tipo de sangre del receptor.
     * @param edad                   Edad del receptor en años.
     * @param puntajeDePrioridad     Puntaje de prioridad clínica del receptor.
     */
    public ReceptorImpl(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoNecesitado, edad);
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

    /**
     * Retorna el puntaje de prioridad del receptor.
     *
     * @return Puntaje de prioridad del receptor.
     */
    @Override
    public byte getPuntajeDePrioridad() {
        return this.puntajeDePrioridad;
    }

    /**
     * Actualiza el puntaje de prioridad del receptor.
     *
     * @param puntajeDePrioridad Nuevo puntaje de prioridad del receptor.
     */
    @Override
    public void setPuntajeDePrioridad(byte puntajeDePrioridad) {
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

}