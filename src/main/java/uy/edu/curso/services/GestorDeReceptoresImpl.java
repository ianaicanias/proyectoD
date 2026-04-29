package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.ReceptorImpl;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeReceptoresImpl implements GestorDeReceptores {

    private final TDAListaEnlazada<Receptor> listaDeReceptores;
    private final TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores;

    public GestorDeReceptoresImpl() {
        this.listaDeReceptores = new ListaEnlazada<>();
        this.listaDePrioridadDeReceptores = new ListaEnlazada<>();
    }

    @Override
    public TDAListaEnlazada<Receptor> getListaDeReceptores() {
        return this.listaDeReceptores;
    }

    @Override
    public TDAListaEnlazada<Receptor> getListaDePrioridadDeReceptores() {
        return this.listaDePrioridadDeReceptores;
    }

    @Override
    public Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        if (this.buscarReceptor(cedulaDeIdentidad) != null) {
            return null;
        }
        Receptor nuevoReceptor = new ReceptorImpl(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado,
                tipoDeSangre, edad, puntajeDePrioridad);

        this.listaDeReceptores.agregar(nuevoReceptor);

        return nuevoReceptor;
    }

    @Override
    public void insertarReceptorEnLaListaDePrioridad(Receptor nuevoReceptor) {
        int tamañoDeLaListaDePrioridad = this.listaDePrioridadDeReceptores.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDePrioridad) {
            Receptor receptorActual = this.listaDePrioridadDeReceptores.obtener(i);
            boolean mayorPrioridad = nuevoReceptor.getPuntajeDePrioridad() > receptorActual.getPuntajeDePrioridad();
            boolean igualPrioridadConDesempate = (nuevoReceptor.getPuntajeDePrioridad() == receptorActual.getPuntajeDePrioridad())
                    && (nuevoReceptor.getEdad() < receptorActual.getEdad());

            if (mayorPrioridad || igualPrioridadConDesempate) {
                this.listaDePrioridadDeReceptores.agregar(i, nuevoReceptor);
                return;
            }
            i++;
        }
        this.listaDePrioridadDeReceptores.agregar(nuevoReceptor);
    }

    @Override
    public Receptor buscarReceptor(String cedulaDeIdentidadReceptor) {
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeReceptores) {
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
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int tamañoDeLaListaDePrioridad = this.listaDePrioridadDeReceptores.tamaño();
        int i = 0;
        int j = 0;

        while (i < tamañoDeLaListaDeReceptores) {
            Receptor receptorEncontrado = this.listaDeReceptores.obtener(i);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                this.listaDeReceptores.remover(i);
                break;
            }
            i++;
        }
        while (j < tamañoDeLaListaDePrioridad) {
            Receptor receptorEncontrado = this.listaDePrioridadDeReceptores.obtener(j);

            if (receptorEncontrado.getCedulaDeIdentidad().equals(cedulaDeIdentidadReceptor)) {
                this.listaDePrioridadDeReceptores.remover(j);
                break;
            }
            j++;
        }
    }

    @Override
    public String listarReceptores() {
        int tamañoDeLaListaDeReceptores = this.listaDeReceptores.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("----------------------- DATOS DE RECEPTORES -----------------------\n");
        while (i < tamañoDeLaListaDeReceptores) {
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
            pagina.append("\n");
            i++;
        }

        return pagina.toString();
    }

}