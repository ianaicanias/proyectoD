package uy.edu.curso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.classes.ReceptorImpl;
import uy.edu.curso.interfaces.Receptor;


@DisplayName("Tests para la Clase ReceptorImpl")
public class ReceptorTest {

    private static Receptor receptor;

    @BeforeAll
    public static void inicializacion() {
        receptor = new ReceptorImpl("32532521", "Raúl Gómez", 
                "Riñón", "A+", (byte) 16, (byte) 80);
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests de la clase receptor finalizados. . .");
    }

    @Test
    @DisplayName("Asignar un Nuevo Puntaje de Prioridad al Receptor")
    public void setPuntajeDePrioridad_receptorCreadoYPuntajeDePrioridadValido_receptorConElNuevoPuntajeDePrioridadAsignado() {
        // Arrange
        byte puntajeDePrioridadEsperado = 40;

        // Act
        receptor.setPuntajeDePrioridad((byte) 40);

        // Assert
        assertEquals(puntajeDePrioridadEsperado, receptor.getPuntajeDePrioridad());
    }

    @Test
    @DisplayName("Asignar un Nuevo Nombre al Receptor")
    public void setNombre_receptorCreadoYNombreValido_receptorConElNuevoNombreAsignado() {
        // Arrange
        String nombreEsperado = "Juan Díaz";

        // Act
        receptor.setNombre("Juan Díaz");

        // Assert
        assertEquals(nombreEsperado, receptor.getNombre());
    }

    @Test
    @DisplayName("Asignar un Nuevo Tipo de Sangre al Receptor")
    public void setTipoDeSangre_receptorCreadoYTipoDeSangreValido_receptorConElNuevoTipoDeSangreAsignado() {
        // Arrange
        String tipoDeSangreEsperado = "O-";

        // Act
        receptor.setTipoDeSangre("O-");

        // Assert
        assertEquals(tipoDeSangreEsperado, receptor.getTipoDeSangre());
    }

    @Test
    @DisplayName("Asignar un Nuevo Tipo de Órgano Necesitado al Receptor")
    public void setTipoDeOrgano_receptorCreadoYTipoDeOrganoValido_receptorConElNuevoTipoDeOrganoAsignado() {
        // Arrange
        String tipoDeOrganoEsperado = "Corazón";

        // Act
        receptor.setTipoDeOrgano("Corazón");

        // Assert
        assertEquals(tipoDeOrganoEsperado, receptor.getTipoDeOrgano());
    }

    @Test
    @DisplayName("Asignar una Nueva Edad al Receptor")
    public void setEdad_receptorCreadoYEdadValida_receptorConLaNuevaEdadAsignada() {
        // Arrange
        byte edadEsperada = 89;

        // Act
        receptor.setEdad((byte) 89);

        // Assert
        assertEquals(edadEsperada, receptor.getEdad());
    }

}