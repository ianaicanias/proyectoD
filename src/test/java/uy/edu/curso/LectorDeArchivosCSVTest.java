package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.services.LectorDeArchivosCSV;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Tests para el Lector de Archivos CSV")
public class LectorDeArchivosCSVTest {

    private static BioQueueFacade facade; 
    private static LectorDeArchivosCSV lectorDeArchivosCSV;

    @BeforeAll
    public static void inicializacion() {
        facade = BioQueueFacade.getInstancia();
        lectorDeArchivosCSV = new LectorDeArchivosCSV(facade);
        System.out.println("Inicializando los tests. . .");
    }
    
    @AfterAll
    public static void finalizacion() {
        BioQueueFacade.resetearInstancia();
        System.out.println("Tests del lector de archivos CSV finalizados. . .");
    }

    @Test
    @Order(1)
    @DisplayName("Carga de Receptores con Archivo CSV Correcto")
    public void cargarReceptores_archivoCSVCorrecto_registraCorrectamenteTodosLosReceptoresIngresados() {
        // Arrange
        int cantidadDeRegistrosDeReceptores = 100;
        String rutaDelArchivoCSV = "src/test/resources/receptores.csv";

        // Act
        lectorDeArchivosCSV.cargarReceptores(rutaDelArchivoCSV);

        // Assert
        assertEquals(cantidadDeRegistrosDeReceptores, facade.getCantidadDeReceptores());
    }

    @Test
    @Order(2)
    @DisplayName("Carga de Donantes con Archivo CSV Correcto")
    public void cargarDonantes_archivoCSVCorrecto_registraCorrectamenteTodosLosReceptoresIngresados() {
        // Arrange
        int cantidadDeRegistrosDeDonantes = 100;
        String rutaDelArchivoCSV = "src/test/resources/donantes.csv";

        // Act
        lectorDeArchivosCSV.cargarDonantes(rutaDelArchivoCSV);

        // Assert
        assertEquals(cantidadDeRegistrosDeDonantes, facade.getCantidadDeDonantes());
    }

}