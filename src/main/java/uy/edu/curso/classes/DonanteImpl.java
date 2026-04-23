package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;


public class DonanteImpl extends Persona implements Donante {

    public DonanteImpl(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoDonado, edad);
    }

}