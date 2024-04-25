/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author moasin
 */
public class NIFController {

    private static final char[] LETRAS_CONTROL = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
    private boolean isSaneado = false;

    public boolean isNifValid(String nif, boolean isEspañol) {
        if (!isValidLength(nif)) {
            System.out.println("Longitud del NIF/NIE no valida");
            return false;
        }

        if (isEspañol) {
            if (isValidStructureNIF(nif)) {
                return checkControlLetter(nif, true);
            }
            return false;
        } else {
            if (isValidStructureNIE(nif)) {
                return checkControlLetter(nif, false);
            }
        }

        return true;
    }

    private boolean isValidLength(String nif) {

        return nif != null && nif.length() == 9;
    }

    private boolean isValidStructureNIF(String nif) {
        if (!Character.isLetter(nif.charAt(nif.length() - 1))) {
            return false;
        }

        for (int i = 0; i < nif.length() - 1; i++) {
            if (!Character.isDigit(nif.charAt(i))) {
                return false;
            }
        }
        System.out.println("Es valida la estructura del NIF");
        return true;
    }

    private boolean isValidStructureNIE(String nif) {
        if (!Character.isLetter(nif.charAt(nif.length() - 1)) || !Character.isLetter(nif.charAt(0))) {
            return false;
        }

        for (int i = 1; i < nif.length() - 1; i++) {
            if (!Character.isDigit(nif.charAt(i))) {
                return false;
            }
        }
        System.out.println("Es valida la estructura del NIE extranjero");
        return true;
    }

    private boolean checkControlLetter(String nif, boolean español) {
        if (español) {

            char letraControlActual = nif.charAt(nif.length() - 1);
            String digits = nif.substring(0, nif.length() - 1);

            char letraControlReal = LETRAS_CONTROL[Integer.parseInt(digits) % 23];

            System.out.println(letraControlActual);
            System.out.println(letraControlReal);

            boolean isSameLetters = (letraControlActual == letraControlReal);

            if (isSameLetters) {
                System.out.println("Las letras son iguales no hayque hacer ni subsanar nada");
            } else {
                System.out.println("Las letras no son iguales  hayque subsanar ");

                String newNif = digits + letraControlReal;
                System.out.println("Nuevo nif: " + newNif);
            }

        } else {
            int firstLetterNum = getFirstLetterNIF(nif);
            char letraControlActual = nif.charAt(nif.length() - 1);
            String digits = firstLetterNum + nif.substring(1, nif.length() - 1);
            System.out.println("digits: " + digits);

            char letraControlReal = LETRAS_CONTROL[Integer.parseInt(digits) % 23];

            boolean isSameLetters = (letraControlActual == letraControlReal);

            if (isSameLetters) {
                System.out.println("Las letras son iguales no hayque hacer ni subsanar nada");
                return true;
            } else {
                System.out.println("Las letras no son iguales  hay que subsanar ");

                String newNie = digits + letraControlReal;
                System.out.println("Nuevo nif: " + newNie);
                return true;
            }   

        }

        System.out.println("Letra correcta no valida");
        return false;
    }

    public boolean getIsSaneado() {
        return this.isSaneado;
    }

    private int getFirstLetterNIF(String nif) {
        char firstLetra = nif.charAt(0);

        switch (firstLetra) {
            case 'X':
            case 'x':
                return 0;
            case 'Y':
            case 'y':
                return 1;
            case 'Z':
            case 'z':
                return 2;

        }
        return -1;

    }

    public boolean isSpanish(String nif) {
        boolean valifNIE = isValidStructureNIE(nif);
        boolean validNIF = isValidStructureNIF(nif);
        
        if(valifNIE){
            return false;
        }
        
        if(validNIF){
            return true;
        }
        
        
        return false;
    }

}
