/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Base64;
import javax.crypto.SecretKey;

/**
 *
 * @author jfcalvao
 */
public class DataManager {
    
    public static SQLData format(String[] params) throws Exception {
        SQLData formattedData = new SQLData();
        
        for(int i = 0; i < params.length; i++)
            formattedData.add(Encryption.encrypt(params[i]));
        
        return formattedData;
    }
    
    public static String[] unformat(SQLData encryptData) throws Exception {
        String[] params = new String[encryptData.size()];
        
        for(int i = 0; i < encryptData.size(); i++)
            params[i] = Encryption.decrypt(encryptData.get(i));
        
        return params;
    }
}
