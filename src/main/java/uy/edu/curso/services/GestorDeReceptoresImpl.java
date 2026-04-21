package uy.edu.curso.services;

import uy.edu.curso.ColaEnlazada;
import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.Persona;
import uy.edu.curso.classes.Receptor;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeReceptoresImpl implements GestorDeReceptores {

    private final TDAListaEnlazada<Receptor> listaDeReceptores;
    private final TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores;

    public GestorDeReceptoresImpl() {
        this.listaDeReceptores = new ListaEnlazada<>();
        this.colaDePrioridadDeReceptores = new ColaEnlazada<>();
    }

    public TDAListaEnlazada<Receptor> getListaDeReceptores() {
        return this.listaDeReceptores;
    }

    public TDAColaEnlazada<Receptor> getColaDePrioridadDeReceptores() {
        return this.colaDePrioridadDeReceptores;
    }

    @Override
    public Persona registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        if (this.buscarReceptor(cedulaDeIdentidad) != null) {
            return null;
        }
        Receptor nuevoReceptor = new Receptor(cedulaDeIdentidad, nombre, tipoDeSangre,
                tipoDeOrganoNecesitado, edad, puntajeDePrioridad);

        this.listaDeReceptores.agregar(nuevoReceptor);

        return nuevoReceptor;
    }

    @Override
    public void insertarReceptorEnLaCola(Persona receptor) {
        int i = 0;
        Receptor nuevoReceptor = (Receptor) receptor;

        while (i < this.colaDePrioridadDeReceptores.tamaño()) {
            Receptor receptorActual = this.colaDePrioridadDeReceptores.obtener(i);
            boolean mayorPrioridad = nuevoReceptor.getPuntajeDePrioridad() > receptorActual.getPuntajeDePrioridad();
            boolean igualPrioridadConDesempate = (nuevoReceptor.getPuntajeDePrioridad() == receptorActual.getPuntajeDePrioridad())
                    && (nuevoReceptor.getEdad() < receptorActual.getEdad());

            if (mayorPrioridad || igualPrioridadConDesempate) {
                this.colaDePrioridadDeReceptores.agregar(i, nuevoReceptor);
                return;
            }
            i++;
        }
        this.colaDePrioridadDeReceptores.poneEnCola((Receptor) nuevoReceptor);
    }

    @Override
    public Receptor buscarReceptor(String cedulaDeIdentidadReceptor) {
        int i = 0;

        while (i < this.listaDeReceptores.tamaño()) {
            Receptor receptorEncontrado = this.listaDeReceptores.obtener(i);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                return receptorEncontrado;
            }
            i++;
        }

        return null;
    }

    @Override
    public void eliminarReceptor(String cedulaDeIdentidadReceptor) {
        int i = 0;
        int j = 0;

        while (i < this.listaDeReceptores.tamaño()) {
            Receptor receptorEncontrado = this.listaDeReceptores.obtener(i);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                this.listaDeReceptores.remover(i);
                break;
            }
            i++;
        }
        while (j < this.colaDePrioridadDeReceptores.tamaño()) {
            Receptor receptorEncontrado = this.colaDePrioridadDeReceptores.obtener(j);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                this.colaDePrioridadDeReceptores.remover(j);
                break;
            }
            j++;
        }
    }

    @Override
    public String listarReceptores() {
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("----------------------- DATOS DE RECEPTORES -----------------------\n");
        while (i < this.listaDeReceptores.tamaño()) {
            StringBuilder renglon = new StringBuilder();
            Receptor receptor = this.listaDeReceptores.obtener(i);

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