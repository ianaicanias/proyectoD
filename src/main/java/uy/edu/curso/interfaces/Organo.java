package uy.edu.curso.interfaces;


public interface Organo {

    long getIdentificador();

    String getNombre();

    String getTipoDeSangre();

    String getCedulaDeIdentidadDelDonante();

    boolean getEsInfantil();

    void setNombre(String nombre);

    void setTipoDeSangre(String tipoDeSangre);

    void setCedulaDeIdentidadDelDonante(String cedulaDeIdentidadDelDonante);

}