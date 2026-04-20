package uy.edu.curso.classes;


public abstract class Persona {

    protected String cedulaDeIdentidad;
    protected String nombre;
    protected String tipoDeSangre;
    protected String tipoDeOrgano;
    protected byte edad;

    public Persona(String cedulaDeIdentidad, String nombre, String tipoDeSangre, String tipoDeOrgano, byte edad) {
        this.cedulaDeIdentidad = cedulaDeIdentidad;
        this.nombre = nombre;
        this.tipoDeSangre = tipoDeSangre;
        this.tipoDeOrgano = tipoDeOrgano;
        this.edad = edad;
    }

    public String getCedulaDeIdentidad() {
        return cedulaDeIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDeSangre() {
        return tipoDeSangre;
    }

    public String getTipoDeOrgano() {
        return tipoDeOrgano;
    }

    public byte getEdad() {
        return edad;
    }

    public void setCedulaDeIdentidad(String cedulaDeIdentidad) {
        this.cedulaDeIdentidad = cedulaDeIdentidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public void setTipoDeOrgano(String tipoDeOrgano) {
        this.tipoDeOrgano = tipoDeOrgano;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }
    
}