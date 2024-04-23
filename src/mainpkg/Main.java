/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Controllers.CCCController;
import Controllers.IBANController;
import Controllers.NIFController;
import Entities.Contribuyente;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author moasin
 */
public class Main {

    public static void main(String[] args) {
//        NIFController sc = new NIFController();
//        String nif = "72331034X";
//        sc.isNifValid(nif, true);
//        System.out.println("---------------");
//        String nif2 = "Y5127874S";
//        sc.isNifValid(nif2, false);
//        
        ExcelManager exc = new ExcelManager();

        List<Contribuyente> lista = exc.readEcel();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }

//        CCCController sc = new CCCController();
//        sc.checkCCC("11112223774444444444");
//        IBANController ib = new IBANController();
//        ib.checkIban("11112223504444444444", "ES");
    }
}
