package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;


public class Transplante {

    private static long CANTIDAD_DE_TRANSPLANTES_REALIZADOS = 0;
    private final long identificador;
    private final Receptor receptorDelTransplante;
    private final Organo organoTransplantado;
    private final Donante donanteDelOrganoDelTransplante;

    public Transplante(Receptor receptorDelTransplante, Organo organoTransplantado) {
        this.identificador = ++CANTIDAD_DE_TRANSPLANTES_REALIZADOS;
        this.receptorDelTransplante = receptorDelTransplante;
        this.organoTransplantado = organoTransplantado;
        this.donanteDelOrganoDelTransplante = organoTransplantado.getDonanteDelOrgano();
    }

    public long getIdentificador() {
        return this.identificador;
    }
    
    public Receptor getReceptorDelTransplante() {
        return this.receptorDelTransplante;
    }

    public Organo getOrganoTransplantado() {
        return this.organoTransplantado;
    }

    public Donante getDonanteDelOrganoDelTransplante() {
        return this.donanteDelOrganoDelTransplante;
    }

    @Override
    public String toString() {
        return "Transplante realizado, con ID: " + identificador + ".\n" 
                + "Órgano: " + organoTransplantado.getNombre() + ".\n"
                + "De parte del donante: " + donanteDelOrganoDelTransplante.getNombre() + ".\n"
                + "Para el receptor: " + receptorDelTransplante.getNombre() + ".";
    }

}