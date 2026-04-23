package uy.edu.curso.interfaces;


public interface GestorDeDonantes {

    Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad);
    
    Donante buscarDonante(String cedulaDeIdentidad);

    void eliminarDonante(String cedulaDeIdentidad);

    String listarDonantes();

}