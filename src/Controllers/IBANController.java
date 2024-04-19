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

    public void checkIban(String inputCCC, String country) {
        HashMap<Character, Integer> tableCountry = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char firstLetter = 'A';
//        tableCountry.put(firstLetter, 11);
        for (int i = 10; i < 36; i++) {
            tableCountry.put(firstLetter, i);
            firstLetter = (char) (firstLetter + 1);

        }

        System.out.println(tableCountry.toString());

//        System.out.println(sb.toString());
        int numOneCountry = tableCountry.get(country.charAt(0));
        int numTwoCountry = tableCountry.get(country.charAt(1));
        System.out.println(numOneCountry);
        System.out.println(numTwoCountry);

        sb.append(inputCCC + numOneCountry + numTwoCountry + "00");
        System.out.println(sb.toString());
        BigInteger bigInteger = new BigInteger(sb.toString());
        BigInteger remainder = bigInteger.mod(BigInteger.valueOf(97));
        System.out.println("Resultado del módulo 97: " + remainder);
    }

}
