package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.interfaces.GestorDeOrganos;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.GestorDeTrasplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.services.GestorDeDonantesImpl;
import uy.edu.curso.services.GestorDeOrganosImpl;
import uy.edu.curso.services.GestorDeReceptoresImpl;
import uy.edu.curso.services.GestorDeTrasplantesImpl;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Tests para el Gestor de Trasplantes")
public class GestorDeTrasplantesTest {

    private static GestorDeTrasplantes gestorDeTrasplantes;
    private static GestorDeDonantes gestorDeDonantes;
    private static GestorDeOrganos gestorDeOrganos;
    private static GestorDeReceptores gestorDeReceptores;

    @BeforeAll
    public static void inicializacion() {
        gestorDeTrasplantes = new GestorDeTrasplantesImpl();
        gestorDeDonantes = new GestorDeDonantesImpl();
        gestorDeOrganos = new GestorDeOrganosImpl();
        gestorDeReceptores = new GestorDeReceptoresImpl();
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests del gestor de trasplantes finalizados. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        gestorDeTrasplantes = new GestorDeTrasplantesImpl();
        gestorDeDonantes = new GestorDeDonantesImpl();
        gestorDeOrganos = new GestorDeOrganosImpl();
        gestorDeReceptores = new GestorDeReceptoresImpl();
    }

    @Test
    @Order(1)
    @DisplayName("Creación de un Gestor de Trasplantes Correctamente")
    public void GestorDeTrasplantesImpl_llamadaAlConstructor_gestorDeTrasplantesCreadoCorrectamenteConSuLista() {
        // Act
        GestorDeTrasplantes gestorDeTrasplantesNuevo = new GestorDeTrasplantesImpl();

        // Assert
        assertNotNull(gestorDeTrasplantesNuevo.getListaDeTrasplantesRealizados());
    }

    @Test
    @Order(2)
    @DisplayName("Asignar Órgano Correctamente a un Receptor")
    public void asignarOrganoAReceptor_receptorYOrganosCompatibles_seAsignaCorrectamenteElOrganoAlReceptorEliminandolosDeLaLista() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Riñón", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "A+", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);

