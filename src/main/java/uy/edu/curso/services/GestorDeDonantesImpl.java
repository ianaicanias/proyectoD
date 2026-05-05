/*
 * Clase: GestorDeDonantesImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta del gestor de donantes
 * en el sistema BioQueue. Implementa la interfaz GestorDeDonantes y administra
 * el registro, búsqueda, eliminación y listado de donantes, previniendo
 * duplicados mediante la validación de cédula de identidad.
 */
package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.DonanteImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Implementación concreta del gestor de donantes del sistema BioQueue.
 * Implementa @see GestorDeDonantes y administra el ciclo de vida de los
 * donantes registrados, garantizando que no existan duplicados por cédula
 * de identidad.
 */
public class GestorDeDonantesImpl implements GestorDeDonantes {

    /**
     * Lista enlazada que almacena todos los donantes registrados en el sistema.
     */
    private final TDAListaEnlazada<Donante> listaDeDonantes;

    /**
     * Constructor de la clase GestorDeDonantesImpl. Inicializa la lista de donantes vacía.
     */
    public GestorDeDonantesImpl() {
        this.listaDeDonantes = new ListaEnlazada<>();
    }

    /**
     * Retorna la lista de donantes registrados en el sistema.
     *
     * @return Lista enlazada con todos los donantes.
     */
    @Override
    public TDAListaEnlazada<Donante> getListaDeDonantes() {
        return this.listaDeDonantes;
    }

    /**
     * Registra un nuevo donante en el sistema. Si ya existe un donante con
     * la misma cédula de identidad, no se registra y retorna {@code null}.
     *
     * @param cedulaDeIdentidad  Cédula de identidad única del donante.
     * @param nombre             Nombre completo del donante.
     * @param tipoDeOrganoDonado Tipo de órgano que el donante ofrece.
     * @param tipoDeSangre       Tipo de sangre del donante.
     * @param edad               Edad del donante en años.
     * @return El donante creado, o {@code null} si ya existe un donante con esa cédula.
     */
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

    /**
     * Busca un donante en el sistema por su cédula de identidad.
     *
     * @param cedulaDeIdentidadDelDonante Cédula de identidad del donante a buscar.
     * @return El donante encontrado, o {@code null} si no existe.
     */
    @Override
    public Donante buscarDonante(String cedulaDeIdentidadDelDonante) {
        int tamañoDeLaListaDeDonantes = this.listaDeDonantes.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeDonantes) {
            String cedulaDeIdentidadDonanteEncontrado = this.listaDeDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidadDelDonante)) {
                return this.listaDeDonantes.obtener(i);
            }
            i++;
        }
        
        return null;
    }

    /**
     * Elimina un donante del sistema por su cédula de identidad.
     * Si no existe un donante con esa cédula, no realiza ninguna acción.
     *
     * @param cedulaDeIdentidadDelDonante Cédula de identidad del donante a eliminar.
     */
    @Override
    public void eliminarDonante(String cedulaDeIdentidadDelDonante) {
        int tamañoDeLaListaDeDonantes = this.listaDeDonantes.tamaño();
        int i = 0;
        
        while (i < tamañoDeLaListaDeDonantes) {
            String cedulaDeIdentidadDonanteEncontrado = this.listaDeDonantes.obtener(i).getCedulaDeIdentidad();

            if (cedulaDeIdentidadDonanteEncontrado.equals(cedulaDeIdentidadDelDonante)) {
                this.listaDeDonantes.remover(i);
                break;
            }
            i++;
        }
    }

    /**
     * Retorna una cadena con el listado de todos los donantes registrados,
     * incluyendo nombre, cédula, tipo de órgano, tipo de sangre y edad.
     *
     * @return Listado de donantes en formato texto.
     */
    @Override
    public String listarDonantes() {
        int tamañoDeLaListaDeDonantes = this.listaDeDonantes.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("---------------------- DATOS DE DONANTES ----------------------\n");
        while (i < tamañoDeLaListaDeDonantes) {
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