package uy.edu.curso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uy.edu.curso.classes.DonanteImpl;
import uy.edu.curso.classes.OrganoImpl;
import uy.edu.curso.interfaces.Donante;
import uy.edu.curso.interfaces.Organo;


@DisplayName("Tests para la Clase OrganoImpl")
public class OrganoTest {

    private static Donante donante;
    private static Organo organo;

    @BeforeAll
    public static void inicializacion() {
        donante = new DonanteImpl("39243929", "Gonzalo Pereira", 
                "Riñón", "A+", (byte) 16);
        organo = new OrganoImpl(donante.getTipoDeOrgano(), donante);
        System.out.println("Inicializando los tests. . .");
    }

    @AfterAll
    public static void finalizacion() {
        System.out.println("Tests de la clase órgano finalizados. . .");
    }

    @Test
    @DisplayName("Asignar un Nuevo Nombre al Órgano")
    public void setNombre_organoCreadoYNombreCorrecto_organoConElNuevoNombreAsignado() {
        // Arrange
        String nombreEsperado = "Páncreas";

        // Act
        organo.setNombre("Páncreas");

        // Assert
        assertEquals(nombreEsperado, organo.getNombre());
    }

    @Test
    @DisplayName("Asignar un Nuevo Tipo de Sangre al Órgano")
    public void setTipoDeSangre_organoCreadoYTipoDeSangreCorrecto_organoConElNuevoTipoDeSangreAsignado() {
        // Arrange
        String tipoDeSangreEsperado = "AB+";

        // Act
        organo.setTipoDeSangre("AB+");

        // Assert
        assertEquals(tipoDeSangreEsperado, organo.getTipoDeSangre());
    }

}