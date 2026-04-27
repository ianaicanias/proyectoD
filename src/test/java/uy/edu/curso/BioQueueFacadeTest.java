package uy.edu.curso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import uy.edu.curso.facade.BioQueueFacade;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;
import uy.edu.curso.interfaces.Receptor;
import uy.edu.curso.interfaces.Trasplante;
import uy.edu.curso.tda.TDAListaEnlazada;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Tests para la Facade BioQueueFacade")
public class BioQueueFacadeTest {

    private static BioQueueFacade bioQueueFacade;

    @BeforeAll
    public static void inicializacion() {
        bioQueueFacade = BioQueueFacade.getInstancia();
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests de la Facade BioQueueFacade finalizados. . .");
    }

    @Test
    @Order(1)
    @DisplayName("Creación Correcta de la Instancia de la Facade BioQueueFacade")
    public void getInstancia_llamadaAGetInstancia_devuelveLaInstanciaConLosCuatroGestoresCreadosCorrectamente() {
        // Act
        BioQueueFacade bioQueueFacadeInstancia = BioQueueFacade.getInstancia();

        // Assert
        assertNotNull(bioQueueFacadeInstancia);
        assertNotNull(bioQueueFacadeInstancia.listarReceptores());
        assertNotNull(bioQueueFacadeInstancia.listarDonantes());
        assertNotNull(bioQueueFacadeInstancia.listarOrganosDisponibles());
        assertNotNull(bioQueueFacadeInstancia.listarTrasplantesRealizados());
    }

    @Test
    @Order(2)
    @DisplayName("Registrar Nuevo Receptor")
    public void registrarReceptor_datosDelReceptorCorrectos_devuelveElReceptorCreadoCorrectamente() {
        // Arrange
        String cedulaDeIdentidad = "48578321";
        String nombreDelReceptor = "Fernando Ávila";
        String tipoDeOrganoNecesitado = "Corazón";
        String tipoDeSangre = "A+";
        byte edad = 39;
        byte puntajeDePrioridad = 100;
    
        // Act
        Receptor receptorCreado = bioQueueFacade.registrarReceptor(cedulaDeIdentidad, nombreDelReceptor, tipoDeOrganoNecesitado, 
                tipoDeSangre, edad, puntajeDePrioridad);

        // Assert
        assertEquals(cedulaDeIdentidad, receptorCreado.getCedulaDeIdentidad());
        assertEquals(nombreDelReceptor, receptorCreado.getNombre());
        assertEquals(tipoDeOrganoNecesitado, receptorCreado.getTipoDeOrgano());
        assertEquals(tipoDeSangre, receptorCreado.getTipoDeSangre());
        assertEquals(edad, receptorCreado.getEdad());
        assertEquals(puntajeDePrioridad, receptorCreado.getPuntajeDePrioridad());
    }

    @Test
    @Order(3)
    @DisplayName("Registrar Nuevo Donante")
    public void registrarDonante_datosDelDonanteCorrectos_devuelveElDonanteCreadoCorrectamente() {
        // Arrange
        String cedulaDeIdentidad = "48578321";
        String nombreDelDonante = "Fernando Gonzales";
        String tipoDeOrganoDonado = "Corazón";
        String tipoDeSangre = "AB-";
        byte edad = 96;
    
        // Act
        Donante donanteCreado = bioQueueFacade.registrarDonante(cedulaDeIdentidad, nombreDelDonante, tipoDeOrganoDonado, 
                tipoDeSangre, edad);

        // Assert
        assertEquals(cedulaDeIdentidad, donanteCreado.getCedulaDeIdentidad());
        assertEquals(nombreDelDonante, donanteCreado.getNombre());
        assertEquals(tipoDeOrganoDonado, donanteCreado.getTipoDeOrgano());
        assertEquals(tipoDeSangre, donanteCreado.getTipoDeSangre());
        assertEquals(edad, donanteCreado.getEdad());
    }

