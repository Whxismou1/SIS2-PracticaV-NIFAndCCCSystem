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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author moasin
 */
public class Main {

    public static void main(String[] args) {
//        
//        List<Contribuyente> lista = excManag.readEcel();

//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i).toString());
//        }
//        Contribuyente juan = lista.get(0);
//        Contribuyente ruben = lista.get(1);
//        
//        List<Contribuyente> waa = new ArrayList<>();
//        waa.add(juan);
//        waa.add(ruben);
//        for (int i = 0; i < waa.size(); i++) {
//            System.out.println(waa.get(i).toString());
//        }
//        
//        ErrorManager aa = new ErrorManager();
//        
//        aa.errorManagerCCC(lista);
        ExcelManager excManag = new ExcelManager();
        List<Contribuyente> listaContribuyentes = excManag.readEcel();
        List<Contribuyente> malNie = new LinkedList<>();
        List<Contribuyente> malCCC = new LinkedList<>();
        List<Contribuyente> nifNiesApariciones = new LinkedList<>();

        CCCController cccController = new CCCController();
        IBANController ibanCont = new IBANController();
        NIFController nifControler = new NIFController();
        ErrorManager errorManager = new ErrorManager();

        for (int i = 0; i < listaContribuyentes.size(); i++) {
            Contribuyente actualContribuyyente = listaContribuyentes.get(i);

            if(isEmptyContribuyente(actualContribuyyente)){
                continue;
            }
            
            String nifActual = actualContribuyyente.getNIFNIE();

            if (nifActual == null) {
                malNie.add(actualContribuyyente);
            } else {

                if (nifNiesApariciones.contains(nifActual)) {
                    malNie.add(actualContribuyyente);
                } else {
                    boolean isSpanish = nifControler.isSpanish(nifActual);

                    if (nifControler.isNifValid(nifActual, isSpanish, actualContribuyyente)) {
                        if (nifControler.getIsSaneado()) {
                            malNie.add(actualContribuyyente);
                            nifControler.clearSaneado();
                        }

                        //ccc y iban
                        String actualCCC = actualContribuyyente.getCCC();

                        cccController.checkCCC(actualCCC, malCCC, actualContribuyyente);

                        ibanCont.checkIban(actualContribuyyente);

                    } else {
                        malNie.add(actualContribuyyente);
                    }

                }
            }
        }
        
        errorManager.errorManagerCCC(malCCC);
        errorManager.errorManagerNIF(malNie);
        

//         for (int i = 0; i < malNie.size(); i++) {
//            System.out.println(malNie.get(i).toString());
//        }
//        System.out.println("---------------------------------");
//        
//        for (int i = 0; i < listaContribuyentes.size(); i++) {
//            System.out.println(listaContribuyentes.get(i).toString());
//        }
    }

//            }else {
//                boolean isSpanish = nifControler.isSpanish(nif);
//                if (nifControler.isNifValid(nif, isSpanish)) {
//
//                    if (nifControler.getIsSaneado()) {
//                        malNie.add(actualContribuyyente);
//                        //llamar a la funcion de cambiar nifnie
//                    }
//
//                    String cccActual = actualContribuyyente.getCCC();
//
//                    cccController.checkCCC(cccActual);
//
//                    ibanCont.checkIban(actualContribuyyente.getCCC(), actualContribuyyente.getPaisCCC());
//
//                } else {
//
//                    malNie.add(actualContribuyyente);
//                }
//
//            }
//
//        }
//        errorManager.ErrorManagerNIF(malNie);
////        System.out.println("Buen nie");
////        printList(buenNie);
////        System.out.println("--------------------------------------------");
////        System.out.println("Mal nie");
////        printList(malNie);
///*
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i).toString());
//        }
//*/
//
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
    private static void printList(List<Contribuyente> waa) {
        for (int i = 0; i < waa.size(); i++) {
            Contribuyente get = waa.get(i);
            System.out.println(get.toString());

        }
    }
    
    private static boolean isEmptyContribuyente(Contribuyente actual){
        
        if(actual.getNIFNIE() == null && actual.getCCC() == null && actual.getIBAN() == null){
            return true;
        }
        
        return false;
    }
    
    
}
