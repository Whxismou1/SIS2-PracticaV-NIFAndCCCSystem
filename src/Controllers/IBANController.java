/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.math.BigInteger;
import java.util.HashMap;

/**
 *
 * @author moasin
 */
public class IBANController {

    public String checkIban(String inputCCC, String country) {
        HashMap<Character, Integer> tableCountry = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char firstLetter = 'A';
        for (int i = 10; i < 36; i++) {
            tableCountry.put(firstLetter, i);
            firstLetter = (char) (firstLetter + 1);

        }

        int numOneCountry = tableCountry.get(country.charAt(0));
        int numTwoCountry = tableCountry.get(country.charAt(1));
        System.out.println(numOneCountry);
        System.out.println(numTwoCountry);

        sb.append(inputCCC + numOneCountry + numTwoCountry + "00");

        String iban = sb.toString().trim();
        BigInteger codIban = new BigInteger(iban);

        BigInteger divisor = new BigInteger("97");
        BigInteger resto = codIban.mod(divisor);
        
        int diferencia = 98 - resto.intValue();
        System.out.println("difenrecia: " + diferencia);

        String numDiferencia = String.valueOf(diferencia);
        String newNum = "";

        if (numDiferencia.length() == 2) {
            System.out.println("2 cifras");
            newNum += numDiferencia;
        } else {
            newNum += "0" + numDiferencia;
        }

        System.out.println("new num:" + newNum);

        sb.delete(0, sb.length());
        sb.append(country + newNum + inputCCC);
        System.out.println(sb.toString());

        return sb.toString();
    }

}
