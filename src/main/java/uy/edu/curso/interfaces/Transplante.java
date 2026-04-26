package uy.edu.curso.interfaces;


public interface Transplante {

    long getIdentificador();

    Receptor getReceptorDelTransplante();

    Organo getOrganoTransplantado();

    Donante getDonanteDelOrganoDelTransplante();

}