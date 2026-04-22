package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.Donante;
import uy.edu.curso.classes.Organo;
import uy.edu.curso.classes.Persona;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeOrganosImpl {

    private TDAListaEnlazada<Organo> listaDeOrganos;

    public GestorDeOrganosImpl() {
        this.listaDeOrganos = new ListaEnlazada<>();
    }

    public TDAListaEnlazada<Organo> getListaDeOrganos() {
        return this.listaDeOrganos;
    }

    public Organo registrarOrgano(String nombreDelOrgano, Persona donante) {
        Organo nuevoOrganoRegistrado = new Organo(nombreDelOrgano, (Donante) donante);

        listaDeOrganos.agregar(nuevoOrganoRegistrado);

        return nuevoOrganoRegistrado;
    }

    public void eliminarOrgano(Organo organo) {
        listaDeOrganos.remover(organo);
    }

}