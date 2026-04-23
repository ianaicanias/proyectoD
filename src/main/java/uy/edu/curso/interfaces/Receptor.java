package uy.edu.curso.interfaces;


public interface Receptor {

    byte getPuntajeDePrioridad();

    void setPuntajeDePrioridad(byte puntajeDePrioridad);

    String getCedulaDeIdentidad();

    String getNombre();

    String getTipoDeSangre();

    String getTipoDeOrgano();

    byte getEdad();

    void setNombre(String nombre);

    void setTipoDeSangre(String tipoDeSangre);

    void setTipoDeOrgano(String tipoDeOrgano);

    void setEdad(byte edad);

}