    @Test
    @Order(4)
    @DisplayName("Buscar un Receptor Existente")
    public void buscarReceptor_receptorExistente_devuelveElReceptorBuscadoCorrectamente() {
        // Arrange
        String cedulaDeIdentidad = "21312336";
        String nombreDelReceptor = "Fernando Ávila";
        String tipoDeOrganoNecesitado = "Riñón";
        String tipoDeSangre = "A+";
        byte edad = 20;
        byte puntajeDePrioridad = 50;
        Receptor receptorCreado = bioQueueFacade.registrarReceptor(cedulaDeIdentidad, nombreDelReceptor, tipoDeOrganoNecesitado, 
                tipoDeSangre, edad, puntajeDePrioridad);

        // Act
        Receptor receptorEncontrado = bioQueueFacade.buscarReceptor(cedulaDeIdentidad);

        // Assert
        assertEquals(receptorCreado, receptorEncontrado);
    }

    @Test
    @Order(5)
    @DisplayName("Buscar un Donante Existente")
    public void buscarDonante_donanteExistente_devuelveElDonanteBuscadoCorrectamente() {
        // Arrange
        String cedulaDeIdentidad = "24234213";
        String nombreDelDonante = "Fernando Pereira";
        String tipoDeOrganoDonado = "Páncreas";
        String tipoDeSangre = "A-";
        byte edad = 23;
        Donante donanteCreado = bioQueueFacade.registrarDonante(cedulaDeIdentidad, nombreDelDonante, tipoDeOrganoDonado, 
                tipoDeSangre, edad);

        // Act
        Donante donanteEncontrado = bioQueueFacade.buscarDonante(cedulaDeIdentidad);

        // Assert
        assertEquals(donanteCreado, donanteEncontrado);
    }

    @Test
    @Order(6)
    @DisplayName("Buscar un Órgano Existente por Identificador")
    public void buscarOrganoPorIdentificador_organoExistente_devuelveElOrganoSolicitado() {
        // Arrange
        String cedulaDeIdentidad = "21421323";
        String nombreDelDonante = "Luis Pereira";
        String tipoDeOrganoDonado = "Riñón";
        String tipoDeSangre = "AB+";
        byte edad = 34;
        Donante donanteCreado = bioQueueFacade.registrarDonante(cedulaDeIdentidad, nombreDelDonante, tipoDeOrganoDonado, 
                tipoDeSangre, edad);
        Organo organoCreado = bioQueueFacade.buscarOrganosPorNombre(tipoDeOrganoDonado).obtener(0);

        // Act
        Organo organoEncontrado = bioQueueFacade.buscarOrganoPorIdentificador(organoCreado.getIdentificador());

        // Assert
        assertEquals(donanteCreado.getTipoDeOrgano(), organoEncontrado.getNombre());
        assertEquals(donanteCreado.getCedulaDeIdentidad(), organoEncontrado.getDonanteDelOrgano().getCedulaDeIdentidad());
        assertEquals(donanteCreado.getNombre(), organoEncontrado.getDonanteDelOrgano().getNombre());
        assertEquals(donanteCreado.getTipoDeSangre(), organoEncontrado.getTipoDeSangre());
        assertEquals(donanteCreado.getEdad(), organoEncontrado.getDonanteDelOrgano().getEdad());
    }

