package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeTrasplantes {

    TDAListaEnlazada<Trasplante> getListaDeTrasplantesRealizados();

    void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles);
    
    Trasplante buscarTrasplante(long identificadorDelTransplante);

    String listarTrasplantesRealizados();

}