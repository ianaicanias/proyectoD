package uy.edu.curso;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Tests para ColaEnlazada")
public class ColaEnlazadaTest {

    private ColaEnlazada<Integer> colaEnlazadaDeEnteros;

    @BeforeAll
    public static void inicializacion() {
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests de la cola enlazada finalizados. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        colaEnlazadaDeEnteros = new ColaEnlazada<>();
    }

    @Test
    @DisplayName("Obtener el Frente de la Cola Enlazada Vacía")
    public void frente_colaEnlazadaVaciaCreada_lanzaNullPointerException() {
        // Act - Assert
        assertThrows(NullPointerException.class, () -> {
            this.colaEnlazadaDeEnteros.frente();
        });
    }

    @Test
    @DisplayName("Poner en Cola un Dato Nulo")
    public void poneEnCola_colaEnlazadaCreadaConElementos_devuelveFalseIndicandoQueNoSePudoAgregarUnNulo() {
        // Arrange
        this.colaEnlazadaDeEnteros.poneEnCola(29);
        this.colaEnlazadaDeEnteros.poneEnCola(23);

        // Act
        boolean sePusoEnCola = this.colaEnlazadaDeEnteros.poneEnCola(null);

        // Assert
        assertFalse(sePusoEnCola);
    }

    @Test
    @DisplayName("Quitar de Cola un Elemento pero con la Cola Enlazada Vacía")
    public void quitaDeCola_colaEnlazadaVaciaCreada_devuelveNullIndicandoQueNoSePuedeQuitarPorqueLaListaEstaVacia() {
        // Act
        Integer elementoQuitado = this.colaEnlazadaDeEnteros.quitaDeCola();

        // Assert
        assertNull(elementoQuitado);
    }

    @Test
    @DisplayName("Poner y Quitar Elementos de la Cola Enlazada respeta FIFO")
    public void poneEnCola_y_quitaDeCola_elementosSeAtiendenEnOrdenFIFO() {
        // Arrange
        this.colaEnlazadaDeEnteros.poneEnCola(10);
        this.colaEnlazadaDeEnteros.poneEnCola(20);
        this.colaEnlazadaDeEnteros.poneEnCola(30);

        // Act
        Integer primero = this.colaEnlazadaDeEnteros.quitaDeCola();
        Integer segundo = this.colaEnlazadaDeEnteros.quitaDeCola();
        Integer tercero = this.colaEnlazadaDeEnteros.quitaDeCola();

        // Assert
        assertEquals(10, primero);
        assertEquals(20, segundo);
        assertEquals(30, tercero);
    }

}