package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;


public class OrganoImpl implements Organo {

    private static long contadorDeOrganosExistentes = 0;
    private final long identificador;
    private final boolean esInfantil;
    private String nombre;
    private String tipoDeSangre;
    private String cedulaDeIdentidadDelDonante;

    public OrganoImpl(String nombre, Donante donante) {
        this.identificador = ++contadorDeOrganosExistentes;
        this.nombre = nombre;
        this.tipoDeSangre = donante.getTipoDeSangre();
        this.esInfantil = donante.getEdad() < 18;
        this.cedulaDeIdentidadDelDonante = donante.getCedulaDeIdentidad();
    }

    @Override
    public long getIdentificador() {
        return this.identificador;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    @Override
    public String getCedulaDeIdentidadDelDonante() {
        return this.cedulaDeIdentidadDelDonante;
    }

    @Override
    public boolean getEsInfantil() {
        return this.esInfantil;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

    @Override
    public void setCedulaDeIdentidadDelDonante(String cedulaDeIdentidadDelDonante) {
        this.cedulaDeIdentidadDelDonante = cedulaDeIdentidadDelDonante;
    }

}