        // Act
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());
        Trasplante trasplanteEncontrado = null;

        for (int i = 0; i <= 10; i++) {
            Trasplante trasplanteObtenido = gestorDeTrasplantes.buscarTrasplante(i);

            if (trasplanteObtenido != null && trasplanteObtenido.getDonanteDelOrganoDelTrasplante().equals(nuevoDonante)) {
                trasplanteEncontrado = trasplanteObtenido;
            }
        }
        StringBuilder trasplanteGeneradoEsperado = new StringBuilder();

        assertNotNull(trasplanteEncontrado); 

        trasplanteGeneradoEsperado.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");
        trasplanteGeneradoEsperado.append("Trasplante realizado, con ID: ");
        trasplanteGeneradoEsperado.append(trasplanteEncontrado.getIdentificador());
        trasplanteGeneradoEsperado.append(".\n");
        trasplanteGeneradoEsperado.append("Órgano: Riñón.\n");
        trasplanteGeneradoEsperado.append("De parte del donante: Rodrigo Lopez.\n");
        trasplanteGeneradoEsperado.append("Para el receptor: Leonardo Perez.");
        trasplanteGeneradoEsperado.append("\n--------------------------------------------\n");

        // Assert
        assertFalse(gestorDeReceptores.getListaDeReceptores().contiene(nuevoReceptor));
        assertFalse(gestorDeReceptores.getListaDePrioridadDeReceptores().contiene(nuevoReceptor));
        assertFalse(gestorDeOrganos.getListaDeOrganosDisponibles().contiene(nuevoOrgano));
        assertEquals(trasplanteGeneradoEsperado.toString(), gestorDeTrasplantes.listarTrasplantesRealizados());
    }

    @Test
    @Order(3)
    @DisplayName("Buscar Trasplante Realizado")
    public void buscarTrasplante_trasplanteExistente_devuelveElTrasplanteSolicitado() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Riñón", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "A+", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Act
        Trasplante trasplanteEncontrado = null;

        for (int i = 0; i <= 10; i++) {
            Trasplante trasplanteObtenido = gestorDeTrasplantes.buscarTrasplante(i);

            if (trasplanteObtenido != null && trasplanteObtenido.getDonanteDelOrganoDelTrasplante().equals(nuevoDonante)) {
                trasplanteEncontrado = trasplanteObtenido;
            }
        }
        
        // Assert
        assertNotNull(trasplanteEncontrado);
        assertEquals(nuevoReceptor, trasplanteEncontrado.getReceptorDelTrasplante());
        assertEquals(nuevoDonante, trasplanteEncontrado.getDonanteDelOrganoDelTrasplante());
        assertEquals(nuevoOrgano, trasplanteEncontrado.getOrganoTrasplantado());
    }

    @Test
    @Order(4)
    @DisplayName("Buscar Trasplante Inexistente")
    public void buscarTrasplante_trasplanteInexistente_devuelveNullDebidoAQueNoSeEncontroElTrasplanteRequerido() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("43223432", "Marcelo Perez", 
                "Riñón", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("78675439", "Rodrigo Tejeira", 
                "Riñón", "A+", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Act
        Trasplante trasplanteRealizado = gestorDeTrasplantes.buscarTrasplante(497589734);

        // Assert
        assertNull(trasplanteRealizado);
    }

    @Test
    @Order(5)
    @DisplayName("Asignar Órgano Incompatible a un Receptor")
    public void asignarOrganoAReceptor_receptorYOrganosImcompatibles_noSeAsignaElOrganoAlReceptor() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Riñón", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "AB-", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        StringBuilder trasplanteGeneradoEsperado = new StringBuilder();

        trasplanteGeneradoEsperado.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");

        // Act
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Assert
        assertTrue(gestorDeReceptores.getListaDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeReceptores.getListaDePrioridadDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeOrganos.getListaDeOrganosDisponibles().contiene(nuevoOrgano));
        assertEquals(trasplanteGeneradoEsperado.toString(), gestorDeTrasplantes.listarTrasplantesRealizados());
    }

    @Test
    @Order(6)
    @DisplayName("Asignar Órgano Cuando no Hay Receptores Disponibles")
    public void asignarOrganoAReceptor_colaDePrioridadDeReceptoresVacia_noSeAsignaElOrgano() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Riñón", "A+", (byte) 29, (byte) 7);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "AB-", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        StringBuilder trasplanteGeneradoEsperado = new StringBuilder();

        trasplanteGeneradoEsperado.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");

        // Act
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Assert
        assertTrue(gestorDeReceptores.getListaDeReceptores().contiene(nuevoReceptor));
        assertEquals(0, gestorDeReceptores.getListaDePrioridadDeReceptores().tamaño());
        assertTrue(gestorDeOrganos.getListaDeOrganosDisponibles().contiene(nuevoOrgano));
        assertEquals(trasplanteGeneradoEsperado.toString(), gestorDeTrasplantes.listarTrasplantesRealizados());
        assertEquals(0, gestorDeTrasplantes.getListaDeTrasplantesRealizados().tamaño());
    }

    @Test
    @Order(7)
    @DisplayName("Asignar un Órgano que no Corresponde a un Receptor")
    public void asignarOrganoAReceptor_organoYOrganoDelReceptorNoCoinciden_noSeAsignaElOrganoAlReceptor() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Páncreas", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "A+", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        StringBuilder trasplanteGeneradoEsperado = new StringBuilder();

        trasplanteGeneradoEsperado.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");

        // Act
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Assert
        assertTrue(gestorDeReceptores.getListaDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeReceptores.getListaDePrioridadDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeOrganos.getListaDeOrganosDisponibles().contiene(nuevoOrgano));
        assertEquals(trasplanteGeneradoEsperado.toString(), gestorDeTrasplantes.listarTrasplantesRealizados());
        assertEquals(0, gestorDeTrasplantes.getListaDeTrasplantesRealizados().tamaño());
    }

    @Test
    @Order(8)
    @DisplayName("Asignar Órgano Incompatible y no Coincidente a un Receptor")
    public void asignarOrganoAReceptor_receptorYOrganosImcompatiblesConOrganosNoCoincidentes_noSeAsignaElOrganoAlReceptor() {
        // Arrange
        Receptor nuevoReceptor = gestorDeReceptores.registrarReceptor("62362723", "Leonardo Perez", 
                "Páncreas", "A+", (byte) 29, (byte) 7);
        gestorDeReceptores.insertarReceptorEnLaListaDePrioridad(nuevoReceptor);
        Donante nuevoDonante = gestorDeDonantes.registrarDonante("84588393", "Rodrigo Lopez", 
                "Riñón", "AB-", (byte) 32);
        Organo nuevoOrgano = gestorDeOrganos.registrarOrgano("Riñón", nuevoDonante);
        StringBuilder trasplanteGeneradoEsperado = new StringBuilder();

        trasplanteGeneradoEsperado.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");

        // Act
        gestorDeTrasplantes.asignarOrganoAReceptor(nuevoOrgano, gestorDeReceptores.getListaDeReceptores(), 
                gestorDeReceptores.getListaDePrioridadDeReceptores(), gestorDeOrganos.getListaDeOrganosDisponibles());

        // Assert
        assertTrue(gestorDeReceptores.getListaDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeReceptores.getListaDePrioridadDeReceptores().contiene(nuevoReceptor));
        assertTrue(gestorDeOrganos.getListaDeOrganosDisponibles().contiene(nuevoOrgano));
        assertEquals(trasplanteGeneradoEsperado.toString(), gestorDeTrasplantes.listarTrasplantesRealizados());
        assertEquals(0, gestorDeTrasplantes.getListaDeTrasplantesRealizados().tamaño());
    }

}