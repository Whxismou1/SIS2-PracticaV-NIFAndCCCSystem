/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Entities.Contribuyente;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author moasin
 */
public class ExcelManager {

    public void writeExcel(List<Contribuyente> listaContribuyentes, int fila, int columna, String dato){
        String filePath = "resources/SistemasAgua.xlsx";
        try {
            // Cargar el archivo Excel existente
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int rowNum = fila;
            int cellNum = columna;
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.getCell(cellNum);
            if (cell == null) {
                cell = row.createCell(cellNum);
            }

            cell.setCellValue(dato);

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);

            fileOutputStream.close();
            workbook.close();

            System.out.println("Se escribió " + dato + " en la fila " + fila + " y columna " + columna + ".");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public List<Contribuyente> readEcel() {
        long startTime = System.currentTimeMillis();
        FileInputStream f = null;
        XSSFWorkbook libro = null;
        List<Contribuyente> entradasExcel = new ArrayList<>();
        try {
            f = new FileInputStream("resources/SistemasAgua.xlsx");
            libro = new XSSFWorkbook(f);
            XSSFSheet hoja = libro.getSheetAt(0);

            Row encabezado = hoja.getRow(0);

            if (encabezado != null) {
                List<String> nombresColumnas = new ArrayList<>();
                Iterator<Cell> celdasEncabezado = encabezado.cellIterator();
                while (celdasEncabezado.hasNext()) {
                    Cell celda = celdasEncabezado.next();
                    nombresColumnas.add(celda.getStringCellValue());
                }

                Iterator<Row> filas = hoja.iterator();
                filas.next(); // Saltar la primera fila (encabezado)
                Long actualId = 2L;
                while (filas.hasNext()) {
                    Row fila = filas.next();
                    Iterator<Cell> celdas = fila.cellIterator();
                    Contribuyente contribuyente = new Contribuyente();
                    contribuyente.setId(actualId);
                    // Recorremos las celdas de la fila
                    while (celdas.hasNext()) {
                        Cell celda = celdas.next();
                        int indiceCelda = celda.getColumnIndex();
                        String nombreColumna = nombresColumnas.get(indiceCelda);
                        asignarValorContribuyente(contribuyente, nombreColumna, celda);
                    }

                    // Agregar el contribuyente a la lista
                    entradasExcel.add(contribuyente);
                    actualId++;

                }
            }

//            return entradas;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (libro != null) {
                    libro.close();
                }
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Tiempo de lectura del archivo: " + duration + " milisegundos");
        }
        return entradasExcel;
    }

    private String getCellValue(Cell celda) {
        if (celda == null) {
            return null;
        }

        switch (celda.getCellType()) {
            case STRING:
                String value = celda.getStringCellValue();
                return value.isEmpty() ? null : value;
            case NUMERIC:
                return String.valueOf(celda.getNumericCellValue());
            case BLANK:
                return null;
            default:
                return null;
        }

    }

    private void asignarValorContribuyente(Contribuyente contribuyente, String nombreColumna, Cell celda) {
        switch (nombreColumna) {
            case "Nombre":
                // Manejar el campo Nombre
                contribuyente.setNombre(getCellValue(celda));
                break;
            case "Apellido1":
                // Manejar el campo Apellido1
                contribuyente.setApellido1(getCellValue(celda));
                break;
            case "Apellido2":
                // Manejar el campo Apellido2
                contribuyente.setApellido2(getCellValue(celda));
                break;
            case "NIFNIE":
                // Manejar el campo NIFNIE
                contribuyente.setNIFNIE(getCellValue(celda));
                break;
            case "Direccion":
                // Manejar el campo Direccion
                contribuyente.setDireccion(getCellValue(celda));
                break;
            case "Numero":
                // Manejar el campo Numero
                contribuyente.setNumero(getCellValue(celda));
                break;
            case "PaisCCC":
                // Manejar el campo PaisCCC
                contribuyente.setPaisCCC(getCellValue(celda));
                break;
            case "CCC":
                // Manejar el campo CCC
                contribuyente.setCCC(getCellValue(celda));
                break;
            case "IBAN":
                // Manejar el campo IBAN
                contribuyente.setIBAN(getCellValue(celda));
                break;
            case "Email":
                // Manejar el campo Email
                contribuyente.setEmail(getCellValue(celda));
                break;
            case "Exencion":
                // Manejar el campo Exencion
                contribuyente.setExencion(getCellValue(celda));
                break;
            case "Bonificacion":
                // Manejar el campo Bonificacion
                contribuyente.setBonificacion(getCellValue(celda));
                break;
            case "LecturaAnterior":
                // Manejar el campo LecturaAnterior
                contribuyente.setLecturaAnterior(getCellValue(celda));
                break;
            case "LecturaActual":
                // Manejar el campo LecturaActual
                contribuyente.setLecturaActual(getCellValue(celda));
                break;
            case "FechaAlta":
                // Manejar el campo FechaAlta
                contribuyente.setFechaAlta(getCellValue(celda));
                break;
            case "FechaBaja":
                // Manejar el campo FechaBaja
                contribuyente.setFechaBaja(getCellValue(celda));
                break;
            case "conceptosACobrar":
                // Manejar el campo conceptosACobrar
                contribuyente.setConceptosACobrar(getCellValue(celda));
                break;
        }
    }

}
