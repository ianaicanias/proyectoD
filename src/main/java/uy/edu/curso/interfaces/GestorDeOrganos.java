package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeOrganos {

    TDAListaEnlazada<Organo> getListaDeOrganosDisponibles();

    Organo registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano);

    Organo buscarOrganoPorIdentificador(long identificadorDelOrgano);

    TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano);

    TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre);

    void eliminarOrgano(long identificadorDelOrgano);

    String listarOrganosDisponibles();

}