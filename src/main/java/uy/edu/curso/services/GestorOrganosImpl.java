package uy.edu.curso.services;

import java.util.LinkedList;

import uy.edu.curso.classes.Organo;

public class GestorOrganosImpl {
    private LinkedList<Organo> listaOrganos;

    public GestorOrganosImpl() {
        this.listaOrganos = new LinkedList<>();
    }

    public void registrarOrgano(Organo organo) {
        listaOrganos.add(organo);
    }

    public void eliminarOrgano(Organo organo) {
        listaOrganos.remove(organo);
    }

}