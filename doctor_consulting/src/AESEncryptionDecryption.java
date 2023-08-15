import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

/**
 * Java String Encryption Decryption Example
 * @author Ramesh Fadatare
 *
 */
public class AESEncryptionDecryption {
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";

    public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            prepareSecreteKey(secret);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public static AESEncryptionDecryption main() {
        final String secretKey = "secrete";

        Scanner ipnut= new Scanner(System.in);
        //String originalString = "hel";

        JFrame f=new JFrame();
        f.setTitle("Image Operation");
        f.setSize(800,800);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TextArea txtqq =new TextArea();
        txtqq.setBounds(10,10,100,100);
        txtqq.setText("Enter ");



        f.setLayout(new FlowLayout());
f.add(txtqq);
        f.setVisible(true);
        return null;
    }
}