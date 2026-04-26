package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.services.GestorDeDonantesImpl;
import uy.edu.curso.services.GestorDeOrganosImpl;
import uy.edu.curso.tda.TDAListaEnlazada;


@DisplayName("Tests para el Gestor de Órganos")
public class GestorDeOrganosTest {

    private static GestorDeOrganosImpl gestorDeOrganos;
    private static GestorDeDonantesImpl gestorDonantes;

    @BeforeAll
    public static void inicializacion() {
        gestorDeOrganos = new GestorDeOrganosImpl();
        gestorDonantes = new GestorDeDonantesImpl();
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests del gestor de órganos finalizados. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        gestorDeOrganos = new GestorDeOrganosImpl();
        gestorDonantes = new GestorDeDonantesImpl();
    }

    @Test
    @DisplayName("Registrar Órgano")
    public void registrarOrgano_datosValidos_organoRegistradoCorrectamente() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        Organo organo = gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Assert
        assertEquals("Riñón", organo.getNombre());
        assertEquals(donante.getCedulaDeIdentidad(), organo.getDonanteDelOrgano().getCedulaDeIdentidad());
    }

    @Test
    @DisplayName("Registrar Varios Órganos")
    public void registrarOrgano_variosRegistros_listaConDosElementos() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        // Act
        gestorDeOrganos.registrarOrgano("Riñón", donante);
        gestorDeOrganos.registrarOrgano("Corazón", donante);

        // Assert
        assertEquals(2, gestorDeOrganos.getListaDeOrganosDisponibles().tamaño());
    }

    @Test
    @DisplayName("Buscar Órgano por ID")
    public void buscarOrganoPorIdentificador_idExistente_devuelveOrgano() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo organo = gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        Organo encontrado = gestorDeOrganos.buscarOrganoPorIdentificador(organo.getIdentificador());

        // Assert
        assertEquals(organo, encontrado);
    }

    @Test
    @DisplayName("Buscar Órgano Inexistente")
    public void buscarOrganoPorIdentificador_idInexistente_devuelveNull() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);
        gestorDeOrganos.registrarOrgano("Corazón", donante);

        // Act
        Organo resultado = gestorDeOrganos.buscarOrganoPorIdentificador(999);

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Buscar por Nombre")
    public void buscarOrganosPorNombre_nombreExistente_unResultado() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorNombre("Riñón");

        // Assert
        assertEquals(1, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Nombre con Dos Resultados")
    public void buscarOrganosPorNombre_nombreExistente_dosResultados() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);
        gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorNombre("Riñón");

        // Assert
        assertEquals(2, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Nombre Inexistente")
    public void buscarOrganosPorNombre_nombreInexistente_listaVacia() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorNombre("Pulmón");

        // Assert
        assertEquals(0, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre")
    public void buscarOrganosPorTipoDeSangre_tipoExistente_unResultado() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorTipoDeSangre("A+");

        // Assert
        assertEquals(1, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre con Dos Resultados")
    public void buscarOrganosPorTipoDeSangre_tipoExistente_dosResultados() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);
        gestorDeOrganos.registrarOrgano("Corazón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorTipoDeSangre("A+");

        // Assert
        assertEquals(2, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre Inexistente")
    public void buscarOrganosPorTipoDeSangre_tipoInexistente_listaVacia() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        TDAListaEnlazada<Organo> lista = gestorDeOrganos.buscarOrganosPorTipoDeSangre("O-");

        // Assert
        assertEquals(0, lista.tamaño());
    }

    @Test
    @DisplayName("Eliminar Órgano")
    public void eliminarOrgano_idExistente_organoEliminado() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo organo = gestorDeOrganos.registrarOrgano("Riñón", donante);

        // Act
        gestorDeOrganos.eliminarOrgano(organo.getIdentificador());

        // Assert
        assertNull(gestorDeOrganos.buscarOrganoPorIdentificador(organo.getIdentificador()));
    }

    @Test
    @DisplayName("Eliminar Órgano Inexistente")
    public void eliminarOrgano_idInexistente_listaSinCambios() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestorDeOrganos.registrarOrgano("Riñón", donante);
        gestorDeOrganos.registrarOrgano("Corazón", donante);

        // Act
        gestorDeOrganos.eliminarOrgano(999);

        // Assert
        assertEquals(2, gestorDeOrganos.getListaDeOrganosDisponibles().tamaño());
    }

    @Test
    @DisplayName("Listar Órganos")
    public void listarOrganos_conDatos_devuelveListadoCorrecto() {
        // Arrange
        Donante donante = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo organo = gestorDeOrganos.registrarOrgano("Riñón", donante);

        StringBuilder esperado = new StringBuilder();

        esperado.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");
        esperado.append(organo.getIdentificador());
        esperado.append(", ");
        esperado.append(organo.getNombre());
        esperado.append(", ");
        esperado.append(organo.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(organo.getDonanteDelOrgano().getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(organo.getEsInfantil());
        esperado.append(".");
        esperado.append("\n");

        // Act
        String resultado = gestorDeOrganos.listarOrganosDisponibles();

        // Assert
        assertEquals(esperado.toString(), resultado);
    }

    @Test
    @DisplayName("Listar Órganos Vacío")
    public void listarOrganos_sinDatos_devuelveEncabezadoSolo() {
        // Arrange
        StringBuilder esperado = new StringBuilder();

        esperado.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");

        // Act
        String resultado = gestorDeOrganos.listarOrganosDisponibles();

        // Assert
        assertEquals(esperado.toString(), resultado);
    }

}