package uy.edu.curso.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.LectorDeArchivos;
import uy.edu.curso.interfaces.Receptor;


public class LectorDeArchivosCSV implements LectorDeArchivos {

    private final BioQueueFacade bioQueue;

    public LectorDeArchivosCSV(BioQueueFacade bioQueue) {
        this.bioQueue = bioQueue;
    }

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
