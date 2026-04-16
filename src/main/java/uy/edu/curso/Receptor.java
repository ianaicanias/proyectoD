package uy.edu.curso;

public class Receptor extends Persona {
    byte puntajePrioridad;

    public Receptor (String nombre, String tipoSangre, int cedula, byte edad, String organoNecesitado, byte puntajePrioridad){
    this.puntajePrioridad = puntajePrioridad;
    this.nombre=nombre;
    this.tipoSangre=tipoSangre;
    this.cedula=cedula;
    this.edad=edad;
    this.organo=organoNecesitado;
    }
