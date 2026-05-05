/*
 * Clase: GestorDeTrasplantesImpl.
 * Programadores: Axel Ferreira, Ianai Canias, Thiago Soca, Valentín Guerrico.
 * Fecha: 03/05/2026.
 * Copyright: Todos los derechos reservados para los programadores de este archivo, 2026.
 *
 * Resumen:
 * La siguiente clase representa la implementación concreta del gestor de trasplantes
 * en el sistema BioQueue. Implementa la interfaz GestorDeTrasplantes y coordina
 * la asignación automática de órganos a receptores compatibles, registrando cada
 * trasplante realizado y delegando la validación de compatibilidad sanguínea
 * al ConsultorDeCompatibilidadSanguinea inyectado.
 */
package uy.edu.curso.services;

import uy.edu.curso.ListaEnlazada;
import uy.edu.curso.classes.TrasplanteImpl;
import uy.edu.curso.interfaces.ConsultorDeCompatibilidadSanguinea;
import uy.edu.curso.interfaces.GestorDeTrasplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.tda.TDAListaEnlazada;


/**
 * Implementación concreta del gestor de trasplantes del sistema BioQueue.
 * Implementa @see GestorDeTrasplantes y coordina la asignación de órganos
 * a receptores compatibles recorriendo la lista de prioridad. La compatibilidad
 * sanguínea es evaluada por el @see ConsultorDeCompatibilidadSanguinea inyectado
 * en el constructor, respetando el principio de inversión de dependencias.
 */
public class GestorDeTrasplantesImpl implements GestorDeTrasplantes {

    /**
     * Lista enlazada que almacena el historial de todos los trasplantes realizados.
     */
    private final TDAListaEnlazada<Trasplante> listaDeTrasplantesRealizados;

    /**
     * Consultor utilizado para verificar la compatibilidad sanguínea entre
     * donante y receptor al momento de asignar un órgano.
     */
    private final ConsultorDeCompatibilidadSanguinea consultorDeCompatibilidadSanguinea;

    /**
     * Constructor de la clase GestorDeTrasplantesImpl. Inicializa la lista de trasplantes
     * y recibe el consultor de compatibilidad sanguínea por inyección de dependencias.
     *
     * @param consultorDeCompatibilidadSanguinea Consultor a utilizar para verificar la compatibilidad sanguínea.
     */
    public GestorDeTrasplantesImpl(ConsultorDeCompatibilidadSanguinea consultorDeCompatibilidadSanguinea) {
        this.listaDeTrasplantesRealizados = new ListaEnlazada<>();
        this.consultorDeCompatibilidadSanguinea = consultorDeCompatibilidadSanguinea;
    }

    /**
     * Retorna la lista de trasplantes realizados en el sistema.
     *
     * @return Lista enlazada con todos los trasplantes realizados.
     */
    @Override
    public TDAListaEnlazada<Trasplante> getListaDeTrasplantesRealizados() {
        return this.listaDeTrasplantesRealizados;
    }

    /**
     * Intenta asignar un órgano al receptor compatible de mayor prioridad en la
     * lista de espera. La compatibilidad se determina por tipo de sangre y tipo
     * de órgano. Si se realiza el trasplante, el receptor es removido de ambas
     * listas y el órgano es removido de los disponibles.
     *
     * @param organo                      Órgano a asignar.
     * @param listaDeReceptores           Registro general de receptores.
     * @param listaDePrioridadDeReceptores Lista de receptores en espera ordenada por prioridad.
     * @param listaDeOrganosDisponibles   Lista de órganos disponibles para trasplante.
     * @return {@code true} si se realizó el trasplante, {@code false} si no se encontró receptor compatible.
     */
    @Override
    public boolean asignarOrganoAReceptor(Organo organo, TDAListaEnlazada<Receptor> listaDeReceptores, 
            TDAListaEnlazada<Receptor> listaDePrioridadDeReceptores, TDAListaEnlazada<Organo> listaDeOrganosDisponibles) {
        int tamañoDeLaColaDePrioridad = listaDePrioridadDeReceptores.tamaño();

        for (int i = 0; i < tamañoDeLaColaDePrioridad; i++) {
            Receptor receptorEncontrado = listaDePrioridadDeReceptores.obtener(i);
            boolean esCompatible = consultorDeCompatibilidadSanguinea.esCompatible(
                    organo.getTipoDeSangre(), receptorEncontrado.getTipoDeSangre());
            boolean esMismoOrgano = organo.getNombre().equals(receptorEncontrado.getTipoDeOrgano());

            if (esCompatible && esMismoOrgano) {
                Trasplante nuevoTrasplanteRealizado = new TrasplanteImpl(receptorEncontrado, organo);

                listaDePrioridadDeReceptores.remover(receptorEncontrado);
                listaDeReceptores.remover(receptorEncontrado);
                listaDeOrganosDisponibles.remover(organo);
                this.listaDeTrasplantesRealizados.agregar(nuevoTrasplanteRealizado);
                
                return true;
            }
        }

        return false;
    }

    /**
     * Busca un trasplante en el historial por su identificador único.
     *
     * @param identificadorDelTrasplante Identificador del trasplante a buscar.
     * @return El trasplante encontrado, o {@code null} si no existe.
     */
    @Override
    public Trasplante buscarTrasplante(long identificadorDelTrasplante) {
        int tamañoDeLaListaDeTrasplantesRealizados = this.listaDeTrasplantesRealizados.tamaño();
        int i = 0;

        while (i < tamañoDeLaListaDeTrasplantesRealizados) {
            Trasplante trasplanteEncontrado = this.listaDeTrasplantesRealizados.obtener(i);

            if (trasplanteEncontrado.getIdentificador() == identificadorDelTrasplante) {
                return trasplanteEncontrado;
            }
            i++;
        }

        return null;
    }

    /**
     * Retorna una cadena con el historial de todos los trasplantes realizados,
     * incluyendo identificador, órgano, donante y receptor de cada trasplante.
     *
     * @return Historial de trasplantes realizados en formato texto.
     */
    @Override
    public String listarTrasplantesRealizados() {
        int tamañoDeLaListaDeTrasplantesRealizados = this.listaDeTrasplantesRealizados.tamaño();
        int i = 0;
        StringBuilder pagina = new StringBuilder();

        pagina.append("-------------------- LISTA DE TRASPLANTES REALIZADOS --------------------\n");
        while (i < tamañoDeLaListaDeTrasplantesRealizados) {
            Trasplante trasplanteEncontrado = this.listaDeTrasplantesRealizados.obtener(i);
            String renglon = trasplanteEncontrado.toString();

            pagina.append(renglon);
            pagina.append("\n--------------------------------------------\n");
            i++;
        }

        return pagina.toString();
    }

}