package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.classes.Receptor;
import uy.edu.curso.services.GestorDeReceptoresImpl;


public class GestorDeReceptoresTest {

    private static GestorDeReceptoresImpl gestorDeReceptoresImpl;

    @BeforeAll
    public static void inicializacion() {
        gestorDeReceptoresImpl = new GestorDeReceptoresImpl();
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Se ejecutaron todos los tests. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        gestorDeReceptoresImpl = new GestorDeReceptoresImpl();
    }

    @Test
    @DisplayName("Crear un Nuevo Gestor de Receptores")
    public void GestorDeReceptoresImpl_creacionDeUnGestorDeReceptores_seInicializanTantoLaListaComoLaColaCorrectamente() {
        // Arrange
        ListaEnlazada<Receptor> listaNoCreada = null;
        ColaEnlazada<Receptor> colaDePrioridadNoCreada = null;

        // Act
        GestorDeReceptoresImpl gestorDeReceptoresImpl2 = new GestorDeReceptoresImpl();

        // Assert
        assertFalse(gestorDeReceptoresImpl2.getListaDeReceptores() == listaNoCreada);
        assertFalse(gestorDeReceptoresImpl2.getColaDePrioridadDeReceptores() == colaDePrioridadNoCreada);
    }

    @Test
    @DisplayName("Registrar Correctamente un Nuevo Receptor.")
    public void registrarReceptor_datosValidos_receptorRegistradoCorrectamente() {
        // Arrange
        String cedulaDeIdentidadEsperada = "37432442";
        String nombreDelReceptorEsperado = "Joaquín Gomez";
        String tipoDeOrganoNecesitadoEsperado = "Hígado";
        String tipoDeSangreEsperado = "A+";
        byte edadEsperada = 19;
        byte puntajeDePrioridadEsperado = 6;

        // Act
        Receptor receptorCreado = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);

