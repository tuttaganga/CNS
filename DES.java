import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {
    private Cipher cipher;
    private SecretKey secretKey;

    // Constructor to initialize Cipher and generate a SecretKey
    public DES() throws Exception {
        this.cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // DES key size is 56 bits
        this.secretKey = keyGen.generateKey();
    }

    // Encryption method
    public String encrypt(String plaintext) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decryption method
    public String decrypt(String encryptedText) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Main method
    public static void main(String[] args) {
        try {
            DES des = new DES();
            
            String plaintext = "Hello, DES!";
            System.out.println("Original Text: " + plaintext);
            
            // Encrypt
            String encryptedText = des.encrypt(plaintext);
            System.out.println("Encrypted Text: " + encryptedText);
            
            // Decrypt
            String decryptedText = des.decrypt(encryptedText);
            System.out.println("Decrypted : " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
