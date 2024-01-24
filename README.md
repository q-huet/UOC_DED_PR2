# UOC Diseño de Estructuras de Datos
## PR2 - Centro de Tecnologia y Telecomunicaciones (CTT)

## Autor
- Enrique Huet Adrover
- ehuet@uoc.edu
- Semestre 1º - Curso 2023/2024

##  Abstracto
Este proyecto consiste en el diseño e implementación de una estructura de datos para la aplicación del Centro de Datos y Telecomunicaciones (CTT).

## Alcance
Practicas de la asignatura "Diseño de Estructuras de Datos" del Grado en Ingenieria Informatica de la UOC.

### 1. Introducción

En esta PR2 se han modificado ciertas estructuras proporcionadas en la solución de la PR1 ademas de añadir nuevas funcionalidades.
- Interfaces:
  - CTTCompaniesJobs
  - CTTCompaniesJobsPR2 que extiende CTTCompaniesJobs

- Implementaciones:
  - CTTCompaniesJobsImpl
  - CTTCompaniesJobsPR2Impl que extiende CTTCompaniesJobsImpl

- Test:
  - CTTCompaniesJobsPR1Test
  - CTTCCompaniesJobsPR2Test
  - CTTCCompaniesJobsPR2PlusTest

### 2. Modificaciones respecto a la PR1
- **CTTCompaniesJobsImpl**
- **Nuevas Estructuras de Datos**
   - workers: DictionaryAVLImpl sustituyendo el anterior DSArray 
   - jobOffers: DictionaryAVLImpl sustituyendo el anterior DSArray
   - companies: HashTable sustituyendo el anterior DSArray

 - **Metodos Revisados en la implementación**
   - No es necesario modificar ningún método de la implementación.

 - **Clases del Modelo Modificadas**
   - Se modifica jobOffer para integrar el tratamiento de las inscripciones de sustitutos. 


### 3. Nuevas caracteristicas de la PR2
- **CTTCompaniesJobsPR2Impl**
- **Nuevas Estructuras de Datos**
  - employees: HashTable
  - rooms: HashTable
  - equipments: DictionaryAVLImpl
  - roles: DSArray
  - socialNetwork: DirectedGraph
  - best5rooms: OrderedVector

- **Metodos añadidos en la implementación**
  - Se implementan todos los metodos que proporciona la interfaz.

- **Clases del Modelo Añadidas**
  - Employee
  - Equipment
  - Role
  - Room

- **Otras Clases del Modelo Añadidas**
  - LevelHelper : TDD Detectada al ejecutar los test.

- **Excepciones**
  - Siguiendo el metodo TDD se añaden todas las excepciones requeridas. 

### 4. Test y Validación

- Todos los test propuestos se superan con éxito.
- No se añaden nuevos test.

### 5. Dificultades

- Uno de los problemas encontrados durante la implementacion, ha sido la actualizacion del vector ordenado best5rooms cuando se asigna o reasigna un material a una sala.
Finalmente lo he resuelto con una solución poco elgante pero efectiva, no se devuelve el vector best5room, en su lugar se genera ad-hoc un  vector ordenado recorriendo todas las salas.
Se ha mantenido la estructura best5rooms aunque no se utiliza. 

### 6. Conclusiones
Se ha completado una practica bastante completa donde hemos podido modelar e implementar una solucion poniendo en practica todos los conceptos teoricos adquiridos a lo largo del semestre.
Sin duda la presente solucion tiene mucho margen de mejora y optimizacion pero creo que esta practica se puede considerar como satisfactoria y ha cumplido con sus objetivos a nivel curricular.

### 7. Anexos

Se adjunta pruebas graficas del resultado de los test ejecutados en mi equipo.
