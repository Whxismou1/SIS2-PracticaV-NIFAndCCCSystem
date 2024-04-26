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

/**
 *
 * @author moasin
 */
public class Main {
    public static void main(String[] args) {
        ExcelManager excManag = new ExcelManager();
        CCCController cccController = new CCCController();
        IBANController ibanCont = new IBANController();
        NIFController nifControler = new NIFController();
        ErrorManager errorManager = new ErrorManager();

        List<Contribuyente> lista = excManag.readEcel();
        List<Contribuyente> malNie = new LinkedList<>();
        for (int i = 0; i < lista.size(); i++) {
            Contribuyente actualContribuyyente = lista.get(i);
            String nif = actualContribuyyente.getNIFNIE();
            if (nif == null) {
                malNie.add(actualContribuyyente);
            } else {
                boolean isSpanish = nifControler.isSpanish(nif);
                if (nifControler.isNifValid(nif, isSpanish)) {

                    if (nifControler.getIsSaneado()) {
                        malNie.add(actualContribuyyente);
                        //llamar a la funcion de cambiar nifnie
                    }

                    String cccActual = actualContribuyyente.getCCC();

                    cccController.checkCCC(cccActual);

                    ibanCont.checkIban(actualContribuyyente.getCCC(), actualContribuyyente.getPaisCCC());

                } else {

                    malNie.add(actualContribuyyente);
                }

            }

        }
        errorManager.ErrorManagerNIF(malNie);
//        System.out.println("Buen nie");
//        printList(buenNie);
//        System.out.println("--------------------------------------------");
//        System.out.println("Mal nie");
//        printList(malNie);
/*
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
        }
*/

        Contribuyente ol = lista.get(0);
        NIFController sc = new NIFController();
        sc.isNifValid(ol.getNIFNIE(), true);
        
        

        CCCController ccc = new CCCController();
        ccc.checkCCC(ol.getCCC());
        IBANController ib = new IBANController();
        ib.checkIban(ol.getCCC(), ol.getPaisCCC());
    }

    private static void printList(List<Contribuyente> waa) {
        for (int i = 0; i < waa.size(); i++) {
            Contribuyente get = waa.get(i);
            System.out.println(get.toString());

        }
    }
}
