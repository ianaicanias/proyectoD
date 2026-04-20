package uy.edu.curso;


public class Donante extends Persona {

    public Donante(String cedulaDeIdentidad, String nombre, String tipoDeSangre, 
                    String tipoDeOrganoNecesitado, byte edad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoNecesitado, edad);
    }

}