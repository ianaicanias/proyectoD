package uy.edu.curso;


public class Donante extends Persona {

    public Donante(String nombre, String tipoSangre, int cedula, byte edad, String organoADonar) {
        this.nombre = nombre;
        this.tipoSangre = tipoSangre;
        this.cedula = cedula;
        this.edad = edad;
        this.organo = organoADonar;
    }

}