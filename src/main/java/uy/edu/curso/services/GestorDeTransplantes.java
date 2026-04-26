package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.Transplante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeTransplantes {

    private final TDAListaEnlazada<Transplante> listaDeTransplantesRealizados;

    public GestorDeTransplantes() {
        this.listaDeTransplantesRealizados = new ListaEnlazada<>();
    }

    public TDAListaEnlazada<Transplante> getListaDeTransplantesRealizados() {
        return this.listaDeTransplantesRealizados;
    }

    public void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles) {
        for (int i = 0; i < colaDePrioridadDeReceptores.tamaño(); i++) {
            Receptor receptorEncontrado = colaDePrioridadDeReceptores.obtener(i);
            boolean esCompatible = organo.esCompatible(receptorEncontrado.getTipoDeSangre());
            boolean esMismoOrgano = organo.getNombre().equals(receptorEncontrado.getTipoDeOrgano());

            if (esCompatible && esMismoOrgano) {
                Transplante nuevoTransplanteRealizado = new Transplante(receptorEncontrado, organo);

                colaDePrioridadDeReceptores.remover(receptorEncontrado);
                listaDeReceptores.remover(receptorEncontrado);
                listaDeOrganosDisponibles.remover(organo);

                listaDeTransplantesRealizados.agregar(nuevoTransplanteRealizado);

                break;
            }
        }
    }

    public Transplante buscarTransplante(long identificadorDelTransplante) {
        int i = 0;

        while (i < this.listaDeTransplantesRealizados.tamaño()) {
            Transplante transplanteEncontrado = this.listaDeTransplantesRealizados.obtener(i);

            if (transplanteEncontrado.getIdentificador() == identificadorDelTransplante) {
                return transplanteEncontrado;
            }
            i++;
        }

        return null;
    }

    public String listarTransplantesRealizados() {
        StringBuilder pagina = new StringBuilder();
        int i = 0;

        pagina.append("-------------------- LISTA DE TRANSPLANTES REALIZADOS --------------------\n");
        while (i < this.listaDeTransplantesRealizados.tamaño()) {
            Transplante transplanteEncontrado = this.listaDeTransplantesRealizados.obtener(i);
            String renglon = transplanteEncontrado.toString();

            pagina.append(renglon);
            pagina.append("\n--------------------------------------------\n");
            i++;
        }

        return pagina.toString();
    }

}