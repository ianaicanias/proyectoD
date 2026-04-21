package uy.edu.curso.interfaces;

import uy.edu.curso.classes.Persona;


public interface GestorDeDonantes {

    Persona registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad);
    
    Persona buscarDonante(String cedulaDeIdentidad);

    void eliminarDonante(String cedulaDeIdentidad);

    String listarDonantes();

}