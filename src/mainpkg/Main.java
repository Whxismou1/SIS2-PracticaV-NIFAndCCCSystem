package mainpkg;

import Controllers.CCCController;
import Controllers.IBANController;
import Controllers.NIFController;
import Entities.Contribuyente;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
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

            if (isEmptyContribuyente(actualContribuyyente)) {
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

        excManag.writeExcel(listaContribuyentes);

    }

    private static boolean isEmptyContribuyente(Contribuyente actual) {

        if (actual.getNIFNIE() == null && actual.getCCC() == null && actual.getIBAN() == null) {
            return true;
        }

        return false;
    }

}
