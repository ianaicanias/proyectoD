/*
 * Interfaz: ConsultorDeCompatibilidadSanguinea.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define el contrato para consultar la compatibilidad
 * sanguínea entre un donante y un receptor dentro del sistema BioQueue.
 * Establece el método que debe ser implementado por cualquier clase que
 * desee evaluar si dos tipos de sangre son compatibles según criterios médicos.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que define el comportamiento de un consultor de compatibilidad sanguínea.
 * Las clases que implementen esta interfaz (ejemplo: @see ConsultorDeCompatibilidadSanguineaImpl) 
 * deberán proporcionar la lógica necesaria para determinar si un tipo de sangre de un donante 
 * es compatible con el de un receptor.
 */
public interface ConsultorDeCompatibilidadSanguinea {

    /**
     * Determina si un tipo de sangre de donante es compatible con el de un receptor.
     *
     * @param tipoDeSangreDelDonante   Tipo de sangre del donante.
     * @param tipoDeSangreDelReceptor  Tipo de sangre del receptor.
     * @return {@code true} si los tipos de sangre son compatibles, {@code false} en caso contrario.
     */
    boolean esCompatible(String tipoDeSangreDelDonante, String tipoDeSangreDelReceptor);

}