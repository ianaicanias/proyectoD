package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;


public class TrasplanteImpl implements Trasplante {

    private static long cantidadDeTrasplanteRealizados = 0;
    private final long identificador;
    private final Receptor receptorDelTrasplante;
    private final Organo organoTrasplantado;
    private final Donante donanteDelOrganoDelTrasplante;

    public TrasplanteImpl(Receptor receptorDelTrasplante, Organo organoTrasplantado) {
        this.identificador = ++cantidadDeTrasplanteRealizados;
        this.receptorDelTrasplante = receptorDelTrasplante;
        this.organoTrasplantado = organoTrasplantado;
        this.donanteDelOrganoDelTrasplante = organoTrasplantado.getDonanteDelOrgano();
    }

    @Override
    public long getIdentificador() {
        return this.identificador;
    }
    
    @Override
    public Receptor getReceptorDelTrasplante() {
        return this.receptorDelTrasplante;
    }

    @Override
    public Organo getOrganoTrasplantado() {
        return this.organoTrasplantado;
    }

    @Override
    public Donante getDonanteDelOrganoDelTrasplante() {
        return this.donanteDelOrganoDelTrasplante;
    }

    @Override
    public String toString() {
        return "Trasplante realizado, con ID: " + identificador + ".\n" 
                + "Órgano: " + organoTrasplantado.getNombre() + ".\n"
                + "De parte del donante: " + donanteDelOrganoDelTrasplante.getNombre() + ".\n"
                + "Para el receptor: " + receptorDelTrasplante.getNombre() + ".";
    }

}