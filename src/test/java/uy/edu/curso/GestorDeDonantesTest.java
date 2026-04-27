package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.services.GestorDeDonantesImpl;


@DisplayName("Tests para el Gestor de Donantes")
public class GestorDeDonantesTest {

    private static GestorDeDonantesImpl gestorDeDonantes;

    @BeforeAll
    public static void inicializacion() {
        gestorDeDonantes = new GestorDeDonantesImpl();
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests del gestor de donantes finalizados. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        gestorDeDonantes = new GestorDeDonantesImpl();
    }

    @Test
    @DisplayName("Registrar Donante")
    public void registrarDonante_datosValidos_donanteRegistradoCorrectamente() {
        // Arrange - Act
        Donante donante = gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Assert
        assertEquals("123", donante.getCedulaDeIdentidad());
        assertEquals("Valentín", donante.getNombre());
        assertEquals("Riñón", donante.getTipoDeOrgano());
        assertEquals("A+", donante.getTipoDeSangre());
        assertEquals(20, donante.getEdad());
        assertEquals(1, gestorDeDonantes.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Registrar Donante Duplicado")
    public void registrarDonante_cedulaDuplicada_devuelveNullYNoAgrega() {
        // Arrange
        gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        Donante duplicado = gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Assert
        assertNull(duplicado);
        assertEquals(1, gestorDeDonantes.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Buscar Donante")
    public void buscarDonante_cedulaExistente_devuelveDonante() {
        // Arrange
        Donante esperado = gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        Donante encontrado = gestorDeDonantes.buscarDonante("123");

        // Assert
        assertEquals(esperado, encontrado);
    }

    @Test
    @DisplayName("Buscar Donante Inexistente")
    public void buscarDonante_cedulaInexistente_devuelveNull() {
        // Act
        Donante encontrado = gestorDeDonantes.buscarDonante("999");

        // Assert
        assertNull(encontrado);
    }

    @Test
    @DisplayName("Eliminar Donante")
    public void eliminarDonante_cedulaExistente_donanteEliminado() {
        // Arrange
        gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        gestorDeDonantes.eliminarDonante("123");

        // Assert
        assertNull(gestorDeDonantes.buscarDonante("123"));
        assertEquals(0, gestorDeDonantes.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Eliminar Donante Inexistente")
    public void eliminarDonante_cedulaInexistente_listaSinCambios() {
        // Arrange
        gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        gestorDeDonantes.eliminarDonante("999");

        // Assert
        assertEquals(1, gestorDeDonantes.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Buscar Donante Luego de Eliminarlo")
    public void buscarDonante_despuesDeEliminar_devuelveNull() {
        // Arrange
        gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestorDeDonantes.eliminarDonante("123");

        // Act
        Donante resultado = gestorDeDonantes.buscarDonante("123");

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Registrar Varios Donantes")
    public void registrarDonante_variosRegistros_listaConTresElementos() {
        // Arrange - Act
        gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestorDeDonantes.registrarDonante("456", "Valentín", "Corazón", "O-", (byte) 25);
        gestorDeDonantes.registrarDonante("789", "Valentín", "Hígado", "B+", (byte) 30);

        // Assert
        assertEquals(3, gestorDeDonantes.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Listar Donantes")
    public void listarDonantes_conDatos_devuelveListadoCorrecto() {
        // Arrange
        Donante donante1 = gestorDeDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Donante donante2 = gestorDeDonantes.registrarDonante("456", "Valentín", "Corazón", "O-", (byte) 25);

        StringBuilder esperado = new StringBuilder();

        esperado.append("---------------------- DATOS DE DONANTES ----------------------\n");
        esperado.append(donante1.getNombre());
        esperado.append(", ");
        esperado.append(donante1.getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(donante1.getTipoDeOrgano());
        esperado.append(", ");
        esperado.append(donante1.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(donante1.getEdad());
        esperado.append(".");
        esperado.append("\n");

        esperado.append(donante2.getNombre());
        esperado.append(", ");
        esperado.append(donante2.getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(donante2.getTipoDeOrgano());
        esperado.append(", ");
        esperado.append(donante2.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(donante2.getEdad());
        esperado.append(".");
        esperado.append("\n");

        // Act
        String resultado = gestorDeDonantes.listarDonantes();

        // Assert
        assertEquals(esperado.toString(), resultado);
    }

    @Test
    @DisplayName("Listar Donantes Vacío")
    public void listarDonantes_sinDatos_devuelveEncabezadoSolo() {
        // Arrange
        StringBuilder esperado = new StringBuilder();

        esperado.append("---------------------- DATOS DE DONANTES ----------------------\n");

        // Act
        String resultado = gestorDeDonantes.listarDonantes();

        // Assert
        assertEquals(esperado.toString(), resultado);
    }

}