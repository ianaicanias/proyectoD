package uy.edu.curso.classes;


public class Receptor extends Persona {

    private byte puntajeDePrioridad;

    public Receptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoNecesitado, edad);
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

    public byte getPuntajeDePrioridad() {
        return this.puntajeDePrioridad;
    }

    public void setPuntajeDePrioridad(byte puntajeDePrioridad) {
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

}