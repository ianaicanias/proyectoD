package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeTrasplantes {

    TDAListaEnlazada<Trasplante> getListaDeTrasplantesRealizados();

    void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles);
    
    Trasplante buscarTrasplante(long identificadorDelTrasplante);

    String listarTrasplantesRealizados();

}