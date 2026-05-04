/*
 * Clase: OrganoImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta de un órgano disponible
 * para trasplante en el sistema BioQueue. Implementa la interfaz Organo y mantiene
 * un identificador único autoincremental por cada instancia creada, además de
 * determinar automáticamente si el órgano es de origen infantil según la edad del donante.
 */
package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;


/**
 * Implementación concreta de un órgano disponible para trasplante en el sistema BioQueue.
 * Implementa @see Organo y genera automáticamente un identificador único por cada
 * instancia creada. El órgano hereda el tipo de sangre del donante y determina
 * si es de origen infantil en base a su edad.
 */
public class OrganoImpl implements Organo {

    /**
     * Contador estático que se incrementa con cada nuevo órgano creado,
     * garantizando identificadores únicos en el sistema.
     */
    private static long contadorDeOrganosExistentes = 0;

    /**
     * Identificador único e inmutable del órgano.
     */
    private final long identificador;

    /**
     * Indica si el órgano proviene de un donante menor de 18 años.
     * Se determina automáticamente al momento de la creación.
     */
    private final boolean esInfantil;

    /**
     * Donante al que pertenece el órgano. Inmutable una vez asignado.
     */
    private final Donante donanteDelOrgano;

    /**
     * Nombre del órgano (ej: "Corazón", "Riñón").
     */
    private String nombre;

    /**
     * Tipo de sangre del órgano, heredado del donante al momento de la creación.
     */
    private String tipoDeSangre;

    /**
     * Constructor de la clase OrganoImpl. Asigna automáticamente el identificador único,
     * determina si el órgano es infantil y obtiene el tipo de sangre del donante.
     *
     * @param nombre  Nombre del órgano.
     * @param donante Donante del cual proviene el órgano.
     */
    public OrganoImpl(String nombre, Donante donante) {
        this.identificador = ++contadorDeOrganosExistentes;
        this.esInfantil = donante.getEdad() < 18;
        this.donanteDelOrgano = donante;
        this.nombre = nombre;
        this.tipoDeSangre = donante.getTipoDeSangre();
    }

    /**
     * Retorna el identificador único del órgano.
     *
     * @return Identificador del órgano.
     */
    @Override
    public long getIdentificador() {
        return this.identificador;
    }

    /**
     * Indica si el órgano proviene de un donante menor de 18 años.
     *
     * @return {@code true} si el donante era menor de 18 años, {@code false} en caso contrario.
     */
    @Override
    public boolean getEsInfantil() {
        return this.esInfantil;
    }

    /**
     * Retorna el nombre del órgano.
     *
     * @return Nombre del órgano.
     */
    @Override
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Retorna el tipo de sangre del órgano.
     *
     * @return Tipo de sangre del órgano.
     */
    @Override
    public String getTipoDeSangre() {
        return this.tipoDeSangre;
    }

    /**
     * Retorna el donante al que pertenece el órgano.
     *
     * @return Donante del órgano.
     */
    @Override
    public Donante getDonanteDelOrgano() {
        return this.donanteDelOrgano;
    }

    /* Métodos pensados para prevenir posibles equivocaciones por parte del usuario. */

    /**
     * Actualiza el nombre del órgano.
     *
     * @param nombre Nuevo nombre del órgano.
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Actualiza el tipo de sangre del órgano.
     *
     * @param tipoDeSangre Nuevo tipo de sangre del órgano.
     */
    @Override
    public void setTipoDeSangre(String tipoDeSangre) {
        this.tipoDeSangre = tipoDeSangre;
    }

}