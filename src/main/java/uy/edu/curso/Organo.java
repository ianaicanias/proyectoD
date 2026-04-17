package uy.edu.curso;

public class Organo {
    String nombre;
    String tipoSangre;
    Boolean esInfantil;
    int cedulaDonante;

    public Organo(String nombre, Donante donante) {
        this.nombre = nombre;
        this.tipoSangre = donante.tipoSangre;
        if (donante.edad < 18) {
            this.esInfantil = true;
        } else {
            this.esInfantil = false;
        }
        this.cedulaDonante=donante.cedula;
    }

    // PODEMOS AUTOMATIZAR ESTO Y QUE DESDE EL RECEPTOR SE PUEDA CREAR UN ORGANO,
    // AHI YA SE AGARRAN TODOS LOS DATOS NECESARIOS PARA CREAR LA NUEVA INSTANCIA

}
