package uy.edu.curso.services;

import uy.edu.curso.interfaces.ConsultorDeCompatibilidadSanguinea;


public class ConsultorDeCompatibilidadSanguineaImpl implements ConsultorDeCompatibilidadSanguinea {

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