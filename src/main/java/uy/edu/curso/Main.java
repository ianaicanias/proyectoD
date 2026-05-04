package uy.edu.curso;

import java.util.Scanner;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.LectorDeArchivos;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.services.LectorDeArchivosCSV;
import uy.edu.curso.tda.TDAListaEnlazada;


public class Main {

    private static final BioQueueFacade BIO_QUEUE = BioQueueFacade.getInstancia();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final LectorDeArchivos LECTOR_DE_ARCHIVOS = new LectorDeArchivosCSV(BIO_QUEUE);

    public static void main(String[] args) {
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerInt();

            switch (opcion) {
                case 1:
                    registrarReceptor();
                    break;
                case 2: 
                    registrarDonante();
                    break;
                case 3:
                    registrarReceptoresPorCSV();
                    break;
                case 4:
                    registrarDonantesPorCSV();
                    break;
                case 5: 
                    buscarReceptor();
                    break;
                case 6:
                    buscarDonante();
                    break;
                case 7:
                    buscarOrganoPorIdentificador();
                    break;
                case 8:
                    buscarOrganosPorNombre();
                    break;
                case 9:
                    buscarOrganosPorTipoDeSangre();
                    break;
                case 10:
                    buscarTrasplante();
                    break;
                case 11:
                    eliminarReceptor();
                    break;
                case 12: 
                    eliminarDonante();
                    break;
                case 13:
                    eliminarOrgano();
                    break;
                case 14:
                    System.out.println(BIO_QUEUE.listarReceptores());
                    break;
                case 15: 
                    System.out.println(BIO_QUEUE.listarDonantes());
                    break;
                case 16: 
                    System.out.println(BIO_QUEUE.listarOrganosDisponibles());
                    break;
                case 17: 
                    System.out.println(BIO_QUEUE.listarTrasplantesRealizados());
                    break;
                case 0: 
                    System.out.println("Gracias por utilizar BioQueue, hasta luego. ;D");
                    System.out.println("Saliendo del sistema. . .");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente. . .");
                    break;
            }
        }

        SCANNER.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n==================== Sistema BioQueue ====================");
        System.out.println("1. Registrar Receptor");
        System.out.println("2. Registrar Donante");
        System.out.println("3. Registrar Receptores con Archivo CSV");
        System.out.println("4. Registrar Donantes con Archivo CSV");
        System.out.println("5. Buscar Receptor");
        System.out.println("6. Buscar Donante");
        System.out.println("7. Buscar Órgano por Identificador");
        System.out.println("8. Buscar Órganos por Nombre");
        System.out.println("9. Buscar Órganos por Tipo de Sangre");
        System.out.println("10. Buscar Trasplante");
        System.out.println("11. Eliminar Receptor");
        System.out.println("12. Eliminar Donante");
        System.out.println("13. Eliminar Órgano");
        System.out.println("14. Listar Receptores");
        System.out.println("15. Listar Donantes");
        System.out.println("16. Listar Órganos Disponibles");
        System.out.println("17. Listar Trasplantes Realizados");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static void registrarReceptor() {
        System.out.print("Cédula de identidad: ");
        String cedula = SCANNER.nextLine();
        System.out.print("Nombre: ");
        String nombre = SCANNER.nextLine();
        System.out.print("Tipo de órgano necesitado: ");
        String tipoOrgano = SCANNER.nextLine();
        System.out.print("Tipo de sangre: ");
        String tipoDeSangre = SCANNER.nextLine();
        System.out.print("Edad: ");
        byte edad = Byte.parseByte(SCANNER.nextLine());
        System.out.print("Puntaje de prioridad: ");
        byte puntaje = Byte.parseByte(SCANNER.nextLine());

        Receptor receptor = BIO_QUEUE.registrarReceptor(cedula, nombre, tipoOrgano, tipoDeSangre, edad, puntaje);

        if (receptor != null) {
            BIO_QUEUE.insertarReceptorEnLaListaDePrioridad(receptor);
            System.out.println("Receptor registrado correctamente.");
        } else {
            System.out.println("Ya existe un receptor con la cédula a registrar.");
        }
    }

    private static void registrarDonante() {
        int cantidadDeTrasplantesAntes = BIO_QUEUE.getCantidadDeTrasplantes();
        int cantidadDeTrasplantesDespues;

        System.out.print("Cédula de identidad: ");
        String cedula = SCANNER.nextLine();
        System.out.print("Nombre: ");
        String nombre = SCANNER.nextLine();
        System.out.print("Tipo de órgano donado: ");
        String tipoOrgano = SCANNER.nextLine();
        System.out.print("Tipo de sangre: ");
        String tipoDeSangre = SCANNER.nextLine();
        System.out.print("Edad: ");
        byte edad = Byte.parseByte(SCANNER.nextLine());

        Donante donante = BIO_QUEUE.registrarDonante(cedula, nombre, tipoOrgano, tipoDeSangre, edad);
        
        if (donante != null) {
            System.out.println("Donante registrado correctamente.");
            cantidadDeTrasplantesDespues = BIO_QUEUE.getCantidadDeTrasplantes();

            if (cantidadDeTrasplantesDespues > cantidadDeTrasplantesAntes) {
                System.out.println("¡Se asignó un trasplante automáticamente al receptor de más prioridad!");
            } else {
                System.out.println("No se pudo encontrar un receptor compatible por el momento.");
            }
        } else {
            System.out.println("Ya existe un donante con la cédula a registrar.");
        }
    }

