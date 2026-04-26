package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeTransplantes {

    TDAListaEnlazada<Transplante> getListaDeTransplantesRealizados();

    void asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles);
    
    Transplante buscarTransplante(long identificadorDelTransplante);

    String listarTransplantesRealizados();

}