/*
 * Clase: DonanteImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta de un donante de órganos
 * en el sistema BioQueue. Extiende Persona e implementa la interfaz Donante,
 * representando a una persona que dona un órgano para ser asignado al receptor
 * compatible de mayor prioridad.
 */
package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;

/**
 * Implementación concreta de un donante de órganos en el sistema BioQueue.
 * Extiende @see Persona e implementa @see Donante, heredando todos
 * los atributos y comportamientos comunes de una persona registrada en el sistema.
 */
public class DonanteImpl extends Persona implements Donante {

    /**
     * Constructor de la clase DonanteImpl.
     *
     * @param cedulaDeIdentidad  Cédula de identidad única del donante.
     * @param nombre             Nombre completo del donante.
     * @param tipoDeOrganoDonado Tipo de órgano que el donante ofrece al sistema.
     * @param tipoDeSangre       Tipo de sangre del donante.
     * @param edad               Edad del donante en años.
     */
    public DonanteImpl(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoDonado, edad);
    }

}