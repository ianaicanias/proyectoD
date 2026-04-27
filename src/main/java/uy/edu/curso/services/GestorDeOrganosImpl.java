package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.OrganoImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeOrganos;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeOrganosImpl implements GestorDeOrganos {

    private final TDAListaEnlazada<Organo> listaDeOrganos;

    public GestorDeOrganosImpl() {
        this.listaDeOrganos = new ListaEnlazada<>();
    }

    @Override
    public TDAListaEnlazada<Organo> getListaDeOrganosDisponibles() {
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
        int tamañoDeLaListaDeOrganos = this.listaDeOrganos.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeOrganos) {
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
        int tamañoDeLaListaDeOrganos = this.listaDeOrganos.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeOrganos) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getNombre().equalsIgnoreCase(nombreDelOrgano)) {
                resultado.agregar(organo);
            }
            i++;
        }

        return resultado;
    }

    @Override
    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangreDelOrgano) {
        TDAListaEnlazada<Organo> resultado = new ListaEnlazada<>();
        int tamañoDeLaListaDeOrganos = this.listaDeOrganos.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeOrganos) {
            Organo organo = this.listaDeOrganos.obtener(i);

            if (organo.getTipoDeSangre().equalsIgnoreCase(tipoDeSangreDelOrgano)) {
                resultado.agregar(organo);
            }
            i++;
        }

        return resultado;
    }

    @Override
    public void eliminarOrgano(long identificadorDelOrgano) {
        int tamañoDeLaListaDeOrganos = this.listaDeOrganos.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeOrganos) {
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
        int tamañoDeLaListaDeOrganos = this.listaDeOrganos.tamaño();
        int i = 0;
        StringBuilder resultado = new StringBuilder();

        resultado.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");
        while (i < tamañoDeLaListaDeOrganos) {
            Organo organo = this.listaDeOrganos.obtener(i);

            resultado.append(organo.getIdentificador());
            resultado.append(", ");
            resultado.append(organo.getNombre());
            resultado.append(", ");
            resultado.append(organo.getTipoDeSangre());
            resultado.append(", ");
            resultado.append(organo.getDonanteDelOrgano().getCedulaDeIdentidad());
            resultado.append(", ");
            resultado.append(organo.getEsInfantil());
            resultado.append(".");
            resultado.append("\n");
            i++;
        }

        return resultado.toString();
    }
    
}