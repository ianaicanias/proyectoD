package uy.edu.curso;

import java.util.LinkedList;

public class GestorOrganos {
private LinkedList<Organo> listaOrganos;

public GestorOrganos(){
    this.listaOrganos=new LinkedList<>();
}

    public void registrarOrgano (Organo organo){
        listaOrganos.add(organo);
    }

    public void eliminarOrgano (Organo organo){
        listaOrganos.remove(organo);
    }

}