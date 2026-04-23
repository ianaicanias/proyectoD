package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.OrganoImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeOrganosImpl {

    private TDAListaEnlazada<Organo> listaDeOrganos;

    public GestorDeOrganosImpl() {
        this.listaDeOrganos = new ListaEnlazada<>();
    }

    public TDAListaEnlazada<Organo> getListaDeOrganos() {
        return this.listaDeOrganos;
    }

    public Organo registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano) {
        Organo nuevoOrganoRegistrado = new OrganoImpl(nombreDelOrgano, donanteDelOrgano);

        this.listaDeOrganos.agregar(nuevoOrganoRegistrado);

        return nuevoOrganoRegistrado;
    }

    public Organo buscarOrganoPorIdentificador(long identificadorDelOrgano) {
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            long identificadorEncontrado = this.listaDeOrganos.obtener(i).getIdentificador();

            if (identificadorEncontrado == identificadorDelOrgano) {
                return this.listaDeOrganos.obtener(i);
            }
            i++;
        }

        return null;
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano) {
        TDAListaEnlazada<Organo> listaDeOrganosConCoincidenciaDeNombre = new ListaEnlazada<>();
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            String nombreDelOrganoEncontrado = this.listaDeOrganos.obtener(i).getNombre();

            if (nombreDelOrganoEncontrado.equals(nombreDelOrgano)) {
                listaDeOrganosConCoincidenciaDeNombre.agregar(this.listaDeOrganos.obtener(i));
            }
            i++;
        }

        return listaDeOrganosConCoincidenciaDeNombre;
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre) {
        TDAListaEnlazada<Organo> listaDeOrganosConCoincidenciaDeTipoDeSangre = new ListaEnlazada<>();
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            String tipoDeSangreEncontrado = this.listaDeOrganos.obtener(i).getTipoDeSangre();

            if (tipoDeSangreEncontrado.equals(tipoDeSangre)) {
                listaDeOrganosConCoincidenciaDeTipoDeSangre.agregar(this.listaDeOrganos.obtener(i));
            }
            i++;
        }

        return listaDeOrganosConCoincidenciaDeTipoDeSangre;
    }

    public void eliminarOrgano(long identificadorDelOrgano) {
        int i = 0;

        while (i < this.listaDeOrganos.tamaño()) {
            long identificadorEncontrado = this.listaDeOrganos.obtener(i).getIdentificador();

            if (identificadorEncontrado == identificadorDelOrgano) {
                this.listaDeOrganos.remover(i);
                return;
            }
            i++;
        }
    }

    public String listarOrganosDisponibles() {
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");
        while (i < this.listaDeOrganos.tamaño()) {
            StringBuilder renglon = new StringBuilder();
            Organo organo = this.listaDeOrganos.obtener(i);

            renglon.append(organo.getIdentificador());
            renglon.append(", ");
            renglon.append(organo.getNombre());
            renglon.append(", ");
            renglon.append(organo.getTipoDeSangre());
            renglon.append(", ");
            renglon.append(organo.getCedulaDeIdentidadDelDonante());
            renglon.append(", ");
            renglon.append(organo.getEsInfantil());
            renglon.append(".");
            pagina.append(renglon.toString());
            pagina.append("\n");
            i++;
        }

        return pagina.toString();
    }

}