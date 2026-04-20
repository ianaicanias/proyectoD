package uy.edu.curso;

import java.util.LinkedList;


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
            
            if (listaDonantes.get(i).cedulaDeIdentidad.equals(cedula)){
                listaDonantes.remove(i);
                break;
            }
            i++;
        }
    }

    public Donante buscarDonante(String cedula) {
        int i = 0;
        while (i < listaDonantes.size()) {

            if (listaDonantes.get(i).cedulaDeIdentidad.equals(cedula)) {
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
            renglon.append(listaDonantes.get(i).nombre + ", " + listaDonantes.get(i).cedulaDeIdentidad + ", " + listaDonantes.get(i).tipoDeOrgano + ", " + listaDonantes.get(i).tipoDeSangre + ", " +listaDonantes.get(i).edad + ".");
            pagina.append(renglon.toString());
            i++;
        }
        return pagina.toString();
    }
}
