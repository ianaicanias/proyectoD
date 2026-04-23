package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public interface GestorDeReceptores {

    TDAListaEnlazada<Receptor> getListaDeReceptores();

    TDAColaEnlazada<Receptor> getColaDePrioridadDeReceptores();

    Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad);

    void insertarReceptorEnLaCola(Receptor receptor);

    Receptor buscarReceptor(String cedulaDeIdentidad);

    void eliminarReceptor(String cedulaDeIdentidad);

    String listarReceptores();

}