package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.services.GestorDeDonantesImpl;

public class GestorDeDonantesTest {

    private static GestorDeDonantesImpl gestor;

    @BeforeAll
    public static void init() {
        gestor = new GestorDeDonantesImpl();
    }

    @AfterAll
    public static void end() {
        System.out.println("Tests de donantes finalizados");
    }

    @BeforeEach
    public void reset() {
        gestor = new GestorDeDonantesImpl();
    }

    @Test
    @DisplayName("Registrar Donante")
    public void registrarDonante_ok() {
        Donante d = gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        assertEquals("123", d.getCedulaDeIdentidad());
        assertEquals("Valentín", d.getNombre());
        assertEquals("Riñón", d.getTipoDeOrgano());
        assertEquals("A+", d.getTipoDeSangre());
        assertEquals(20, d.getEdad());
        assertEquals(1, gestor.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Registrar Donante Duplicado")
    public void registrarDonante_duplicado() {
        gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Donante duplicado = gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        assertNull(duplicado);
        assertEquals(1, gestor.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Buscar Donante")
    public void buscarDonante_ok() {
        Donante esperado = gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Donante encontrado = gestor.buscarDonante("123");

        assertEquals(esperado, encontrado);
    }

    @Test
    @DisplayName("Buscar Donante Inexistente")
    public void buscarDonante_null() {
        Donante encontrado = gestor.buscarDonante("999");

        assertNull(encontrado);
    }

    @Test
    @DisplayName("Eliminar Donante")
    public void eliminarDonante_ok() {
        gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.eliminarDonante("123");

        assertNull(gestor.buscarDonante("123"));
        assertEquals(0, gestor.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Eliminar Donante Inexistente")
    public void eliminarDonante_inexistente_noCambiaLaLista() {
        gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.eliminarDonante("999");

        assertEquals(1, gestor.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Buscar Donante Luego de Eliminarlo")
    public void buscarDonante_despuesDeEliminar_retornaNull() {
        gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.eliminarDonante("123");

        Donante resultado = gestor.buscarDonante("123");

        assertNull(resultado);
    }

    @Test
    @DisplayName("Registrar Varios Donantes")
    public void registrarDonante_varios_datosGuardadosCorrectamente() {
        gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestor.registrarDonante("456", "Valentín", "Corazón", "O-", (byte) 25);
        gestor.registrarDonante("789", "Valentín", "Hígado", "B+", (byte) 30);

        assertEquals(3, gestor.getListaDeDonantes().tamaño());
    }

    @Test
    @DisplayName("Listar Donantes")
    public void listarDonantes_ok() {
        Donante d1 = gestor.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Donante d2 = gestor.registrarDonante("456", "Valentín", "Corazón", "O-", (byte) 25);

        StringBuilder esperado = new StringBuilder();

        esperado.append("---------------------- DATOS DE DONANTES ----------------------\n");
        esperado.append(d1.getNombre());
        esperado.append(", ");
        esperado.append(d1.getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(d1.getTipoDeOrgano());
        esperado.append(", ");
        esperado.append(d1.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(d1.getEdad());
        esperado.append(".");
        esperado.append("\n");

        esperado.append(d2.getNombre());
        esperado.append(", ");
        esperado.append(d2.getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(d2.getTipoDeOrgano());
        esperado.append(", ");
        esperado.append(d2.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(d2.getEdad());
        esperado.append(".");
        esperado.append("\n");

        String resultado = gestor.listarDonantes();

        assertEquals(esperado.toString(), resultado);
    }

    @Test
    @DisplayName("Listar Donantes Vacío")
    public void listarDonantes_vacio() {
        StringBuilder esperado = new StringBuilder();

        esperado.append("---------------------- DATOS DE DONANTES ----------------------\n");

        String resultado = gestor.listarDonantes();

        assertEquals(esperado.toString(), resultado);
    }
}