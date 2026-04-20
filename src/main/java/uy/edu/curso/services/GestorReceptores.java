package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.Persona;
import uy.edu.curso.classes.Receptor;
import uy.edu.curso.tda.TDAListaEnlazada;

public class GestorReceptores {

    private final TDAListaEnlazada<Receptor> listaReceptores;

    public GestorReceptores() {
        this.listaReceptores = new ListaEnlazada<>();
    }

    public Persona registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
                                     String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        Receptor nuevoReceptor = new Receptor(cedulaDeIdentidad, nombre, tipoDeSangre, 
                                              tipoDeOrganoNecesitado, edad, puntajeDePrioridad);

        this.listaReceptores.agregar(nuevoReceptor);

        return nuevoReceptor;
    }

    public Receptor buscarReceptor(String cedulaDeIdentidadReceptor) {
        int i = 0;

        while (i < listaReceptores.tamaño()) {
            if (listaReceptores.obtener(i).getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                return listaReceptores.obtener(i);
            }
            i++;
        }

        return null;
    }

    public void eliminarReceptor(String cedulaDeIdentidadReceptor) {
        int i = 0;

        while (i < listaReceptores.tamaño()) {
            if (listaReceptores.obtener(i).getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                listaReceptores.remover(i);
                break;
            }
            i++;
        }
    }

    public String listarReceptores() {
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("----------------------- DATOS DE RECEPTORES -----------------------\n");
        while (i < listaReceptores.tamaño()) {
            StringBuilder renglon = new StringBuilder();
            Receptor receptor = this.listaReceptores.obtener(i);

            renglon.append(receptor.getNombre());
            renglon.append(", ");
            renglon.append(receptor.getCedulaDeIdentidad());
            renglon.append(", ");
            renglon.append(receptor.getTipoDeOrgano());
            renglon.append(", ");
            renglon.append(receptor.getTipoDeSangre());
            renglon.append(", ");
            renglon.append(receptor.getEdad());
            renglon.append(".");
            pagina.append(renglon.toString());
            i++;
        }

        return pagina.toString();
    }

}