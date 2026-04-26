package uy.edu.curso.interfaces;


public interface Trasplante {

    long getIdentificador();

    Receptor getReceptorDelTrasplante();

    Organo getOrganoTrasplantado();

    Donante getDonanteDelOrganoDelTrasplante();

}