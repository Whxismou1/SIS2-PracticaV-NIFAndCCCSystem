#  SIS 2: Práctica 5 Sistema de comprobacion de NIE, CCC & IBAN

## Descripción

Esta es la segunda practica de Java y la quinta práctica de la asignatura Sistemas de la Información 2 de tercer curso. En esta práctica, se utiliza Java para leer datos desde un archivo Excel y realizar varias operaciones de validación y corrección de datos.

El programa:

1. Lee los datos de un archivo Excel llamado [`SistemasAgua.xlsx`](src/resources/SistemasAgua.xlsx).
2. Obtiene todos los contribuyentes del Excel y comprueba si el DNI/NIF está bien.
3. Comprueba el CCC.
4. Genera el IBAN.
5. Guarda en listas correspondientes los contribuyentes con errores en el DNI/NIF o CCC.
6. Genera dos archivos XML ([`ErroresNifNie.xml`](src/resources/ErroresNifNie.xml) y [`ErroresCCC.xml`](src/resources/ErroresCCC.xml)), con los contribuyentes que tienen errores.
7. Una vez finaliza el programa, introduce en el Excel los contribuyentes corregidos.
## Requisitos

- Java 8 o superior
- Apache POI (para manipular archivos Excel)
- Una librería para generar archivos XML (por ejemplo, JAXB)

## Instalación

1. Clona este repositorio:
   ```sh
   git clone https://github.com/Whxismou1/SIS2-PracticaV-NIFAndCCCSystem.git
   ```
2. Importa el proyecto en tu IDE de preferencia (por ejemplo, IntelliJ IDEA o Eclipse).
