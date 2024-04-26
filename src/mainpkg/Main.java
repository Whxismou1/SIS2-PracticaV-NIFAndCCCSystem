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

    private static List<String> nifListas;

    public static void main(String[] args) {
        nifListas = new LinkedList<>();

        ExcelManager excManag = new ExcelManager();
        CCCController cccController = new CCCController();
        IBANController ibanCont = new IBANController();
        NIFController nifControler = new NIFController();

        List<Contribuyente> lista = excManag.readEcel();

        List<Contribuyente> buenNie = new LinkedList<>();
        List<Contribuyente> malNie = new LinkedList<>();

        for (int i = 0; i < lista.size(); i++) {
            Contribuyente actualContribuyyente = lista.get(i);
            String nif = actualContribuyyente.getNIFNIE();
            System.out.println("Nif acutal: " + nif);
            if (nif == null) {
                malNie.add(actualContribuyyente);
            } else {
                boolean isSpanish = nifControler.isSpanish(nif);
                if (nifControler.isNifValid(nif, isSpanish)) {

                    if (nifControler.getIsSaneado()) {
                        //Xml
                    }

                    String cccActual = actualContribuyyente.getCCC();

                    buenNie.add(actualContribuyyente);

                    cccController.checkCCC(cccActual);

                    ibanCont.checkIban(actualContribuyyente.getCCC(), actualContribuyyente.getPaisCCC());

                } else {

                    malNie.add(actualContribuyyente);
                }

            }

        }
//        System.out.println("Buen nie");
//        printList(buenNie);
//        System.out.println("--------------------------------------------");
//        System.out.println("Mal nie");
//        printList(malNie);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }

//        Contribuyente ol = lista.get(0);
//        NIFController sc = new NIFController();
//        sc.isNifValid(ol.getNIFNIE(), true);
//        
//        
//
//        CCCController ccc = new CCCController();
//        ccc.checkCCC(ol.getCCC());
//        IBANController ib = new IBANController();
//        ib.checkIban(ol.getCCC(), ol.getPaisCCC());
    }

    private static void printList(List<Contribuyente> waa) {
        for (int i = 0; i < waa.size(); i++) {
            Contribuyente get = waa.get(i);
            System.out.println(get.toString());

        }
    }
}
