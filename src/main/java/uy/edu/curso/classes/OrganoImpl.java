package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;

public class OrganoImpl implements Organo {

    private static final String[][] COMPATIBILIDADES = {
        { "A+", "A+", "AB+" },
        { "A-", "A+", "A-", "AB+", "AB-" },
        { "B+", "B+", "AB+" },
        { "B-", "B+", "B-", "AB+", "AB-" },
        { "AB+", "AB+" },
        { "AB-", "AB+", "AB-" },
        { "O+", "A+", "B+", "AB+", "O+" },
        { "O-", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }
    };
    private static long contadorDeOrganosExistentes = 0;
    private final long identificador;
    private final boolean esInfantil;
    private final Donante donanteDelOrgano;
    private String nombre;
    private String tipoDeSangre;

    public OrganoImpl(String nombre, Donante donante) {
        this.identificador = ++contadorDeOrganosExistentes;
        this.esInfantil = donante.getEdad() < 18;
        this.donanteDelOrgano = donante;
        this.nombre = nombre;
        this.tipoDeSangre = donante.getTipoDeSangre();
    }

    @Override
    public long getIdentificador() {
        return this.identificador;
    }

    @Override
    public boolean getEsInfantil() {
        return this.esInfantil;
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
    public Donante getDonanteDelOrgano() {
        return this.donanteDelOrgano;
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
    public boolean esCompatible(String tipoDeSangreDelReceptor) {
        for (String[] fila : COMPATIBILIDADES) {
            if (fila[0].equals(this.tipoDeSangre)) {
                for (int i = 1; i < fila.length; i++) {
                    if (fila[i].equals(tipoDeSangreDelReceptor)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}