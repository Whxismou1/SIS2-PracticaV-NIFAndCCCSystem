/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Controllers.NIFController;

/**
 *
 * @author moasin
 */
public class Main {

    public static void main(String[] args) {
        NIFController sc = new NIFController();
        String nif = "72331034X";
        sc.isNifValid(nif, true);
        System.out.println("---------------");
        String nif2 = "X3465715D";
        sc.isNifValid(nif2, false);
        
        ExcelManager exc = new ExcelManager();
        exc.readEcel();
        
    }
}
