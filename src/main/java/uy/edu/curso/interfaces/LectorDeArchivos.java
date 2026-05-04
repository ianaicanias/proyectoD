/*
 * Interfaz: LectorDeArchivos.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente interfaz define las operaciones de carga de datos desde archivos
 * externos hacia el sistema BioQueue. Permite importar información de receptores
 * y donantes, facilitando la inicialización o actualización de los datos del sistema.
 */
package uy.edu.curso.interfaces;


/**
 * Interfaz que define el comportamiento de un lector de archivos.
 * Proporciona métodos para cargar información desde archivos externos,
 * integrando los datos en las estructuras internas del sistema.
 */
public interface LectorDeArchivos {

    /**
     * Carga receptores desde un archivo CSV e intenta registrar cada uno en el sistema.
     * Si un receptor ya existe (cédula duplicada), se informa por consola y se omite.
     * Si el archivo no se encuentra, se informa por consola y no se realiza ninguna acción.
     * El scanner se cierra siempre en el bloque finally.
     *
     * @param rutaDelArchivoDeReceptores Ruta del archivo CSV con los datos de los receptores.
     */
    void cargarReceptores(String rutaDelArchivoDeReceptores);
    
    /**
     * Carga donantes desde un archivo CSV e intenta registrar cada uno en el sistema.
     * Si un donante ya existe (cédula duplicada), se informa por consola y se omite.
     * Si el archivo no se encuentra, se informa por consola y no se realiza ninguna acción.
     * El scanner se cierra siempre en el bloque finally.
     *
     * @param rutaDelArchivoDeDonantes Ruta del archivo CSV con los datos de los donantes.
     */
    void cargarDonantes(String rutaDelArchivoDeDonantes);

}