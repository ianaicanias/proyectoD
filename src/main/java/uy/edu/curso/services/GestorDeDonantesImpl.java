package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.DonanteImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeDonantesImpl implements GestorDeDonantes {

    private final TDAListaEnlazada<Donante> listaDeDonantes;

    public GestorDeDonantesImpl() {
        this.listaDeDonantes = new ListaEnlazada<>();
    }

    public TDAListaEnlazada<Donante> getListaDeDonantes() {
        return this.listaDeDonantes;
    }

    @Override
    public Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad) {
        if (this.buscarDonante(cedulaDeIdentidad) != null) {
            return null;
        }
        Donante donante = new DonanteImpl(cedulaDeIdentidad, nombre, tipoDeOrganoDonado, tipoDeSangre, edad);

        this.listaDeDonantes.agregar(donante);
        
        return donante;
    }

    @Override
    public Donante buscarDonante(String cedulaDeIdentidad) {
        int i = 0;

        while (i < this.listaDeDonantes.tamaño()) {
            String cedulaDeIdentidadDonanteEncontrado = this.listaDeDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidad)) {
                return this.listaDeDonantes.obtener(i);
            }
            i++;
        }
        
        return null;
    }

    @Override
    public void eliminarDonante(String cedulaDeIdentidad) {
        int i = 0;
        
        while (i < this.listaDeDonantes.tamaño()) {
            String cedulaDeIdentidadDonanteEncontrado = this.listaDeDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidad)) {
                this.listaDeDonantes.remover(i);
                break;
            }
            i++;
        }
    }

    @Override
    public String listarDonantes() {
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("---------------------- DATOS DE DONANTES ----------------------\n");
        while (i < this.listaDeDonantes.tamaño()) {
            StringBuilder renglon = new StringBuilder();
            Donante donanteEncontrado = this.listaDeDonantes.obtener(i);

            renglon.append(donanteEncontrado.getNombre());
            renglon.append(", ");
            renglon.append(donanteEncontrado.getCedulaDeIdentidad());
            renglon.append(", ");
            renglon.append(donanteEncontrado.getTipoDeOrgano());
            renglon.append(", ");
            renglon.append(donanteEncontrado.getTipoDeSangre());
            renglon.append(", ");
            renglon.append(donanteEncontrado.getEdad()); 
            renglon.append(".");
            pagina.append(renglon.toString());
            pagina.append("\n");
            i++;
        }

        return pagina.toString();
    }

}