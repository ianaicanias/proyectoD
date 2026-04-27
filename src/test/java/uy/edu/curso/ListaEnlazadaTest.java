package uy.edu.curso;

import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.tda.TDAListaEnlazada;


@DisplayName("Tests para ListaEnlazada")
public class ListaEnlazadaTest {

    private ListaEnlazada<Integer> listaEnlazadaDeEnteros;

    @BeforeAll
    public static void inicializacion() {
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests de lista enlazada finalizados. . .");
    }

    @BeforeEach
    public void limpiarDatos() {
        this.listaEnlazadaDeEnteros = new ListaEnlazada<>();
    }

    @Test
    @DisplayName("Vaciar una Lista Enlazada")
    public void vaciar_listaCreadaConElementos_listaVaciaSinElementos() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(342);

        // Act
        this.listaEnlazadaDeEnteros.vaciar();

        // Assert
        assertTrue(this.listaEnlazadaDeEnteros.tamaño() == 0);
    }

    @Test
    @DisplayName("Obtener el Primer Valor de la Lista Enlazada Vacía")
    public void primero_listaEnlazadaVaciaCreada_seObtieneNullIndicandoQueEstaVacia() {
        // Act
        Integer primerReceptor = this.listaEnlazadaDeEnteros.primero();

        // Assert
        assertNull(primerReceptor);
    }

    @Test
    @DisplayName("Obtener el Primer Valor de la Lista Enlazada Con Valores")
    public void primero_listaEnlazadaConElementosCargados_seObtieneElPrimerElementoCargadoEnLaLista() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(213);

        // Act
        Integer primerReceptor = this.listaEnlazadaDeEnteros.primero();

        // Assert
        assertNotNull(primerReceptor);
        assertEquals(213, primerReceptor);
    }

    @Test
    @DisplayName("Buscar Elemento Existente Devuelve el Elemento Correcto")
    void buscar_elementoExistente_devuelveElemento() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(1);
        this.listaEnlazadaDeEnteros.agregar(2);
        this.listaEnlazadaDeEnteros.agregar(3);

        // Act
        Integer resultado = this.listaEnlazadaDeEnteros.buscar(x -> x == 2);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado);
    }

    @Test
    @DisplayName("Buscar Elemento Inexistente Devuelve Null")
    void buscar_elementoInexistente_devuelveNull() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(1);
        this.listaEnlazadaDeEnteros.agregar(2);
        this.listaEnlazadaDeEnteros.agregar(3);

        // Act
        Integer resultado = this.listaEnlazadaDeEnteros.buscar(x -> x == 5);

        // Assert
        assertNull(resultado);
    }

    @Test
    @DisplayName("Indice de un Elemento en el Medio de la Lista")
    public void indiceDe_elementoEnMedio_devuelveIndiceCorrecto() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);
        this.listaEnlazadaDeEnteros.agregar(20);
        this.listaEnlazadaDeEnteros.agregar(30);

        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(20);

        // Assert
        assertEquals(1, resultado);
    }

    @Test
    @DisplayName("Indice de un Elemento al Inicio de la Lista")
    public void indiceDe_elementoAlInicio_devuelveCero() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(5);
        this.listaEnlazadaDeEnteros.agregar(10);
        this.listaEnlazadaDeEnteros.agregar(15);

        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(5);

        // Assert
        assertEquals(0, resultado);
    }

    @Test
    @DisplayName("Indice de un Elemento al Final de la Lista")
    public void indiceDe_elementoAlFinal_devuelveIndiceCorrecto() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(7);
        this.listaEnlazadaDeEnteros.agregar(8);
        this.listaEnlazadaDeEnteros.agregar(9);

        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(9);

        // Assert
        assertEquals(2, resultado);
    }

    @Test
    @DisplayName("Indice de un Elemento Inexistente Devuelve -1")
    public void indiceDe_elementoInexistente_devuelveMenosUno() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(1);
        this.listaEnlazadaDeEnteros.agregar(2);
        this.listaEnlazadaDeEnteros.agregar(3);

        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(100);

        // Assert
        assertEquals(-1, resultado);
    }

    @Test
    @DisplayName("Indice en Lista Vacía Devuelve -1")
    public void indiceDe_listaVacia_devuelveMenosUno() {
        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(1);

        // Assert
        assertEquals(-1, resultado);
    }

    @Test
    @DisplayName("Indice de Elemento Duplicado Devuelve el Primero")
    public void indiceDe_elementosDuplicados_devuelvePrimerIndice() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(4);
        this.listaEnlazadaDeEnteros.agregar(6);
        this.listaEnlazadaDeEnteros.agregar(4);

        // Act
        int resultado = this.listaEnlazadaDeEnteros.indiceDe(4);

        // Assert
        assertEquals(0, resultado);
    }
    
    @Test
    @DisplayName("Ordenar Lista de Enteros Ascendente")
    public void ordenar_listaConElementos_desordenados_devuelveListaOrdenadaAscendente() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(5);
        this.listaEnlazadaDeEnteros.agregar(1);
        this.listaEnlazadaDeEnteros.agregar(3);

        // Act
        TDAListaEnlazada<Integer> resultado = this.listaEnlazadaDeEnteros.ordenar((a, b) -> a - b);

        // Assert
        assertEquals(1, resultado.obtener(0));
        assertEquals(3, resultado.obtener(1));
        assertEquals(5, resultado.obtener(2));
    }

    @Test
    @DisplayName("Ordenar Lista Vacía Devuelve Lista Vacía")
    public void ordenar_listaVacia_devuelveListaVacia() {
        // Act
        TDAListaEnlazada<Integer> resultado = this.listaEnlazadaDeEnteros.ordenar((a, b) -> a - b);

        // Assert
        assertEquals(0, resultado.tamaño());
    }

    @Test
    @DisplayName("Obtener con Índice Negativo Lanza Excepción")
    public void obtener_indiceNegativo_lanzaIndexOutOfBoundsException() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);

        // Act - Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.obtener(-1);
        });
    }

    @Test
    @DisplayName("Obtener con Índice Mayor al Tamaño Lanza Excepción")
    public void obtener_indiceMayorAlTamanio_lanzaIndexOutOfBoundsException() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.obtener(5);
        });
    }

    @Test
    @DisplayName("Contiene en Lista Vacía Devuelve False")
    public void contiene_listaVacia_devuelveFalse() {
        // Act
        boolean resultado = this.listaEnlazadaDeEnteros.contiene(10);

        // Assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Contiene Elemento Inexistente Devuelve False")
    public void contiene_elementoInexistente_devuelveFalse() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(1);
        this.listaEnlazadaDeEnteros.agregar(2);
        this.listaEnlazadaDeEnteros.agregar(3);

        // Act
        boolean resultado = this.listaEnlazadaDeEnteros.contiene(10);

        // Assert
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Remover con Índice Negativo Lanza Excepción")
    public void remover_indiceNegativo_lanzaIndexOutOfBoundsException() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);

        // Act - Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.remover(-1);
        });
    }

    @Test
    @DisplayName("Remover con Índice Mayor al Tamaño Lanza Excepción")
    public void remover_indiceMayorAlTamanio_lanzaIndexOutOfBoundsException() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(19);

        // Act - Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.remover(198);
        });
    }

    @Test
    @DisplayName("Remover un Elemento en una Lista Enlazada Vacía por Índice")
    public void remover_listaVaciaCreada_devuelveNuloIndicandoQueNoEsPosibleEliminarElElementoDeLaLista() {
        // Act
        Integer eliminado = this.listaEnlazadaDeEnteros.remover(0);

        // Assert
        assertNull(eliminado); 
    }

    @Test
    @DisplayName("Remover un Elemento en una Lista Enlazada Vacía por Elemento")
    public void remover_listaVaciaCreada_devuelveFalseIndicandoQueNoSePuedeEliminarAlgoQueNoExiste() {
        // Act
        boolean fueEliminado = this.listaEnlazadaDeEnteros.remover((Integer) 19);

        // Assert
        assertFalse(fueEliminado);
    }

    @Test
    @DisplayName("Remover un Elemento en una Lista Enlazada el Cual no Existe por Elemento")
    public void remover_listaCreadaConElementos_devuelveFalseIndicandoQueNoSePudoEncontrarElElementoARemover() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);
        this.listaEnlazadaDeEnteros.agregar(30);
        this.listaEnlazadaDeEnteros.agregar(231);

        // Act
        boolean fueEliminado = this.listaEnlazadaDeEnteros.remover((Integer) 43);
 
        // Assert
        assertFalse(fueEliminado); 
    }

    @Test
    @DisplayName("Agregar un Elemento por Índice pero con un Índice Negativo")
    public void agregar_listaCreadaYSolicitudConIndiceNegativo_lanzaIndexOutOfBoundsException() {
        // Act - Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.agregar(-1, 2933);
        });
    }

    @Test
    @DisplayName("Agregar un Elemento por Índice pero con un Índice Mayor al Tamaño de la Lista Enlazada")
    public void agregar_listaCreadaYSolicitudConIndiceMayorAlTamanio_lanzaIndexOutOfBoundsException() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(139);

        // Act - Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            this.listaEnlazadaDeEnteros.agregar(2832, 21);
        });
    }

    @Test
    @DisplayName("Agregar un Elemento Antes del Final de la Lista Enlazada por Índice")
    public void agregar_listaConElementosCargados_agregaElNuevoElementoEnLaAntepenultimaPosicionDeLaLista() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(283);
        this.listaEnlazadaDeEnteros.agregar(231);
        this.listaEnlazadaDeEnteros.agregar(212);

        // Act
        this.listaEnlazadaDeEnteros.agregar(2, 21);

        // Assert
        assertTrue(this.listaEnlazadaDeEnteros.tamaño() == 4);
        assertEquals(21, this.listaEnlazadaDeEnteros.obtener(2));
        assertEquals(212, this.listaEnlazadaDeEnteros.obtener(3));
    }

    @Test
    @DisplayName("Agregar un Elemento al Final de la Lista Enlazada por Índice")
    public void agregar_listaConElementosCargados_agregaElElementoAlFinalDeLaLista() {
        // Arrange
        this.listaEnlazadaDeEnteros.agregar(10);
        this.listaEnlazadaDeEnteros.agregar(20);
        this.listaEnlazadaDeEnteros.agregar(30);

        // Act
        this.listaEnlazadaDeEnteros.agregar(3, 40); // index == tamaño

        // Assert
        assertEquals(4, this.listaEnlazadaDeEnteros.tamaño());
        assertEquals(40, this.listaEnlazadaDeEnteros.obtener(3));
    }

}