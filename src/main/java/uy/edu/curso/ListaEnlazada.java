/*
 * Clase: ListaEnlazada.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 15/04/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 * 
 * Resumen: 
 * La siguiente clase representa la lógica necesaria implementada de TDALista, para poder
 * utilizar una lista enlazada mediante un primero y un frente determinados.
 */
package uy.edu.curso;

import java.util.Comparator;
import java.util.function.Predicate;

import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Define una lista enlazada que implementa la interfaz TDALista,
 * con todos sus métodos implementados.
 *
 * <p>
 * Una lista permite almacenar elementos en una secuencia ordenada por posición,
 * admitiendo inserciones, accesos, eliminaciones, búsquedas y operaciones de
 * ordenamiento.
 * </p>
 *
 * <p>
 * Las posiciones de los elementos se interpretan mediante índices enteros.
 * Salvo que la implementación indique lo contrario, se asume indexación basada
 * en 0.
 * </p>
 *
 * @param <T> el tipo de los elementos almacenados en la lista
 */
public class ListaEnlazada<T> implements TDAListaEnlazada<T> {

    /**
     * Nodo que representa el primer elemento de la lista.
     */ 
    protected Nodo<T> primero;

    /**
     * Obtiene el dato del primer elemento de la lista.
     * 
     * @return dato del nodo que contiene al primer elemento de la lista.
     */
    public T primero() {
        if (this.primero == null) {
            return null;
        }
        
        return primero.getDato();
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elemento el elemento a agregar
     */
    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento, null);
        Nodo<T> actual = this.primero;

