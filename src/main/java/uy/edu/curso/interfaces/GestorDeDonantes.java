/*
 * Interfaz: GestorDeDonantes.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define las operaciones de gestión de donantes dentro
 * del sistema BioQueue. Permite registrar, buscar, eliminar y listar donantes,
 * así como acceder a la lista que los almacena.
 */
package uy.edu.curso.interfaces;

import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Interfaz que define el comportamiento de un gestor de donantes.
 * Proporciona métodos para la administración de donantes dentro del sistema,
 * incluyendo su registro, búsqueda, eliminación y listado.
 */
public interface GestorDeDonantes {

    /**
     * Retorna la lista de donantes registrados en el sistema.
     *
     * @return Lista enlazada con todos los donantes.
     */
    TDAListaEnlazada<Donante> getListaDeDonantes();

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
    Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, 
            String tipoDeSangre, byte edad);
    
    /**
     * Busca un donante en el sistema por su cédula de identidad.
     *
     * @param cedulaDeIdentidad Cédula de identidad del donante a buscar.
     * @return El donante encontrado, o {@code null} si no existe.
     */
    Donante buscarDonante(String cedulaDeIdentidad);

    /**
     * Elimina un donante del sistema por su cédula de identidad.
     * Si no existe un donante con esa cédula, no realiza ninguna acción.
     *
     * @param cedulaDeIdentidad Cédula de identidad del donante a eliminar.
     */
    void eliminarDonante(String cedulaDeIdentidad);

    /**
     * Retorna una cadena con el listado de todos los donantes registrados,
     * incluyendo nombre, cédula, tipo de órgano, tipo de sangre y edad.
     *
     * @return Listado de donantes en formato texto.
     */
    String listarDonantes();

}