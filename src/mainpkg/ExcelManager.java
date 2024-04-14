/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author moasin
 */
public class ExcelManager {

    public void readEcel() {
        long startTime = System.currentTimeMillis();
        FileInputStream f = null;
        XSSFWorkbook libro = null;
        try {
            f = new FileInputStream("resources/SistemasAgua.xlsx");
            libro = new XSSFWorkbook(f);
            XSSFSheet hoja = libro.getSheetAt(0);

            Iterator<Row> filas = hoja.iterator();
            Iterator<Cell> celdas;

            Row fila;
            Cell celda;

            while (filas.hasNext()) {
                fila = filas.next();
                celdas = fila.cellIterator();

                while (celdas.hasNext()) {
                    celda = celdas.next();
                    System.out.println(celda);
                    switch (celda.getCellType()) {

                    }

                }

            }

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
    }
}
