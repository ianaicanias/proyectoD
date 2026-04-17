package uy.edu.curso;

import java.util.LinkedList;

public class GestorReceptores {
    private LinkedList<Receptor> listaReceptores;

    public GestorReceptores() {
        this.listaReceptores = new LinkedList<>();
    }

    public void registrarReceptor(Receptor receptor) {

        int i = 0;
        while ((i < listaReceptores.size() && listaReceptores.get(i).puntajePrioridad > receptor.puntajePrioridad)) {
            i++;
        }
        while ((i < listaReceptores.size()) && (listaReceptores.get(i).puntajePrioridad == receptor.puntajePrioridad)
                && (listaReceptores.get(i).edad > receptor.edad)) {
            i++;
        }
        listaReceptores.add(i, receptor);

    }

    public void eliminarReceptor(int cedula) {
        int i = 0;
        while (i < listaReceptores.size()) {

            if (listaReceptores.get(i).cedula == cedula) {
                listaReceptores.remove(i);
                break;
            }
            i++;
        }
    }

    public Receptor buscarReceptor(int cedula) {
        int i = 0;
        while (i < listaReceptores.size()) {

            if (listaReceptores.get(i).cedula == cedula) {
                return listaReceptores.get(i);
            }
            i++;
        }
        return null;
    }

    public String listarReceptores() {
        int i = 0;
        StringBuilder pagina = new StringBuilder();
        pagina.append("DATOS DE RECEPTORES:");
        while (i < listaReceptores.size()) {
            StringBuilder renglon = new StringBuilder();
            renglon.append(listaReceptores.get(i).nombre + ", " + listaReceptores.get(i).cedula + ", "
                    + listaReceptores.get(i).organo + ", " + listaReceptores.get(i).tipoSangre + ", "
                    + listaReceptores.get(i).edad + ".");
            pagina.append(renglon.toString());
            i++;
        }
        return pagina.toString();
    }
}
