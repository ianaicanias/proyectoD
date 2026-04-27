package uy.edu.curso;

import java.util.Scanner;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.tda.TDAListaEnlazada;


public class Main {

    private static final BioQueueFacade bioQueue = BioQueueFacade.getInstancia();
    private static final Scanner scanner = new Scanner(System.in);

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
                    buscarReceptor();
                    break;
                case 4:
                    buscarDonante();
                    break;
                case 5:
                    buscarOrganoPorIdentificador();
                    break;
                case 6:
                    buscarOrganosPorNombre();
                    break;
                case 7:
                    buscarOrganosPorTipoDeSangre();
                    break;
                case 8:
                    buscarTrasplante();
                    break;
                case 9:
                    eliminarReceptor();
                    break;
                case 10: 
                    eliminarDonante();
                    break;
                case 11:
                    eliminarOrgano();
                    break;
                case 12:
                    System.out.println(bioQueue.listarReceptores());
                    break;
                case 13: 
                    System.out.println(bioQueue.listarDonantes());
                    break;
                case 14: 
                    System.out.println(bioQueue.listarOrganosDisponibles());
                    break;
                case 15: 
                    System.out.println(bioQueue.listarTrasplantesRealizados());
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

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n==================== Sistema BioQueue ====================");
        System.out.println("1. Registrar Receptor");
        System.out.println("2. Registrar Donante");
        System.out.println("3. Buscar Receptor");
        System.out.println("4. Buscar Donante");
        System.out.println("5. Buscar Órgano por Identificador");
        System.out.println("6. Buscar Órganos por Nombre");
        System.out.println("7. Buscar Órganos por Tipo de Sangre");
        System.out.println("8. Buscar Trasplante");
        System.out.println("9. Eliminar Receptor");
        System.out.println("10. Eliminar Donante");
        System.out.println("11. Eliminar Órgano");
        System.out.println("12. Listar Receptores");
        System.out.println("13. Listar Donantes");
        System.out.println("14. Listar Órganos Disponibles");
        System.out.println("15. Listar Trasplantes Realizados");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }

    private static void registrarReceptor() {
        System.out.print("Cédula de identidad: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo de órgano necesitado: ");
        String tipoOrgano = scanner.nextLine();
        System.out.print("Tipo de sangre: ");
        String tipoDeSangre = scanner.nextLine();
        System.out.print("Edad: ");
        byte edad = Byte.parseByte(scanner.nextLine());
        System.out.print("Puntaje de prioridad: ");
        byte puntaje = Byte.parseByte(scanner.nextLine());

        Receptor receptor = bioQueue.registrarReceptor(cedula, nombre, tipoOrgano, tipoDeSangre, edad, puntaje);

        bioQueue.insertarReceptorEnLaListaDePrioridad(receptor);
        System.out.println("Receptor registrado correctamente.");
    }

    private static void registrarDonante() {
        System.out.print("Cédula de identidad: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Tipo de órgano donado: ");
        String tipoOrgano = scanner.nextLine();
        System.out.print("Tipo de sangre: ");
        String tipoDeSangre = scanner.nextLine();
        System.out.print("Edad: ");
        byte edad = Byte.parseByte(scanner.nextLine());

        bioQueue.registrarDonante(cedula, nombre, tipoOrgano, tipoDeSangre, edad);
        System.out.println("Donante registrado correctamente.");
    }

    private static void buscarReceptor() {
        System.out.print("Cédula de identidad del receptor: ");
        String cedula = scanner.nextLine();

        Receptor receptor = bioQueue.buscarReceptor(cedula);

        if (receptor != null) {
            System.out.println("Receptor encontrado: " + receptor.getNombre());
        } else {
            System.out.println("Receptor no encontrado.");
        }
    }

    private static void buscarDonante() {
        System.out.print("Cédula de identidad del donante: ");
        String cedula = scanner.nextLine();

        Donante donante = bioQueue.buscarDonante(cedula);

        if (donante != null) {
            System.out.println("Donante encontrado: " + donante.getNombre());
        } else {
            System.out.println("Donante no encontrado.");
        }
    }

    private static void buscarOrganoPorIdentificador() {
        System.out.print("Identificador del órgano: ");
        long id = Long.parseLong(scanner.nextLine());

        Organo organo = bioQueue.buscarOrganoPorIdentificador(id);

        if (organo != null) {
            System.out.println("Órgano encontrado: " + organo.getNombre());
        } else {
            System.out.println("Órgano no encontrado.");
        }
    }

    private static void buscarOrganosPorNombre() {
        System.out.print("Nombre del órgano: ");
        String nombreDelOrgano = scanner.nextLine();

        TDAListaEnlazada<Organo> listaDeOrganosConElNombreBuscado = bioQueue.buscarOrganosPorNombre(nombreDelOrgano);

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
        String tipoDeSangreDelOrgano = scanner.nextLine();

        TDAListaEnlazada<Organo> listaDeOrganosConElTipoDeSangreBuscado = bioQueue.buscarOrganosPorTipoDeSangre(tipoDeSangreDelOrgano);

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
        long id = Long.parseLong(scanner.nextLine());

        Trasplante trasplante = bioQueue.buscarTrasplante(id);

        if (trasplante != null) {
            System.out.println(trasplante.toString());
        } else {
            System.out.println("Trasplante no encontrado.");
        }
    }

    private static void eliminarReceptor() {
        System.out.print("Cédula de identidad del receptor a eliminar: ");
        String cedula = scanner.nextLine();

        bioQueue.eliminarReceptor(cedula);
        System.out.println("Receptor eliminado correctamente.");
    }

    private static void eliminarDonante() {
        System.out.print("Cédula de identidad del donante a eliminar: ");
        String cedula = scanner.nextLine();

        bioQueue.eliminarDonante(cedula);
        System.out.println("Donante eliminado correctamente.");
    }

    private static void eliminarOrgano() {
        System.out.print("Identificador del órgano a eliminar: ");
        long id = Long.parseLong(scanner.nextLine());

        bioQueue.eliminarOrgano(id);
        System.out.println("Órgano eliminado correctamente.");
    }

    private static int leerInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}