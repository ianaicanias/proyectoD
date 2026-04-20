package uy.edu.curso.services;

import java.util.LinkedList;

import uy.edu.curso.classes.Donante;


public class GestorDonantes {

    private LinkedList<Donante> listaDonantes;

    public GestorDonantes() {
        this.listaDonantes = new LinkedList<>();
    }

    public void registrarDonante (Donante donante){
        listaDonantes.add(donante);
    }

    public void eliminarDonante (String cedula){
        int i = 0;
        while (i<listaDonantes.size()){
            
            if (listaDonantes.get(i).getCedulaDeIdentidad().equals(cedula)){
                listaDonantes.remove(i);
                break;
            }
            i++;
        }
    }

    public Donante buscarDonante(String cedula) {
        int i = 0;
        while (i < listaDonantes.size()) {

            if (listaDonantes.get(i).getCedulaDeIdentidad().equals(cedula)) {
                return listaDonantes.get(i);
            }
            i++;
        }
        return null;
    }

    public String listarDonantes (){
        int i = 0;
        StringBuilder pagina = new StringBuilder ();
        pagina.append("DATOS DE DONANTES:");
        while (i<listaDonantes.size()){
            StringBuilder renglon = new StringBuilder ();
            renglon.append(listaDonantes.get(i).getNombre() + ", " + listaDonantes.get(i).getCedulaDeIdentidad() + ", " + listaDonantes.get(i).getTipoDeOrgano() + ", " + listaDonantes.get(i).getTipoDeSangre() + ", " +listaDonantes.get(i).getEdad() + ".");
            pagina.append(renglon.toString());
            i++;
        }
        return pagina.toString();
    }
}
