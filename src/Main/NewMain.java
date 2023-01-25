/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author arsen
 */
public class NewMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
public class GeneratePlainPassword {

    public static final String AES = "AES";

    private  String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private  byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    
    /**
     * @param args the command line arguments
         * @throws java.io.FileNotFoundException
         * @throws java.security.NoSuchAlgorithmException
         * @throws javax.crypto.NoSuchPaddingException
         * @throws java.security.InvalidKeyException
         * @throws javax.crypto.IllegalBlockSizeException
         * @throws javax.crypto.BadPaddingException
     */
    public  void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // TODO code application logic here
                String tempkey = "";
        String password = "";
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("c:/keypassword.properties");
        // load a properties file
        prop.load(input);
        tempkey = prop.getProperty("Key");
        password = prop.getProperty("Encrypted_Password");

        byte[] bytekey = hexStringToByteArray(tempkey);
        SecretKeySpec sks = new SecretKeySpec(bytekey, GeneratePlainPassword.AES);
        Cipher cipher = Cipher.getInstance(GeneratePlainPassword.AES);
        cipher.init(Cipher.DECRYPT_MODE, sks);
        byte[] decrypted = cipher.doFinal(hexStringToByteArray(password));
        String OriginalPassword = new String(decrypted);
        System.out.println("****************  Original Password  ****************");
        System.out.println(OriginalPassword);
        System.out.println("****************  Original Password  ****************");

    }
    
}
}
