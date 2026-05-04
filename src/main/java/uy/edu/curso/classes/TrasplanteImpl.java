/*
 * Clase: TrasplanteImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta de un trasplante realizado
 * en el sistema BioQueue. Implementa la interfaz Trasplante y registra la transacción
 * entre un receptor, un órgano y su donante, manteniendo un identificador único
 * autoincremental por cada trasplante efectuado.
 */
package uy.edu.curso.classes;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;


/**
 * Implementación concreta de un trasplante realizado en el sistema BioQueue.
 * Implementa @see Trasplante y registra la asignación de un órgano a un receptor,
 * incluyendo la referencia al donante correspondiente. Genera un identificador
 * único autoincremental por cada trasplante efectuado.
 */
public class TrasplanteImpl implements Trasplante {

    /**
     * Contador estático que se incrementa con cada nuevo trasplante registrado,
     * garantizando identificadores únicos en el sistema.
     */
    private static long cantidadDeTrasplanteRealizados = 0;

    /**
     * Identificador único e inmutable del trasplante.
     */
    private final long identificador;

    /**
     * Receptor que recibe el órgano en el trasplante.
     */
    private final Receptor receptorDelTrasplante;

    /**
     * Órgano trasplantado al receptor.
     */
    private final Organo organoTrasplantado;

    /**
     * Donante del órgano trasplantado, obtenido directamente del órgano.
     */
    private final Donante donanteDelOrganoDelTrasplante;

    /**
     * Constructor de la clase TrasplanteImpl. Asigna automáticamente el identificador único
     * y obtiene el donante a partir del órgano trasplantado.
     *
     * @param receptorDelTrasplante Receptor que recibe el órgano.
     * @param organoTrasplantado    Órgano que será trasplantado.
     */
    public TrasplanteImpl(Receptor receptorDelTrasplante, Organo organoTrasplantado) {
        this.identificador = ++cantidadDeTrasplanteRealizados;
        this.receptorDelTrasplante = receptorDelTrasplante;
        this.organoTrasplantado = organoTrasplantado;
        this.donanteDelOrganoDelTrasplante = organoTrasplantado.getDonanteDelOrgano();
    }

    /**
     * Retorna el identificador único del trasplante.
     *
     * @return Identificador del trasplante.
     */
    @Override
    public long getIdentificador() {
        return this.identificador;
    }
    
    /**
     * Retorna el receptor del trasplante.
     *
     * @return Receptor que recibió el órgano.
     */
    @Override
    public Receptor getReceptorDelTrasplante() {
        return this.receptorDelTrasplante;
    }

    /**
     * Retorna el órgano trasplantado.
     *
     * @return Órgano trasplantado al receptor.
     */
    @Override
    public Organo getOrganoTrasplantado() {
        return this.organoTrasplantado;
    }

    /**
     * Retorna el donante del órgano trasplantado.
     *
     * @return Donante del órgano.
     */
    @Override
    public Donante getDonanteDelOrganoDelTrasplante() {
        return this.donanteDelOrganoDelTrasplante;
    }

    /**
     * Retorna una representación en texto del trasplante realizado,
     * incluyendo el identificador, el órgano, el donante y el receptor.
     *
     * @return Cadena de texto con los datos del trasplante.
     */
    @Override
    public String toString() {
        return "Trasplante realizado, con ID: " + identificador + ".\n" 
                + "Órgano: " + organoTrasplantado.getNombre() + ".\n"
                + "De parte del donante: " + donanteDelOrganoDelTrasplante.getNombre() + ".\n"
                + "Para el receptor: " + receptorDelTrasplante.getNombre() + ".";
    }

}