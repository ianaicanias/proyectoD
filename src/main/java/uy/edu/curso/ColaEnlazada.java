/*
 * Clase: ColaEnlazada.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 15/04/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la lógica necesaria implementada de TDACola, para poder
 * utilizar una cola enlazada mediante un primero y un frente determinados.
 */
package uy.edu.curso;

import uy.edu.curso.tda.TDAColaEnlazada;

/**
 * Define una cola enlazada que extiende ListaEnlazada e implementa la interfaz TDACola,
 * con todos sus métodos implementados.
 *
 * <p>
 * Una cola permite almacenar elementos siguiendo el principio FIFO (First In, First Out),
 * donde el primer elemento en ingresar es el primero en ser retirado.
 * </p>
 *
 * @param <T> el tipo de los elementos almacenados en la cola
 */
public class ColaEnlazada<T> extends ListaEnlazada<T> implements TDAColaEnlazada<T> {

    /**
     * Nodo que apunta al último elemento ingresado en la cola (frente de salida).
     */
    protected Nodo<T> frente;

    /**
     * Retorna el dato del elemento que se encuentra al frente de la cola.
     *
     * @return el dato del elemento al frente de la cola
     * @throws NullPointerException si la cola está vacía
     */
    @Override
    public T frente() {
        if (this.frente == null) {
            throw new NullPointerException("No hay elementos en la cola.");
        }

        return this.frente.getDato();
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param dato el elemento a agregar
     * @return {@code true} si el elemento fue agregado correctamente;
     *         {@code false} si el dato es nulo
     */
    @Override
    public boolean poneEnCola(T dato) {
        if (dato != null) {
            if (this.frente == null) {
                this.primero = new Nodo<>(dato, null);
                this.frente = this.primero;

                return true;
            }
            Nodo<T> nodo = new Nodo<>(dato, null);
            this.frente.setSiguiente(nodo);
            this.frente = nodo;

            return true;
        }
        
        return false;
    }

    /**
     * Remueve y retorna el elemento que se encuentra al inicio de la cola.
     *
     * <p>
     * El elemento removido es el que lleva más tiempo en la cola,
     * siguiendo el principio FIFO.
     * </p>
     *
     * @return el elemento removido, o {@code null} si la cola está vacía
     */
    @Override
    public T quitaDeCola() {
        if (this.primero == null) {
            return null;
        }
        Nodo<T> nodoEliminado = this.primero;
        this.primero = nodoEliminado.getSiguiente();

        if (this.primero == null) {
            this.frente = null;
        }
        nodoEliminado.setSiguiente(null);

        return nodoEliminado.getDato();
    }

}