    private static void registrarReceptoresPorCSV() {
        System.out.print("Ingrese la ruta al archivo con el registro de nuevos receptores (CSV): ");
        String rutaDelArchivoDeReceptores = SCANNER.nextLine();

        LECTOR_DE_ARCHIVOS.cargarReceptores(rutaDelArchivoDeReceptores);
    }

    private static void registrarDonantesPorCSV() {
        System.out.print("Ingrese la ruta al archivo con el registro de nuevos donantes (CSV): ");
        String rutaDelArchivoDeDonantes = SCANNER.nextLine();

        LECTOR_DE_ARCHIVOS.cargarDonantes(rutaDelArchivoDeDonantes);
    }

    private static void buscarReceptor() {
        System.out.print("Cédula de identidad del receptor: ");
        String cedula = SCANNER.nextLine();

        Receptor receptor = BIO_QUEUE.buscarReceptor(cedula);

        if (receptor != null) {
            System.out.println("Receptor encontrado: " + receptor.getNombre());
        } else {
            System.out.println("Receptor no encontrado.");
        }
    }

    private static void buscarDonante() {
        System.out.print("Cédula de identidad del donante: ");
        String cedula = SCANNER.nextLine();

        Donante donante = BIO_QUEUE.buscarDonante(cedula);

        if (donante != null) {
            System.out.println("Donante encontrado: " + donante.getNombre());
        } else {
            System.out.println("Donante no encontrado.");
        }
    }

    private static void buscarOrganoPorIdentificador() {
        System.out.print("Identificador del órgano: ");
        long id = Long.parseLong(SCANNER.nextLine());

        Organo organo = BIO_QUEUE.buscarOrganoPorIdentificador(id);

        if (organo != null) {
            System.out.println("Órgano encontrado: " + organo.getNombre());
        } else {
            System.out.println("Órgano no encontrado.");
        }
    }

    private static void buscarOrganosPorNombre() {
        System.out.print("Nombre del órgano: ");
        String nombreDelOrgano = SCANNER.nextLine();

        TDAListaEnlazada<Organo> listaDeOrganosConElNombreBuscado = BIO_QUEUE.buscarOrganosPorNombre(nombreDelOrgano);

        if (listaDeOrganosConElNombreBuscado != null) {
            int tamanioDeLaListaDeOrganosObtenida = listaDeOrganosConElNombreBuscado.tamaño();

            System.out.println("----------- Lista de Órganos Encontrados -----------");
            for (int i = 0; i < tamanioDeLaListaDeOrganosObtenida; i++) {
                System.out.println("Órgano: " + listaDeOrganosConElNombreBuscado.obtener(i).getNombre());
            }
        } else {
            System.out.println("No existen órganos con el nombre específicado.");
        }
    }

    private static void buscarOrganosPorTipoDeSangre() {
        System.out.print("Tipo de sangre del órgano: ");
        String tipoDeSangreDelOrgano = SCANNER.nextLine();

        TDAListaEnlazada<Organo> listaDeOrganosConElTipoDeSangreBuscado = BIO_QUEUE.buscarOrganosPorTipoDeSangre(tipoDeSangreDelOrgano);

        if (listaDeOrganosConElTipoDeSangreBuscado != null) {
            int tamanioDeLaListaDeOrganosObtenida = listaDeOrganosConElTipoDeSangreBuscado.tamaño();

            System.out.println("----------- Lista de Órganos Encontrados -----------");
            for (int i = 0; i < tamanioDeLaListaDeOrganosObtenida; i++) {
                System.out.println("Órgano: " + listaDeOrganosConElTipoDeSangreBuscado.obtener(i).getNombre());
            }
        } else {
            System.out.println("No existen órganos con el tipo de sangre específicado.");
        }
    }

    private static void buscarTrasplante() {
        System.out.print("Identificador del trasplante: ");
        long id = Long.parseLong(SCANNER.nextLine());

        Trasplante trasplante = BIO_QUEUE.buscarTrasplante(id);

        if (trasplante != null) {
            System.out.println(trasplante.toString());
        } else {
            System.out.println("Trasplante no encontrado.");
        }
    }

    private static void eliminarReceptor() {
        System.out.print("Cédula de identidad del receptor a eliminar: ");
        String cedula = SCANNER.nextLine();

        BIO_QUEUE.eliminarReceptor(cedula);
        System.out.println("Receptor eliminado correctamente.");
    }

    private static void eliminarDonante() {
        System.out.print("Cédula de identidad del donante a eliminar: ");
        String cedula = SCANNER.nextLine();

        BIO_QUEUE.eliminarDonante(cedula);
        System.out.println("Donante eliminado correctamente.");
    }

    private static void eliminarOrgano() {
        System.out.print("Identificador del órgano a eliminar: ");
        long id = Long.parseLong(SCANNER.nextLine());

        BIO_QUEUE.eliminarOrgano(id);
        System.out.println("Órgano eliminado correctamente.");
    }

    private static int leerInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}