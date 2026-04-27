package uy.edu.curso;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.interfaces.Receptor;


@DisplayName("Tests para Nodo")
public class NodoTest {

    @Test
    @DisplayName("Crear un Nodo Vacío")
    public void Nodo_claseNodoExistente_nodoSinDatosCreadoExistosamente() {
        // Act
        Nodo<Receptor> nodo = new Nodo<>();

        // Assert
        assertNotNull(nodo);
        assertNull(nodo.getDato());
        assertNull(nodo.getSiguiente());
    }

}