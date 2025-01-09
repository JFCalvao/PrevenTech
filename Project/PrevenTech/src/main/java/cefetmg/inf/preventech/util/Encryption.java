/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

/**
 *
 * @author jfcalvao
 */
import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Security;
import java.util.Base64;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    private static final Logger logger = Logger.getLogger(Encryption.class.getName());
    
    static {
        if(Security.getProvider("BC") == null)
            Security.addProvider(new BouncyCastleProvider());
    }
    
    public static String encrypt(String data) throws EncryptationException {
        try {
            byte[] decodedKey = Base64.getDecoder().decode("OZs9mvp8TV3nLzn5K7ctYZk1UnWVM6U6tKHLzFR4N8A=");
            SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch(Exception e) {
            throw new EncryptationException();
        }
    }
    
    public static String decrypt(String encryptedData) throws EncryptationException {
        try {
            byte[] decodedKey = Base64.getDecoder().decode("OZs9mvp8TV3nLzn5K7ctYZk1UnWVM6U6tKHLzFR4N8A=");
            SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key); 

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData); 
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes); 

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch(Exception e) {
            throw new EncryptationException();
        }
    }
}
