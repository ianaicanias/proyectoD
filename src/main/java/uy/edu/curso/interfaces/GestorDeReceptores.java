package uy.edu.curso.interfaces;

import uy.edu.curso.classes.Persona;


public interface GestorDeReceptores {

    Persona registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad);

    void insertarReceptorEnLaCola(Persona receptor);

    Persona buscarReceptor(String cedulaDeIdentidad);

    void eliminarReceptor(String cedulaDeIdentidad);

    String listarReceptores();

}