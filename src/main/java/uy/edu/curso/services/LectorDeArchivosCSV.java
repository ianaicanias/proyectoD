/*
 * Clase: LectorDeArchivosCSV.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta del lector de archivos
 * en el sistema BioQueue. Implementa la interfaz LectorDeArchivos y permite la
 * carga automática de receptores y donantes desde archivos CSV, delegando el
 * registro de cada entidad a la fachada del sistema.
 */
package uy.edu.curso.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.LectorDeArchivos;
import uy.edu.curso.interfaces.Receptor;


/**
 * Implementación concreta del lector de archivos CSV del sistema BioQueue.
 * Implementa @see LectorDeArchivos y permite la carga masiva de receptores
 * y donantes desde archivos CSV, registrándolos en el sistema a través de
 * la fachada. 
 * 
 * El formato esperado del CSV de receptores es:
 * cedulaDeIdentidad, nombre, tipoDeOrgano, tipoDeSangre, edad, puntajeDePrioridad.
 * 
 * El formato esperado del CSV de donantes es:
 * cedulaDeIdentidad, nombre, tipoDeOrgano, tipoDeSangre, edad.
 */
public class LectorDeArchivosCSV implements LectorDeArchivos {

    /**
     * Fachada del sistema utilizada para registrar los receptores y donantes
     * leídos desde los archivos CSV.
     */
    private final BioQueueFacade bioQueue;

    /**
     * Constructor de la clase LectorDeArchivosCSV.
     *
     * @param bioQueue Fachada del sistema BioQueue a utilizar para el registro.
     */
    public LectorDeArchivosCSV(BioQueueFacade bioQueue) {
        this.bioQueue = bioQueue;
    }

    /**
     * Carga receptores desde un archivo CSV e intenta registrar cada uno en el sistema.
     * Si un receptor ya existe (cédula duplicada), se informa por consola y se omite.
     * Si el archivo no se encuentra, se informa por consola y no se realiza ninguna acción.
     * El scanner se cierra siempre en el bloque finally.
     *
     * @param rutaDelArchivoDeReceptores Ruta del archivo CSV con los datos de los receptores.
     */
    @Override
    public void cargarReceptores(String rutaDelArchivoDeReceptores) {
        Scanner scanner = null;

        try {
            File archivoCSV = new File(rutaDelArchivoDeReceptores);
            scanner = new Scanner(archivoCSV);

            while (scanner.hasNextLine()) {
                String lineaDeDatosDelNuevoReceptor = scanner.nextLine();
                String[] datosDelNuevoReceptor = lineaDeDatosDelNuevoReceptor.split(",");

                String cedulaDeIdentidad = datosDelNuevoReceptor[0];  
                String nombre = datosDelNuevoReceptor[1];
                String tipoDeOrganoNecesitado = datosDelNuevoReceptor[2];
                String tipoDeSangre = datosDelNuevoReceptor[3]; 
                byte edad = Byte.parseByte(datosDelNuevoReceptor[4]); 
                byte puntajeDePrioridad = Byte.parseByte(datosDelNuevoReceptor[5]);
                Receptor receptorRegistrado = this.bioQueue.registrarReceptor(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado, 
                        tipoDeSangre, edad, puntajeDePrioridad);

                if (receptorRegistrado != null) {
                    this.bioQueue.insertarReceptorEnLaListaDePrioridad(receptorRegistrado);
                    System.out.println("El receptor de nombre: "+ nombre + " con cédula: " + cedulaDeIdentidad + " fue registrado correctamente.");
                } else {
                    System.out.println("El receptor de nombre: "+ nombre + " con cédula: " + cedulaDeIdentidad + " no pudo ser registrado.");
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No se pudo encontrar el archivo solicitado. . .");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Carga donantes desde un archivo CSV e intenta registrar cada uno en el sistema.
     * Si un donante ya existe (cédula duplicada), se informa por consola y se omite.
     * Si el archivo no se encuentra, se informa por consola y no se realiza ninguna acción.
     * El scanner se cierra siempre en el bloque finally.
     *
     * @param rutaDelArchivoDeDonantes Ruta del archivo CSV con los datos de los donantes.
     */
    @Override
    public void cargarDonantes(String rutaDelArchivoDeDonantes) {
        Scanner scanner = null;

        try {
            File archivoCSV = new File(rutaDelArchivoDeDonantes);
            scanner = new Scanner(archivoCSV);

            while (scanner.hasNextLine()) {
                String lineaDeDatosDelNuevoDonante = scanner.nextLine();
                String[] datosDelNuevoDonante = lineaDeDatosDelNuevoDonante.split(",");

                String cedulaDeIdentidad = datosDelNuevoDonante[0];  
                String nombre = datosDelNuevoDonante[1];
                String tipoDeOrganoDonado = datosDelNuevoDonante[2];
                String tipoDeSangre = datosDelNuevoDonante[3]; 
                byte edad = Byte.parseByte(datosDelNuevoDonante[4]);
                Donante donanteRegistrado = this.bioQueue.registrarDonante(cedulaDeIdentidad, nombre, 
                        tipoDeOrganoDonado, tipoDeSangre, edad);

                if (donanteRegistrado != null) {
                    System.out.println("El donante de nombre: "+ nombre + " con cédula: " + cedulaDeIdentidad + " fue registrado correctamente.");
                } else {
                    System.out.println("El donante de nombre: "+ nombre + " con cédula: " + cedulaDeIdentidad + " no pudo ser registrado.");
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No se pudo encontrar el archivo solicitado. . .");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}