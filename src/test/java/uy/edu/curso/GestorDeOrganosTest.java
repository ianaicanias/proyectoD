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


public class GestorDeOrganosTest {

    private static GestorDeOrganosImpl gestor;
    private static GestorDeDonantesImpl gestorDonantes;

    @BeforeAll
    public static void init() {
        gestor = new GestorDeOrganosImpl();
        gestorDonantes = new GestorDeDonantesImpl();
    }

    @AfterAll
    public static void end() {
        System.out.println("Tests de órganos finalizados");
    }

    @BeforeEach
    public void reset() {
        gestor = new GestorDeOrganosImpl();
        gestorDonantes = new GestorDeDonantesImpl();
    }

    @Test
    @DisplayName("Registrar Organo")
    public void registrarOrgano_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo o = gestor.registrarOrgano("Riñón", d);

        assertEquals("Riñón", o.getNombre());
        assertEquals(d.getCedulaDeIdentidad(), o.getDonanteDelOrgano().getCedulaDeIdentidad());
    }

    @Test
    @DisplayName("Registrar Varios Organos")
    public void registrarOrgano_varios() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);
        gestor.registrarOrgano("Corazón", d);

        assertEquals(2, gestor.getListaDeOrganos().tamaño());
    }

    @Test
    @DisplayName("Buscar Organo por ID")
    public void buscarOrganoPorId_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo o = gestor.registrarOrgano("Riñón", d);

        Organo encontrado = gestor.buscarOrganoPorIdentificador(o.getIdentificador());

        assertEquals(o, encontrado);
    }

    @Test
    @DisplayName("Buscar Organo Inexistente")
    public void buscarOrgano_inexistente() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);
        gestor.registrarOrgano("Corazón", d);

        Organo resultado = gestor.buscarOrganoPorIdentificador(999);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Buscar por Nombre")
    public void buscarPorNombre_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestor.registrarOrgano("Riñón", d);

        var lista = gestor.buscarOrganosPorNombre("Riñón");

        assertEquals(1, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Nombre con Dos Resultados")
    public void buscarPorNombre_dos() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);
        gestor.registrarOrgano("Riñón", d);

        var lista = gestor.buscarOrganosPorNombre("Riñón");

        assertEquals(2, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Nombre Inexistente")
    public void buscarPorNombre_inexistente() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);

        var lista = gestor.buscarOrganosPorNombre("Pulmón");

        assertEquals(0, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre")
    public void buscarPorSangre_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        gestor.registrarOrgano("Riñón", d);

        var lista = gestor.buscarOrganosPorTipoDeSangre("A+");

        assertEquals(1, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre con Dos Resultados")
    public void buscarPorSangre_dos() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);
        gestor.registrarOrgano("Corazón", d);

        var lista = gestor.buscarOrganosPorTipoDeSangre("A+");

        assertEquals(2, lista.tamaño());
    }

    @Test
    @DisplayName("Buscar por Tipo de Sangre Inexistente")
    public void buscarPorSangre_inexistente() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);

        var lista = gestor.buscarOrganosPorTipoDeSangre("O-");

        assertEquals(0, lista.tamaño());
    }

    @Test
    @DisplayName("Eliminar Organo")
    public void eliminarOrgano_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo o = gestor.registrarOrgano("Riñón", d);

        gestor.eliminarOrgano(o.getIdentificador());

        assertNull(gestor.buscarOrganoPorIdentificador(o.getIdentificador()));
    }

    @Test
    @DisplayName("Eliminar Organo Inexistente")
    public void eliminarOrgano_inexistente() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);

        gestor.registrarOrgano("Riñón", d);
        gestor.registrarOrgano("Corazón", d);

        gestor.eliminarOrgano(999);

        assertEquals(2, gestor.getListaDeOrganos().tamaño());
    }

    @Test
    @DisplayName("Listar Organos")
    public void listarOrganos_ok() {
        Donante d = gestorDonantes.registrarDonante("123", "Valentín", "Riñón", "A+", (byte) 20);
        Organo o = gestor.registrarOrgano("Riñón", d);

        StringBuilder esperado = new StringBuilder();

        esperado.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");
        esperado.append(o.getIdentificador());
        esperado.append(", ");
        esperado.append(o.getNombre());
        esperado.append(", ");
        esperado.append(o.getTipoDeSangre());
        esperado.append(", ");
        esperado.append(o.getDonanteDelOrgano().getCedulaDeIdentidad());
        esperado.append(", ");
        esperado.append(o.getEsInfantil());
        esperado.append(".");
        esperado.append("\n");

        String resultado = gestor.listarOrganosDisponibles();

        assertEquals(esperado.toString(), resultado);
    }

    @Test
    @DisplayName("Listar Organos Vacío")
    public void listarOrganos_vacio() {
        StringBuilder esperado = new StringBuilder();

        esperado.append("----------------------- DATOS DE ÓRGANOS DISPONIBLES -----------------------\n");

        String resultado = gestor.listarOrganosDisponibles();

        assertEquals(esperado.toString(), resultado);
    }
}