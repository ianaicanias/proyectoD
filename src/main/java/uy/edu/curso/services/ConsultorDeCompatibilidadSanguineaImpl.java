/*
 * Clase: ConsultorDeCompatibilidadSanguineaImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta del consultor de
 * compatibilidad sanguínea en el sistema BioQueue. Implementa la interfaz
 * ConsultorDeCompatibilidadSanguinea y encapsula las reglas médicas de
 * compatibilidad entre tipos de sangre de donantes y receptores.
 */
package uy.edu.curso.services;

import uy.edu.curso.interfaces.ConsultorDeCompatibilidadSanguinea;


/**
 * Implementación concreta del consultor de compatibilidad sanguínea.
 * Implementa @see ConsultorDeCompatibilidadSanguinea y encapsula las reglas
 * médicas de compatibilidad entre tipos de sangre, determinando si un órgano
 * de un donante puede ser trasplantado a un receptor dado.
 */
public class ConsultorDeCompatibilidadSanguineaImpl implements ConsultorDeCompatibilidadSanguinea {

    /**
     * Tabla de combinaciones compatibles entre tipos de sangre de donante y receptor.
     * Cada entrada representa una combinación válida en formato "TipoDonanteTipoReceptor"
     * (ej: "A+AB+" indica que un donante A+ es compatible con un receptor AB+).
     * Las reglas son fijas y están determinadas por criterios médicos.
     * Para más detalle ver la tabla de compatibilidades al final del README del proyecto.
     */
    private static final String[] COMPATIBILIDADES = { 
        "A+A+", "A+AB+", 
        "A-A+", "A-A-", "A-AB+", "A-AB-", 
        "B+B+", "B+AB+", 
        "B-B+", "B-B-", "B-AB+", "B-AB-", 
        "AB+AB+", 
        "AB-AB+", "AB-AB-",
        "O+A+", "O+B+", "O+AB+", "O+O+",
        "O-A+", "O-A-", "O-B+", "O-B-", "O-AB+", "O-AB-", "O-O+", "O-O-"
    };

    /**
     * Determina si un tipo de sangre de donante es compatible con el de un receptor,
     * consultando la tabla de compatibilidades médicas.
     *
     * @param tipoDeSangreDelDonante   Tipo de sangre del donante.
     * @param tipoDeSangreDelReceptor  Tipo de sangre del receptor.
     * @return {@code true} si los tipos de sangre son compatibles, {@code false} en caso contrario.
     */
    @Override
    public boolean esCompatible(String tipoDeSangreDelDonante, String tipoDeSangreDelReceptor) {
        String combinacion = tipoDeSangreDelDonante + tipoDeSangreDelReceptor;

        for (String combinacionCompatible : COMPATIBILIDADES) {
            if (combinacion.equals(combinacionCompatible)) {
                return true;
            }
        }

        return false;
    }

}