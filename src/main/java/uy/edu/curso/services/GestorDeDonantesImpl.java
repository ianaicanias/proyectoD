package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.Donante;
import uy.edu.curso.classes.Persona;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.tda.TDAListaEnlazada;


public class GestorDeDonantesImpl implements GestorDeDonantes {

    private final TDAListaEnlazada<Donante> listaDonantes;

    public GestorDeDonantesImpl() {
        this.listaDonantes = new ListaEnlazada<>();
    }

    public TDAListaEnlazada<Donante> getListaDeDonantes() {
        return this.listaDonantes;
    }

    @Override
    public Persona registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad) {
        if (this.buscarDonante(cedulaDeIdentidad) != null) {
            return null;
        }
        Donante donante = new Donante(cedulaDeIdentidad, nombre, tipoDeOrganoDonado, tipoDeSangre, edad);

        this.listaDonantes.agregar(donante);
        
        return donante;
    }

    @Override
    public Donante buscarDonante(String cedulaDeIdentidad) {
        int i = 0;

        while (i < listaDonantes.tamaño()) {
            String cedulaDeIdentidadDonanteEncontrado = listaDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidad)) {
                return listaDonantes.obtener(i);
            }
            i++;
        }
        
        return null;
    }

    @Override
    public void eliminarDonante(String cedulaDeIdentidad) {
        int i = 0;
        
        while (i < listaDonantes.tamaño()) {
            String cedulaDeIdentidadDonanteEncontrado = listaDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidad)) {
                listaDonantes.remover(i);
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
        while (i < listaDonantes.tamaño()) {
            StringBuilder renglon = new StringBuilder();
            renglon.append(listaDonantes.obtener(i).getNombre());
            renglon.append(", ");
            renglon.append(listaDonantes.obtener(i).getCedulaDeIdentidad());
            renglon.append(", ");
            renglon.append(listaDonantes.obtener(i).getTipoDeOrgano());
            renglon.append(", ");
            renglon.append(listaDonantes.obtener(i).getTipoDeSangre());
            renglon.append(", ");
            renglon.append(listaDonantes.obtener(i).getEdad()); 
            renglon.append(".");
            pagina.append(renglon.toString());
            i++;
        }

        return pagina.toString();
    }

}