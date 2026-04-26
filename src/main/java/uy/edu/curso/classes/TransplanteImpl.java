package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Transplante;


public class TransplanteImpl implements Transplante {

    private static long CANTIDAD_DE_TRANSPLANTES_REALIZADOS = 0;
    private final long identificador;
    private final Receptor receptorDelTransplante;
    private final Organo organoTransplantado;
    private final Donante donanteDelOrganoDelTransplante;

    public TransplanteImpl(Receptor receptorDelTransplante, Organo organoTransplantado) {
        this.identificador = ++CANTIDAD_DE_TRANSPLANTES_REALIZADOS;
        this.receptorDelTransplante = receptorDelTransplante;
        this.organoTransplantado = organoTransplantado;
        this.donanteDelOrganoDelTransplante = organoTransplantado.getDonanteDelOrgano();
    }

    @Override
    public long getIdentificador() {
        return this.identificador;
    }
    
    @Override
    public Receptor getReceptorDelTransplante() {
        return this.receptorDelTransplante;
    }

    @Override
    public Organo getOrganoTransplantado() {
        return this.organoTransplantado;
    }

    @Override
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