        if (actual == null) {
            this.primero = nuevoNodo;
        } else {
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    /**
     * Agrega un elemento en la posición indicada.
     *
     * <p>
     * Los elementos ubicados desde esa posición en adelante
     * desplazan su índice una posición hacia la derecha.
     * </p>
     *
     * @param index la posición en la que se insertará el elemento
     * @param elemento  el elemento a agregar
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    @Override
    public void agregar(int index, T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento, null);
        Nodo<T> actual = this.primero;

        if (index < 0 || index > this.tamaño()) {
            throw new IndexOutOfBoundsException();
        }
        if (actual == null || index == 0) {
            nuevoNodo.setSiguiente(actual);
            this.primero = nuevoNodo;
        } else {
            int i = 1;

            while (i < index && actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
                i++;
            }
            Nodo<T> nodoProximoAlSiguiente = actual.getSiguiente();

            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(nodoProximoAlSiguiente);
        }
    }

    /**
     * Obtiene el elemento almacenado en la posición indicada.
     *
     * @param index la posición del elemento a recuperar
     * @return el elemento ubicado en la posición indicada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    @Override
    public T obtener(int index) {
        Nodo<T> actual = this.primero;
        int i = 0;
        
        if (index < 0 || index > this.tamaño()) {
            throw new IndexOutOfBoundsException();
        }
        while (i < index) {
            actual = actual.getSiguiente();
            i++;
        }

        return actual.getDato();
    }

    /**
     * Remueve y devuelve el elemento almacenado en la posición indicada.
     *
     * <p>
     * Los elementos posteriores, si existen, desplazan su índice
     * una posición hacia la izquierda.
     * </p>
     *
     * @param index la posición del elemento a remover
     * @return el elemento removido
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */
    @Override
    public T remover(int index) {
        Nodo<T> actual = this.primero;
        Nodo<T> nodoEliminado;
        int i = 1;

        if (index < 0 || index > this.tamaño()) {
            throw new IndexOutOfBoundsException();
        }
        if (actual == null) {
            return null;
        }
        if (index == 0) {
            T primerDato = actual.getDato();
            this.primero = actual.getSiguiente();
            actual.setSiguiente(null);

            return primerDato;
        }
        while (i < index) {
            actual = actual.getSiguiente();
            i++;
        }
        nodoEliminado = actual.getSiguiente();
        actual.setSiguiente(nodoEliminado.getSiguiente());
        nodoEliminado.setSiguiente(null);

        return nodoEliminado.getDato();
    }

    /**
     * Remueve la primera ocurrencia del elemento indicado en la lista.
     *
     * <p>
     * La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.
     * </p>
     *
     * @param elemento el elemento a remover
     * @return {@code true} si el elemento fue encontrado y removido;
     *         {@code false} en caso contrario
     */
    @Override
    public boolean remover(T elemento) {
        Nodo<T> actual = this.primero;
        Nodo<T> eliminado;

        if (actual == null) {
            return false;
        }
        if (actual.getDato().equals(elemento)) {
            this.primero = actual.getSiguiente();

            return true;
        }
        while (actual.getSiguiente() != null) {
            T datoActual = actual.getSiguiente().getDato();

            if (datoActual.equals(elemento)) {
                eliminado = actual.getSiguiente();
                actual.setSiguiente(eliminado.getSiguiente());
                eliminado.setSiguiente(null);

                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    /**
     * Determina si la lista contiene el elemento indicado.
     *
     * <p>
     * La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.
     * </p>
     *
     * @param elemento el elemento a buscar
     * @return {@code true} si el elemento está presente en la lista;
     *         {@code false} en caso contrario
     */
    @Override
    public boolean contiene(T elemento) {
        Nodo<T> actual = this.primero;

        if (actual == null) {
            return false;
        }
        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    /**
     * Retorna el índice de la primera ocurrencia del elemento indicado.
     *
     * <p>
     * La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.
     * </p>
     *
     * @param elemento el elemento a buscar
     * @return el índice de la primera ocurrencia del elemento, o {@code -1}
     *         si el elemento no se encuentra en la lista
     */
    @Override
    public int indiceDe(T elemento) {
        Nodo<T> actual = this.primero;
        int i = 0;

        while (actual != null) {
            if (actual.getDato().equals(elemento)) {
                return i;
            }
            actual = actual.getSiguiente();
            i++;
        }

        return -1;
    }

    /**
     * Busca y retorna el primer elemento que cumple con el criterio dado.
     *
     * @param criterio el predicado que define la condición de búsqueda
     * @return el primer elemento que cumple el criterio, o {@code null}
     *         si no existe ninguno
     */
    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = this.primero;

        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    /**
     * Retorna una nueva lista con los elementos ordenados 
     * según el comparador dado.
     *
     * <p>
     * El criterio de orden está determinado por el objeto {@link Comparator}
     * recibido como parámetro.
     * </p>
     *
     * @param comparator el comparador que define el orden de los elementos
     * @return una lista ordenada según el criterio indicado
     */
    @Override
    public TDAListaEnlazada<T> ordenar(Comparator<T> comparator) {
        Nodo<T> actual = this.primero;
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>();
        int j;

        while (actual != null) {
            j = 0;

            while (j < nuevaLista.tamaño() && comparator.compare(actual.getDato(), nuevaLista.obtener(j)) > 0) {
                j++;
            }
            nuevaLista.agregar(j, actual.getDato());
            actual = actual.getSiguiente();
        }

        return nuevaLista;
    }

    /**
     * Retorna la cantidad de elementos almacenados en la lista.
     *
     * @return la cantidad de elementos de la lista
     */
    @Override
    public int tamaño() {
        int i = 0;
        Nodo<T> actual = this.primero;

        while (actual != null) {
            i++;
            actual = actual.getSiguiente();
        }

        return i;
    }

    /**
     * Determina si la lista no contiene elementos.
     *
     * @return {@code true} si la lista está vacía;
     *         {@code false} en caso contrario
     */
    @Override
    public boolean esVacio() {
        return primero == null;
    }

    /**
     * Elimina todos los elementos de la lista.
     *
     * <p>
     * Luego de invocar este método, la lista queda vacía.
     * </p>
     */
    @Override
    public void vaciar() {
        primero = null;
    }
    
}