package uy.edu.curso.classes;


public abstract class Persona {

    protected final String cedulaDeIdentidad;
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
        return this.cedulaDeIdentidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    public String getTipoDeOrgano() {
        return this.tipoDeOrgano;
    }

    public byte getEdad() {
        return this.edad;
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