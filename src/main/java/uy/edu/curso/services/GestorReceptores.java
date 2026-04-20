package uy.edu.curso.services;

import java.util.LinkedList;

import uy.edu.curso.classes.Receptor;


public class GestorReceptores {
    private LinkedList<Receptor> listaReceptores;

    public GestorReceptores() {
        this.listaReceptores = new LinkedList<>();
    }

    public void registrarReceptor(Receptor receptor) {

        int i = 0;
        while ((i < listaReceptores.size() && listaReceptores.get(i).getPuntajeDePrioridad() > receptor.getPuntajeDePrioridad())) {
            i++;
        }
        while ((i < listaReceptores.size()) && (listaReceptores.get(i).getPuntajeDePrioridad() == receptor.getPuntajeDePrioridad())
                && (listaReceptores.get(i).getEdad() > receptor.getEdad())) {
            i++;
        }
        listaReceptores.add(i, receptor);

    }

    public void eliminarReceptor(String cedula) {
        int i = 0;
        while (i < listaReceptores.size()) {

            if (listaReceptores.get(i).getCedulaDeIdentidad().equals(cedula)) {
                listaReceptores.remove(i);
                break;
            }
            i++;
        }
    }

    public Receptor buscarReceptor(String cedula) {
        int i = 0;
        while (i < listaReceptores.size()) {

            if (listaReceptores.get(i).getCedulaDeIdentidad().equals(cedula)) {
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
            renglon.append(listaReceptores.get(i).getNombre() + ", " + listaReceptores.get(i).getCedulaDeIdentidad() + ", "
                    + listaReceptores.get(i).getTipoDeOrgano() + ", " + listaReceptores.get(i).getTipoDeSangre() + ", "
                    + listaReceptores.get(i).getEdad() + ".");
            pagina.append(renglon.toString());
            i++;
        }
        return pagina.toString();
    }

}