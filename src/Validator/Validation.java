/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.regex.Pattern;

/**
 *
 * @author junio
 */
public class Validation {
    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email == "")
            return false;
        return pat.matcher(email).matches();
    }
    
     public static String increment(String number) {
    char[] cars = number.toUpperCase().toCharArray();
    for (int i = cars.length - 1; i >= 0; i--) {
        if (cars[i] == '9') {
            cars[i] = '0';
        } else {
            cars[i]++;
            break;
        }
    }
    return String.valueOf(cars);
}

}
