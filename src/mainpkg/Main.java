/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Controllers.CCCController;
import Controllers.IBANController;
import Controllers.NIFController;

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
//        ExcelManager exc = new ExcelManager();
//        exc.readEcel();

//        CCCController sc = new CCCController();
//        sc.checkCCC("11112223774444444444");

        IBANController ib = new IBANController();
        ib.checkIban("11112223774444444444", "ES");

    }
}
