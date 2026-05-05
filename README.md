# 💻 Proyecto BioQueue - Grupo D (4) 💻

# Sistema de Gestión de Trasplantes de Órganos por Consola

## Integrantes

Axel Ferreira, Valentín Guerrico, Ianai Canias y Thiago Soca

## Descripción del Proyecto

BioQueue es un sistema de gestión de trasplantes de órganos que permite administrar donantes, receptores y órganos disponibles, así como mantener un registro de los trasplantes que se han realizado, automatizando el proceso de asignación de órganos según compatibilidad sanguínea y prioridad médica.

## Funcionalidades

- Registro de donantes y sus órganos.
- Registro de receptores con lista de prioridad basada por prioridad de urgencia médica.
- Asignación automática de órganos según compatibilidad sanguínea y tipo de órgano.
- Historial de trasplantes realizados.
- Gestión de donantes, receptores, órganos y trasplantes realizados.
- Carga rápida de receptores y donantes mediante archivos CSV.
- Listado de receptores, donantes, órganos y trasplantes del sistema, pudiendo ser solicitado en todo momento.

## Estructura del Proyecto

- `classes/` - Entidades del dominio (DonanteImpl, ReceptorImpl, OrganoImpl, TrasplanteImpl, Persona).
  - `DonanteImpl` - Representa a una persona que dona un órgano al sistema.
  - `ReceptorImpl` - Representa a una persona que necesita un órgano y está en lista de espera.
  - `Persona` - Clase abstracta base que centraliza los atributos comunes de donantes y receptores.
  - `OrganoImpl` - Representa un órgano disponible para trasplante, asociado a su donante.
  - `TrasplanteImpl` - Registra el historial de cada trasplante realizado.
- `interfaces/` - Contratos de cada entidad del sistema.
- `services/` - Lógica de negocio.
  - `GestorDeOrganosImpl` - Administra los órganos disponibles.
  - `GestorDeReceptoresImpl` - Administra la lista de prioridad de receptores.
  - `GestorDeDonantesImpl` - Administra la lista de donantes del sistema.
  - `GestorDeTrasplantesImpl` - Orquesta la asignación de órganos y registra los trasplantes.
  - `ConsultorDeCompatibilidadSanguineaImpl` - Encapsula las reglas médicas de compatibilidad entre tipos de sangre de donante y receptor.
  - `LectorDeArchivosCSV` - Permite la carga masiva de receptores y donantes desde archivos CSV.
- `facade/` - Punto de entrada único al sistema (BioQueueFacade).
- `tda/` - Estructuras de datos propias (TDAListaEnlazada, TDAColaEnlazada).
- `Main` - Clase principal de funcionamiento del sistema.
- `ListaEnlazada, Nodo` - Clases de estructuras de datos implementadas.

## Tecnologías Utilizadas

- Java.
- Maven.
- JUnit 5.

## Diagrama de Clases - UML

![Diagrama de Clases UML](DiagramaDeClasesUMLBioQueue.png)

## Tabla de Compatibilidades Sanguíneas Utilizada

![Tabla de Compatibilidades Sanguíneas](TablaDeCompatibilidades.png)