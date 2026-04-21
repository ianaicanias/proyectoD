package uy.edu.curso.classes;


public class Donante extends Persona {

    public Donante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoDonado, edad);
    }

}