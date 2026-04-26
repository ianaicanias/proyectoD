package uy.edu.curso.facade;

import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.GestorDeDonantes;
import uy.edu.curso.interfaces.GestorDeOrganos;
import uy.edu.curso.interfaces.GestorDeReceptores;
import uy.edu.curso.interfaces.GestorDeTransplantes;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Transplante;
import uy.edu.curso.services.GestorDeDonantesImpl;
import uy.edu.curso.services.GestorDeOrganosImpl;
import uy.edu.curso.services.GestorDeReceptoresImpl;
import uy.edu.curso.services.GestorDeTransplantesImpl;
import uy.edu.curso.tda.TDAColaEnlazada;
import uy.edu.curso.tda.TDAListaEnlazada;


public class BioQueueFacade {

    private static BioQueueFacade instancia;
    private final GestorDeReceptores gestorDeReceptores;
    private final GestorDeDonantes gestorDeDonantes;
    private final GestorDeOrganos gestorDeOrganos;
    private final GestorDeTransplantes gestorDeTransplantes;

    private BioQueueFacade() {
        this.gestorDeReceptores = new GestorDeReceptoresImpl();
        this.gestorDeDonantes = new GestorDeDonantesImpl();
        this.gestorDeOrganos = new GestorDeOrganosImpl();
        this.gestorDeTransplantes = new GestorDeTransplantesImpl();
    }

    public static BioQueueFacade getInstancia() {
        if (instancia == null) {
            instancia = new BioQueueFacade();
        }

        return instancia;
    }

    public Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado, 
            String tipoDeSangre, byte edad, byte puntajeDePrioridad) {
        return this.gestorDeReceptores.registrarReceptor(cedulaDeIdentidad, nombre, tipoDeOrganoNecesitado, 
                tipoDeSangre, edad, puntajeDePrioridad);
    }

    public void insertarReceptorEnLaCola(Receptor receptor) {
        this.gestorDeReceptores.insertarReceptorEnLaCola(receptor);
    }

    public Donante registrarDonante(String cedulaDeIdentidad, String nombre, String tipoDeOrganoDonado, String tipoDeSangre, byte edad) {
        Donante nuevoDonante = this.gestorDeDonantes.registrarDonante(cedulaDeIdentidad, nombre, tipoDeOrganoDonado, tipoDeSangre, edad);
        this.registrarOrgano(tipoDeOrganoDonado, nuevoDonante);

        return nuevoDonante;
    }

    private void registrarOrgano(String nombreDelOrgano, Donante donanteDelOrgano) {
        Organo nuevoOrgano = this.gestorDeOrganos.registrarOrgano(nombreDelOrgano, donanteDelOrgano);
        TDAListaEnlazada<Receptor> listaDeReceptores = this.gestorDeReceptores.getListaDeReceptores();
        TDAColaEnlazada<Receptor> colaDePrioridadDeReceptores = this.gestorDeReceptores.getColaDePrioridadDeReceptores();
        TDAListaEnlazada<Organo> listaDeOrganosDisponibles = this.gestorDeOrganos.getListaDeOrganosDisponibles();

        this.gestorDeTransplantes.asignarOrganoAReceptor(nuevoOrgano, listaDeReceptores, 
                colaDePrioridadDeReceptores, listaDeOrganosDisponibles);
    }

    public Receptor buscarReceptor(String cedulaDeIdentidad) {
        return this.gestorDeReceptores.buscarReceptor(cedulaDeIdentidad);
    }

    public Donante buscarDonante(String cedulaDeIdentidad) {
        return this.gestorDeDonantes.buscarDonante(cedulaDeIdentidad);
    }

    public Organo buscarOrganoPorIdentificador(long identificadorDelOrgano) {
        return this.gestorDeOrganos.buscarOrganoPorIdentificador(identificadorDelOrgano);
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorNombre(String nombreDelOrgano) {
        return this.gestorDeOrganos.buscarOrganosPorNombre(nombreDelOrgano);
    }

    public TDAListaEnlazada<Organo> buscarOrganosPorTipoDeSangre(String tipoDeSangre) {
        return this.gestorDeOrganos.buscarOrganosPorTipoDeSangre(tipoDeSangre);
    }

    public Transplante buscarTransplante(long identificadorDelTransplante) {
        return this.gestorDeTransplantes.buscarTransplante(identificadorDelTransplante);
    }

    public void eliminarReceptor(String cedulaDelReceptor) {
        this.gestorDeReceptores.eliminarReceptor(cedulaDelReceptor);
    }

    public void eliminarDonante(String cedulaDelDonante) {
        this.gestorDeDonantes.eliminarDonante(cedulaDelDonante);
    }

    public void eliminarOrgano(long identificadorDelOrgano) {
        this.gestorDeOrganos.eliminarOrgano(identificadorDelOrgano);
    }

    public String listarReceptores() {
        return this.gestorDeReceptores.listarReceptores();
    }

    public String listarDonantes() {
        return this.gestorDeDonantes.listarDonantes();
    }

    public String listarOrganosDisponibles() {
        return this.gestorDeOrganos.listarOrganosDisponibles();
    }

    public String listarTransplantesRealizados() {
        return this.gestorDeTransplantes.listarTransplantesRealizados();
    }

}