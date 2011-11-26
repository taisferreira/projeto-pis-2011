/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Fabricio
 */
public class Misc {
    public static String getDigitos(String str){
        StringBuffer digitos = new StringBuffer();
        char temp;
        for(int i=0; i<str.length(); i++){
            temp = str.charAt(i);
            if(Character.isDigit(temp)){
                digitos.append(str.charAt(i));
            }
        }
        return new String(digitos);
    }
}
