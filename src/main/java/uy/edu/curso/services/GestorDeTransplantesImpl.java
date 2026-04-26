package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.TransplanteImpl;
import uy.edu.curso.interfaces.GestorDeTransplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Transplante;
import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeTransplantesImpl implements GestorDeTransplantes {

    private final TDAListaEnlazada<Transplante> listaDeTransplantesRealizados;

    public GestorDeTransplantesImpl() {
        this.listaDeTransplantesRealizados = new ListaEnlazada<>();
    }

    @Override
    public TDAListaEnlazada<Transplante> getListaDeTransplantesRealizados() {
        return this.listaDeTransplantesRealizados;
    }

    @Override
    public void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles) {
        int tamañoDeLaColaDePrioridad = colaDePrioridadDeReceptores.tamaño();

        for (int i = 0; i < tamañoDeLaColaDePrioridad; i++) {
            Receptor receptorEncontrado = colaDePrioridadDeReceptores.obtener(i);
            boolean esCompatible = organo.esCompatible(receptorEncontrado.getTipoDeSangre());
            boolean esMismoOrgano = organo.getNombre().equals(receptorEncontrado.getTipoDeOrgano());

            if (esCompatible && esMismoOrgano) {
                Transplante nuevoTransplanteRealizado = new TransplanteImpl(receptorEncontrado, organo);

                colaDePrioridadDeReceptores.remover(receptorEncontrado);
                listaDeReceptores.remover(receptorEncontrado);
                listaDeOrganosDisponibles.remover(organo);

                listaDeTransplantesRealizados.agregar(nuevoTransplanteRealizado);

                break;
            }
        }
    }

    @Override
    public Transplante buscarTransplante(long identificadorDelTransplante) {
        int tamañoDeLaListaDeTransplantesRealizados = this.listaDeTransplantesRealizados.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeTransplantesRealizados) {
            Transplante transplanteEncontrado = this.listaDeTransplantesRealizados.obtener(i);

            if (transplanteEncontrado.getIdentificador() == identificadorDelTransplante) {
                return transplanteEncontrado;
            }
            i++;
        }

        return null;
    }

    @Override
    public String listarTransplantesRealizados() {
        int tamañoDeLaListaDeTransplantesRealizados = this.listaDeTransplantesRealizados.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("-------------------- LISTA DE TRANSPLANTES REALIZADOS --------------------\n");
        while (i < tamañoDeLaListaDeTransplantesRealizados) {
            Transplante transplanteEncontrado = this.listaDeTransplantesRealizados.obtener(i);
            String renglon = transplanteEncontrado.toString();

            pagina.append(renglon);
            pagina.append("\n--------------------------------------------\n");
            i++;
        }

        return pagina.toString();
    }

}