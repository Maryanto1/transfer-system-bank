package id.co.com.transfer_system.core.config;

import id.co.com.transfer_system.core.exception.ProcessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AESCrypto {
    private static final String COMPONENT =
            "000000000000000湫-" +
            "000000000000000䴴-" +
            "000000000000000㍲-" +
            "000000000000000䭹-";
    private static String MASTER_KEY;
    private static final String ALG = "AES/ECB/PKCS5PADDING";
    private static final String ALGSEC = "AES";

    @Autowired
    AESCrypto() {
    }

    public static String encrypt(String value) throws ProcessException {
        if (MASTER_KEY == null) {
            try {
                getKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return encrypt(value, MASTER_KEY);
    }

    public static String encryptSalt(String value, String salt) throws ProcessException {
        if (MASTER_KEY == null) {
            try {
                getKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return encrypt(value, MASTER_KEY + salt);
    }

    public static String decrypt(String value) throws ProcessException {
        if (MASTER_KEY == null) {
            try {
                getKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return decrypt(value, MASTER_KEY);
    }

    public static String decryptSalt(String value, String salt) throws ProcessException {
        if (MASTER_KEY == null) {
            try {
                getKey();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return decrypt(value, MASTER_KEY + salt);
    }

    public static String encrypt(String value, String key) throws ProcessException {
        if (value == null || value.isEmpty()) return "";
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGSEC);

            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ProcessException("-999", "Invalid Chipper Encript");
        }
    }

    public static String decrypt(String encrypted, String key) throws ProcessException {
        if (encrypted == null || encrypted.isEmpty()) return "";
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGSEC);

            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProcessException("-999", "Invalid chiper Decrypt");
        }
    }

    public static void getKey() {
        StringBuilder sb = new StringBuilder();

        final String[] component = COMPONENT.split("-");
        for (int a=0; a<component.length; a++) {
            sb.append(component[a].charAt(component[a].length()-1));
        }
        String rawString = sb.toString();
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_16);
        MASTER_KEY = new String(bytes, StandardCharsets.UTF_8).substring(2);
    }

    public static void main(String[] args) {
        System.out.println(AESCrypto.encrypt("Test123"));
        //nkM43rKy <-key
    }
}
