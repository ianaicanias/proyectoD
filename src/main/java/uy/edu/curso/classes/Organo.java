package uy.edu.curso.classes;


public class Organo {

    private String nombre;
    private String tipoDeSangre;
    private String cedulaDeIdentidadDelDonante;
    private boolean esInfantil;

    public String getNombre() {
        return this.nombre;
    }

    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    public String getCedulaDeIdentidadDelDonante() {
        return this.cedulaDeIdentidadDelDonante;
    }

    public boolean getEsInfantil() {
        return this.esInfantil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    public void setCedulaDeIdentidadDelDonante(String cedulaDeIdentidadDelDonante) {
        this.cedulaDeIdentidadDelDonante = cedulaDeIdentidadDelDonante;
    }

    public void setEsInfantil(boolean esInfantil) {
        this.esInfantil = esInfantil;
    }

    public Organo(String nombre, Donante donante) {
        this.nombre = nombre;
        this.tipoDeSangre = donante.tipoDeSangre;
        this.esInfantil = donante.edad < 18;
        this.cedulaDeIdentidadDelDonante = donante.cedulaDeIdentidad;
    }

}