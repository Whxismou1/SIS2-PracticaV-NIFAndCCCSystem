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
public class CCCController {

    private static int[] arrayPos = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};

    public void checkCCC(String ccc) {
        if (!isValidLengthCCC(ccc)) {
            System.out.println("Error CCC no se puede subsanar por la longitud");
            return;
        }

        int firstDigitOfControl = Integer.parseInt(String.valueOf(ccc.charAt(8)));
        int secondDigitOfControl = Integer.parseInt(String.valueOf(ccc.charAt(9)));
//        System.out.println(firstDigitOfControl + "" + secondDigitOfControl);
        StringBuilder sb = new StringBuilder("00");

        for (int i = 0; i < 8; i++) {
            sb.append(ccc.charAt(i));
        }
        int sum = 0;
        for (int i = 0; i < sb.length(); i++) {
            int num = Integer.parseInt(String.valueOf(sb.charAt(i)));
            sum += num * arrayPos[i];
        }
        int realFirstDigitOfControl = 11 - (sum % 11);

        sb.delete(0, sb.length());

        for (int i = 10; i < ccc.length(); i++) {
            sb.append(ccc.charAt(i));
        }

        sum = 0;
        for (int i = 0; i < sb.length(); i++) {
            int num = Integer.parseInt(String.valueOf(sb.charAt(i)));
            sum += num * arrayPos[i];
        }
        
        int temp = 11 - (sum % 11);
        int realSecondDigitOfControl;

        if (temp >= 11) {
            realSecondDigitOfControl = 0;
        } else if (temp >= 10) {
            realSecondDigitOfControl = 1;
        } else {
            realSecondDigitOfControl = temp;
        }

        System.out.println(realSecondDigitOfControl);
    }

    private boolean isValidLengthCCC(String ccc) {
        if (ccc.length() > 20 || ccc.length() < 20) {
            return false;
        }

        return true;
    }
}