    @Test
    @Order(7)
    @DisplayName("Buscar Órganos por Nombre")
    public void buscarOrganosPorNombre_organoExistente_devuelveLaListaDeOrganosConEseNombre() {
        // Arrange
        Donante donante = bioQueueFacade.registrarDonante("11111111", "Juan Perez", 
                "Riñón", "A+", (byte) 30);

        // Act
        TDAListaEnlazada<Organo> resultado = bioQueueFacade.buscarOrganosPorNombre("Riñón");

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.tamaño() > 0);
    }

    @Test
    @Order(8)
    @DisplayName("Buscar Órganos por Tipo de Sangre")
    public void buscarOrganosPorTipoDeSangre_organoExistente_devuelveLaListaDeOrganosConEseTipoDeSangre() {
        // Arrange
        Donante donante = bioQueueFacade.registrarDonante("22222222", "Maria Lopez", 
                "Corazón", "B+", (byte) 25);

        // Act
        TDAListaEnlazada<Organo> resultado = bioQueueFacade.buscarOrganosPorTipoDeSangre("B+");

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.tamaño() > 0);
    }

    @Test
    @Order(9)
    @DisplayName("Eliminar Receptor Existente")
    public void eliminarReceptor_receptorExistente_eliminaElReceptorDeLaLista() {
        // Arrange
        Receptor receptor = bioQueueFacade.registrarReceptor("55555555", "Ana Martinez", 
                "Corazón", "O+", (byte) 50, (byte) 90);

        // Act
        bioQueueFacade.eliminarReceptor("55555555");
        Receptor receptorEncontrado = bioQueueFacade.buscarReceptor("55555555");

        // Assert
        assertNull(receptorEncontrado);
    }

    @Test
    @Order(10)
    @DisplayName("Eliminar Donante Existente")
    public void eliminarDonante_donanteExistente_eliminaElDonanteDeLaLista() {
        // Arrange
        Donante donante = bioQueueFacade.registrarDonante("66666666", "Luis Fernandez", 
                "Páncreas", "AB+", (byte) 45);

        // Act
        bioQueueFacade.eliminarDonante("66666666");
        Donante donanteEncontrado = bioQueueFacade.buscarDonante("66666666");

        // Assert
        assertNull(donanteEncontrado);
    }

    @Test
    @Order(11)
    @DisplayName("Eliminar Órgano Existente")
    public void eliminarOrgano_organoExistente_eliminaElOrganoDeLaLista() {
        // Arrange
        Donante donante = bioQueueFacade.registrarDonante("77777777", "Sofia Diaz", 
                "Hígado", "O-", (byte) 28);
        Organo organo = bioQueueFacade.buscarOrganosPorNombre("Hígado").obtener(0);

        // Act
        bioQueueFacade.eliminarOrgano(organo.getIdentificador());
        Organo organoEncontrado = bioQueueFacade.buscarOrganoPorIdentificador(organo.getIdentificador());

        // Assert
        assertNull(organoEncontrado);
    }

    @Test
    @Order(12)
    @DisplayName("Insertar Receptor en la Cola de Prioridad")
    public void insertarReceptorEnLaCola_receptorRegistrado_seInsertaCorrectamenteEnLaCola() {
        // Arrange
        Receptor receptorCreado = bioQueueFacade.registrarReceptor("99999999", "Juan Perez", 
                "Riñón", "A+", (byte) 30, (byte) 80);

        // Act
        bioQueueFacade.insertarReceptorEnLaCola(receptorCreado);

        // Assert
        assertNotNull(bioQueueFacade.listarReceptores());
    }

    @Test
    @Order(13)
    @DisplayName("Buscar un Trasplante Existente")
    public void buscarTrasplante_trasplanteExistente_devuelveElTrasplanteSolicitado() {
        // Arrange
        Receptor receptor = bioQueueFacade.registrarReceptor("11111111", "Juan Perez", 
                "Riñón", "A+", (byte) 30, (byte) 80);
        bioQueueFacade.insertarReceptorEnLaCola(receptor);
        bioQueueFacade.registrarDonante("96465787", "Maria Lopez", 
                "Riñón", "A+", (byte) 25);
        Donante donante2 = bioQueueFacade.registrarDonante("27372647", "Leandro Lopez", 
                "Riñón", "A+", (byte) 25);

        // Act
        Trasplante trasplanteEncontrado = null;

        for (int i = 1; i <= 10; i++) {
            Trasplante trasplanteObtenido = bioQueueFacade.buscarTrasplante(i);
            
            if (trasplanteObtenido != null && trasplanteObtenido.getDonanteDelOrganoDelTrasplante().equals(donante2)) {
                trasplanteEncontrado = trasplanteObtenido;
                break;
            }
        }

        // Assert
        assertNotNull(trasplanteEncontrado);
        assertEquals(receptor, trasplanteEncontrado.getReceptorDelTrasplante());
        assertEquals(donante2, trasplanteEncontrado.getDonanteDelOrganoDelTrasplante());
    }

}