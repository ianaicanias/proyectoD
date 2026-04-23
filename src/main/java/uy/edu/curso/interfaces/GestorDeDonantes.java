package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeDonantes {

    TDAListaEnlazada<Donante> getListaDeDonantes();

    Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad);
    
    Donante buscarDonante(String cedulaDeIdentidad);

    void eliminarDonante(String cedulaDeIdentidad);

    String listarDonantes();

}