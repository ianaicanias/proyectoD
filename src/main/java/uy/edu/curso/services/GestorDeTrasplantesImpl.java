package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.TrasplanteImpl;
import uy.edu.curso.interfaces.GestorDeTrasplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeTrasplantesImpl implements GestorDeTrasplantes {

    private final TDAListaEnlazada<Trasplante> listaDeTrasplantesRealizados;

    public GestorDeTrasplantesImpl() {
        this.listaDeTrasplantesRealizados = new ListaEnlazada<>();
    }

    @Override
    public TDAListaEnlazada<Trasplante> getListaDeTrasplantesRealizados() {
        return this.listaDeTrasplantesRealizados;
    }

    @Override
    public void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles) {
        int tamañoDeLaColaDePrioridad = listaDePrioridadDeReceptores.tamaño();

        for (int i = 0; i < tamañoDeLaColaDePrioridad; i++) {
            Receptor receptorEncontrado = listaDePrioridadDeReceptores.obtener(i);
            boolean esCompatible = organo.esCompatible(receptorEncontrado.getTipoDeSangre());
            boolean esMismoOrgano = organo.getNombre().equals(receptorEncontrado.getTipoDeOrgano());

            if (esCompatible && esMismoOrgano) {
                Trasplante nuevoTrasplanteRealizado = new TrasplanteImpl(receptorEncontrado, organo);

                listaDePrioridadDeReceptores.remover(receptorEncontrado);
                listaDeReceptores.remover(receptorEncontrado);
                listaDeOrganosDisponibles.remover(organo);
                this.listaDeTrasplantesRealizados.agregar(nuevoTrasplanteRealizado);
                break;
            }
        }
    }

    @Override
    public Trasplante buscarTrasplante(long identificadorDelTrasplante) {
        int tamañoDeLaListaDeTrasplantesRealizados = this.listaDeTrasplantesRealizados.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeTrasplantesRealizados) {
            Trasplante trasplanteEncontrado = this.listaDeTrasplantesRealizados.obtener(i);

            if (trasplanteEncontrado.getIdentificador() == identificadorDelTrasplante) {
                return trasplanteEncontrado;
            }
            i++;
        }

        return null;
    }

    @Override
    public String listarTrasplantesRealizados() {
        int tamañoDeLaListaDeTrasplantesRealizados = this.listaDeTrasplantesRealizados.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");
        while (i < tamañoDeLaListaDeTrasplantesRealizados) {
            Trasplante trasplanteEncontrado = this.listaDeTrasplantesRealizados.obtener(i);
            String renglon = trasplanteEncontrado.toString();

            pagina.append(renglon);
            pagina.append("\n--------------------------------------------\n");
            i++;
        }

        return pagina.toString();
    }

}