        // Assert
        assertEquals(cedulaDeIdentidadEsperada, receptorCreado.getCedulaDeIdentidad());
        assertEquals(nombreDelReceptorEsperado, receptorCreado.getNombre());
        assertEquals(tipoDeOrganoNecesitadoEsperado, receptorCreado.getTipoDeOrgano());
        assertEquals(tipoDeSangreEsperado, receptorCreado.getTipoDeSangre());
        assertEquals(edadEsperada, receptorCreado.getEdad());
        assertEquals(puntajeDePrioridadEsperado, receptorCreado.getPuntajeDePrioridad());
        assertTrue(gestorDeReceptoresImpl.buscarReceptor(receptorCreado.getCedulaDeIdentidad()) != null);
        assertFalse(gestorDeReceptoresImpl.getListaDeReceptores().esVacio());
    }

    @Test
    @DisplayName("Registrar Un Receptor Duplicado.")
    public void registrarReceptor_datosDuplicados_retornaNullIndicandoQueYaExisteElReceptorIngresado() {
        // Arrange
        byte tamañoDeListaEsperado = 1;

        // Act
        Receptor receptorCreado1 = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);
        Receptor receptorCreado2 = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);

        // Assert
        assertTrue(gestorDeReceptoresImpl.buscarReceptor(receptorCreado1.getCedulaDeIdentidad()) != null);
        assertTrue(gestorDeReceptoresImpl.getListaDeReceptores().tamaño() == tamañoDeListaEsperado);
        assertNull(receptorCreado2);
    }

    @Test
    @DisplayName("Insertar en la Cola de Prioridad un Receptor")
    public void insertarReceptorEnLaCola_receptorCreadoEnLaListaDeReceptores_insertaCorrectamenteEnLaColaDePrioridadAlReceptor() {
        // Arrange
        Receptor receptorCreado = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);

        // Act
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorCreado);

        // Assert
        assertEquals(receptorCreado, gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().frente());
    }

    @Test
    @DisplayName("Insertar en la Cola de Prioridad Varios Receptores")
    public void insertarReceptorEnLaCola_receptoresCreadosEnLaListaDeReceptores_insertaCorrectamenteEnLaColaDePrioridadALosReceptoresPorOrdenDePrioridad() {
        // Arrange
        Receptor receptorBaja = (Receptor) gestorDeReceptoresImpl.registrarReceptor(
                "37432442", "Joaquín Gomez", "Hígado", "A+", (byte) 19, (byte) 3);
        Receptor receptorAlta = (Receptor) gestorDeReceptoresImpl.registrarReceptor(
                "12345678", "Ana García", "Hígado", "A+", (byte) 25, (byte) 9);
        Receptor receptorMedia = (Receptor) gestorDeReceptoresImpl.registrarReceptor(
                "87654321", "Luis Pérez", "Hígado", "A+", (byte) 30, (byte) 6);

        // Act
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorBaja);
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorAlta);
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorMedia);

        // Assert
        assertEquals(receptorBaja.getCedulaDeIdentidad(), gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().frente().getCedulaDeIdentidad());
        assertEquals(receptorAlta, gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().quitaDeCola());
        assertEquals(receptorMedia, gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().quitaDeCola());
        assertEquals(receptorBaja, gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().quitaDeCola());
    }

    @Test
    @DisplayName("Insertar en la Cola con Desempate por Edad")
    public void insertarReceptorEnLaCola_dosReceptoresConMismoPuntaje_elMenorEnEdadQuedaPrimeroEnLaCola() {
        // Arrange
        Receptor receptorMayor = (Receptor) gestorDeReceptoresImpl.registrarReceptor(
            "11111111", "Carlos López", "Hígado", "A+", (byte) 40, (byte) 6);
        Receptor receptorMenor = (Receptor) gestorDeReceptoresImpl.registrarReceptor(
            "22222222", "María Díaz", "Hígado", "A+", (byte) 19, (byte) 6);

        // Act
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorMayor);
        gestorDeReceptoresImpl.insertarReceptorEnLaCola(receptorMenor);

        // Assert
        assertEquals(receptorMenor, gestorDeReceptoresImpl.getColaDePrioridadDeReceptores().frente());
    }

    @Test
    @DisplayName("Buscar un Receptor Correcto.")
    public void buscarReceptor_listaConReceptorCreado_encuentraYDevuelveElReceptorSolicitado() {
        // Arrange
        Receptor receptorCreadoEsperado = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);

        // Act
        Receptor receptorBuscado = gestorDeReceptoresImpl.buscarReceptor("37432442");

        // Assert
        assertEquals(receptorCreadoEsperado, receptorBuscado);
        assertEquals(receptorCreadoEsperado.getCedulaDeIdentidad(), receptorBuscado.getCedulaDeIdentidad());
        assertEquals(receptorCreadoEsperado.getNombre(), receptorBuscado.getNombre());
        assertEquals(receptorCreadoEsperado.getTipoDeOrgano(), receptorBuscado.getTipoDeOrgano());
        assertEquals(receptorCreadoEsperado.getTipoDeSangre(), receptorBuscado.getTipoDeSangre());
        assertEquals(receptorCreadoEsperado.getEdad(), receptorBuscado.getEdad());
        assertEquals(receptorCreadoEsperado.getPuntajeDePrioridad(), receptorBuscado.getPuntajeDePrioridad());
    }

    @Test
    @DisplayName("Eliminar un Receptor de la Lista")
    public void eliminarReceptor_listaConReceptorCreado_encuentraYEliminaElReceptorSolicitado() {
        // Arrange
        Receptor receptorCreado = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);
        String cedulaDeIdentidadDelReceptorEliminado = receptorCreado.getCedulaDeIdentidad();

        // Act
        gestorDeReceptoresImpl.eliminarReceptor(cedulaDeIdentidadDelReceptorEliminado);

        // Assert
        assertNull(gestorDeReceptoresImpl.buscarReceptor(cedulaDeIdentidadDelReceptorEliminado));
    }

    @Test
    @DisplayName("Listar los Receptores de la Lista")
    public void listarReceptores_listaConReceptoresCreados_retornaUnaListaConTodosLosReceptoresExistentes() {
        // Arrange
        Receptor receptorCreado1 = (Receptor) gestorDeReceptoresImpl.registrarReceptor("37432442", "Joaquín Gomez",
                "Hígado", "A+", (byte) 19, (byte) 6);
        Receptor receptorCreado2 = (Receptor) gestorDeReceptoresImpl.registrarReceptor("48578471", "Luis Mendez",
                "Riñón", "O-", (byte) 26, (byte) 3);
        Receptor receptorCreado3 = (Receptor) gestorDeReceptoresImpl.registrarReceptor("73472730", "Leandro Rodriguez",
                "Estómago", "A-", (byte) 57, (byte) 9);
        StringBuilder resultadoEsperado = new StringBuilder();

        resultadoEsperado.append("----------------------- DATOS DE RECEPTORES -----------------------\n");
        resultadoEsperado.append(receptorCreado1.getNombre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado1.getCedulaDeIdentidad());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado1.getTipoDeOrgano());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado1.getTipoDeSangre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado1.getEdad());
        resultadoEsperado.append(".");
        resultadoEsperado.append(receptorCreado2.getNombre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado2.getCedulaDeIdentidad());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado2.getTipoDeOrgano());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado2.getTipoDeSangre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado2.getEdad());
        resultadoEsperado.append(".");
        resultadoEsperado.append(receptorCreado3.getNombre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado3.getCedulaDeIdentidad());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado3.getTipoDeOrgano());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado3.getTipoDeSangre());
        resultadoEsperado.append(", ");
        resultadoEsperado.append(receptorCreado3.getEdad());
        resultadoEsperado.append(".");

        // Act
        String listadoDeReceptores = gestorDeReceptoresImpl.listarReceptores();

        // Assert
        assertEquals(resultadoEsperado.toString(), listadoDeReceptores);
    }

}