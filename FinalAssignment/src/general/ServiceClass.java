/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

/**
 *
 * @author Branden
 */
public class ServiceClass {
    // validation 
    public static boolean stringChecker(String data){
        if(data.length() > 100){
            return true;
        } else {
            data = data.trim();
            return data.isEmpty();
        }
    }//End of stringChecker
}
