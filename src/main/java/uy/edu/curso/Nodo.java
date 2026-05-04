/*
 * Clase: Nodo.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 15/04/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa un nodo genérico utilizado en estructuras
 * de datos enlazadas, como listas y colas, almacenando un dato y una referencia
 * al siguiente nodo.
 */
package uy.edu.curso;


/**
 * Representa un nodo genérico que almacena un dato y una referencia
 * al siguiente nodo en una estructura enlazada.
 *
 * @param <T> el tipo de dato almacenado en el nodo
 */
public class Nodo<T> {

    /**
     * Dato almacenado en el nodo.
     */
    private T dato;

    /**
     * Referencia al siguiente nodo en la estructura.
     */
    private Nodo<T> siguiente;

    /**
     * Crea un nodo vacío sin dato ni referencia al siguiente nodo.
     */
    public Nodo() {
    }

    /**
     * Crea un nodo con un dato y una referencia al siguiente nodo.
     *
     * @param dato el dato a almacenar en el nodo
     * @param siguiente referencia al siguiente nodo
     */
    public Nodo(T dato, Nodo<T> siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return el dato contenido en el nodo
     */
    public T getDato() {
        return this.dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     *
     * @return el siguiente nodo, o {@code null} si no existe
     */
    public Nodo<T> getSiguiente() {
        return this.siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente el nodo que será el siguiente en la estructura
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

}