package uy.edu.curso.classes;

public class RegistroTransplante {

    private String nombreReceptor;
    private String organo;

    public RegistroTransplante(String nombreReceptor, String organo) {
        this.nombreReceptor = nombreReceptor;
        this.organo = organo;
    }

    @Override
    public String toString() {
        return "Transplante: " + organo + " a " + nombreReceptor;
    }
}
