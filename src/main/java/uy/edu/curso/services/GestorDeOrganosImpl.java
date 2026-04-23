package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.OrganoImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeOrganos;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.tda.TDAListaEnlazada;

public class GestorDeOrganosImpl implements GestorDeOrganos {

    private final TDAListaEnlazada<Organo> listaDeOrganos;

    public GestorDeOrganosImpl() {
        this.listaDeOrganos = new ListaEnlazada<>();
    }

    @Override
    public TDAListaEnlazada<Organo> getListaDeOrganos() {
        return this.listaDeOrganos;
    }

    @Override
    public Organo registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano) {
        Organo nuevoOrgano = new OrganoImpl(nombreDelOrgano, donanteDelOrgano);
        this.listaDeOrganos.agregar(nuevoOrgano);
        return nuevoOrgano;
    }

    @Override
    public Organo buscarOrganoPorIdentificador(long identificadorDelOrgano) {
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getIdentificador() == identificadorDelOrgano) {
                return organo;
            }
            i++;
        }

        return null;
    }

    @Override
    public TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano) {
        TDAListaEnlazada<Organo> resultado = new ListaEnlazada<>();
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getNombre().equalsIgnoreCase(nombreDelOrgano)) {
                resultado.agregar(organo);
            }
            i++;
        }

        return resultado;
    }

    @Override
    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre) {
        TDAListaEnlazada<Organo> resultado = new ListaEnlazada<>();
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getTipoDeSangre().equalsIgnoreCase(tipoDeSangre)) {
                resultado.agregar(organo);
            }
            i++;
        }

        return resultado;
    }

    @Override
    public void eliminarOrgano(long identificadorDelOrgano) {
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getIdentificador() == identificadorDelOrgano) {
                this.listaDeOrganos.remover(i);
                break;
            }
            i++;
        }
    }

    @Override
    public String listarOrganosDisponibles() {
        int i = 0;
        StringBuilder resultado = new StringBuilder();

        while (i < this.listaDeOrganos.tamaño()) {
            Organo organo = this.listaDeOrganos.obtener(i);
            resultado.append(organo.getNombre()).append("\n");
            i++;
        }

        return resultado.toString();
    }

    // -------------------- PARTE 3 --------------------

    @Override
    public boolean esCompatible(String donante, String receptor) {

        if (donante.equals("O-")) return true;

        if (donante.equals("O+")) {
            return receptor.equals("O+") || receptor.equals("A+") || receptor.equals("B+") || receptor.equals("AB+");
        }

        if (donante.equals("A-")) {
            return receptor.equals("A-") || receptor.equals("A+") || receptor.equals("AB-") || receptor.equals("AB+");
        }

        if (donante.equals("A+")) {
            return receptor.equals("A+") || receptor.equals("AB+");
        }

        if (donante.equals("B-")) {
            return receptor.equals("B-") || receptor.equals("B+") || receptor.equals("AB-") || receptor.equals("AB+");
        }

        if (donante.equals("B+")) {
            return receptor.equals("B+") || receptor.equals("AB+");
        }

        if (donante.equals("AB-")) {
            return receptor.equals("AB-") || receptor.equals("AB+");
        }

        if (donante.equals("AB+")) {
            return receptor.equals("AB+");
        }

        return false;
    }

    @Override
    public Receptor asignarOrganoAReceptor(Organo organo, GestorDeReceptores receptores) {

        int i = 0;

        while (i < receptores.getColaDePrioridadDeReceptores().tamaño()) {

            Receptor r = receptores.getColaDePrioridadDeReceptores().obtener(i);

            boolean mismoOrgano = r.getTipoDeOrgano().equalsIgnoreCase(organo.getNombre());
            boolean compatible = esCompatible(organo.getTipoDeSangre(), r.getTipoDeSangre());

            if (mismoOrgano && compatible) {

                receptores.eliminarReceptor(r.getCedulaDeIdentidad());
                this.eliminarOrgano(organo.getIdentificador());

                return r;
            }

            i++;
        }

        return null;
    }
}