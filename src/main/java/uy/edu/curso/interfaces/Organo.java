package uy.edu.curso.interfaces;


public interface Organo {

    long getIdentificador();

    boolean getEsInfantil();

    String getNombre();

    String getTipoDeSangre();

    Donante getDonanteDelOrgano();

    void setNombre(String nombre);

    void setTipoDeSangre(String tipoDeSangre);

    boolean esCompatible(String tipoDeSangreDelReceptor);

}