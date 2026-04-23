package uy.edu.curso.interfaces;


public interface GestorDeReceptores {

    Receptor registrarReceptor(String cedulaDeIdentidad, String nombre, String tipoDeOrganoNecesitado,
            String tipoDeSangre, byte edad, byte puntajeDePrioridad);

    void insertarReceptorEnLaCola(Receptor receptor);

    Receptor buscarReceptor(String cedulaDeIdentidad);

    void eliminarReceptor(String cedulaDeIdentidad);

    String listarReceptores();

}