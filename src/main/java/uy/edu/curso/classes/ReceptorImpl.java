package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Receptor;


public class ReceptorImpl extends Persona implements Receptor {

    private byte puntajeDePrioridad;

    public ReceptorImpl(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        super(cedulaDeIdentidad, nombre, tipoDeSangre, tipoDeOrganoNecesitado, edad);
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

    @Override
    public byte getPuntajeDePrioridad() {
        return this.puntajeDePrioridad;
    }

    @Override
    public void setPuntajeDePrioridad(byte puntajeDePrioridad) {
        this.puntajeDePrioridad = puntajeDePrioridad;
    }

}