package uy.edu.curso.classes;

public class Organo {
    String nombre;
    String tipoSangre;
    Boolean esInfantil;
    String cedulaDonante;

    public Organo(String nombre, Donante donante) {
        this.nombre = nombre;
        this.tipoSangre = donante.tipoDeSangre;
        if (donante.edad < 18) {
            this.esInfantil = true;
        } else {
            this.esInfantil = false;
        }
        this.cedulaDonante=donante.cedulaDeIdentidad;
    }

    // PODEMOS AUTOMATIZAR ESTO Y QUE DESDE EL RECEPTOR SE PUEDA CREAR UN ORGANO,
    // AHI YA SE AGARRAN TODOS LOS DATOS NECESARIOS PARA CREAR LA NUEVA INSTANCIA

}
