package uy.edu.curso.classes;


public class Organo {

    private static long contadorDeOrganosExistentes = 0;
    private final long identificador;
    private final boolean esInfantil;
    private String nombre;
    private String tipoDeSangre;
    private String cedulaDeIdentidadDelDonante;

    public Organo(String nombre, Donante donante) {
        this.identificador = ++contadorDeOrganosExistentes;
        this.nombre = nombre;
        this.tipoDeSangre = donante.getTipoDeSangre();
        this.esInfantil = donante.getEdad() < 18;
        this.cedulaDeIdentidadDelDonante = donante.getCedulaDeIdentidad();
    }

    public long getIdentificador() {
        return this.identificador;
    }

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

}