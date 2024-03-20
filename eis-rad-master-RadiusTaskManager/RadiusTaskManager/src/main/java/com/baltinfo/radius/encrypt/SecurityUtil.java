package com.baltinfo.radius.encrypt;

// Убрал шифрование в проекте, но класс оставил. Чтобы вернуть дым в трубу
// нужно раскомментить импорт Base64 и его использование в классе
// import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

/**
 * Шифрование пароля
 *
 * @author bsa
 */
public class SecurityUtil implements Serializable {

    /* Ключ для Base64 */
    private String key = "CoOLOvjRttvmHDVtmt04mw==";

    /* Алгоритм шифрования, используемый по-умолчанию */
    private Algorithm mode = Algorithm.CAESAR;

    public String fromSecure(String str, Algorithm alg)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeySpecException,
            IllegalBlockSizeException, BadPaddingException,
            FileNotFoundException, CertificateException, NoSuchProviderException {
        String value = null;
        if (alg == Algorithm.BASE64) {
            Charset cUtf8 = Charset.forName("UTF-8");
            Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
            byte[] s64bytes = str.getBytes(cUtf8);

            // bsa 30.05.2011: Раскомментить вместо нижней строчки byte[] secureBytes = Base64.decode(new String(s64bytes));
            byte[] secureBytes = str.getBytes(cUtf8);

            byte[] valueBytes = cipher.doFinal(secureBytes);
            value = new String(valueBytes, cUtf8);
        } else if (alg == Algorithm.CAESAR) {
            //Создать объект класса шифр Цезаря над данным алфавитом
            // igl 18_07_2012 - подключаем русские буквы
            // Caesar caesar = new Caesar(Alphabet.getForURL());
            Caesar caesar = new Caesar(Alphabet.getEverything());
            // Расшифровать строку
            if (str != null) {
                String hex = str.toLowerCase();
                byte[] bytes = new byte[hex.length() / 2];
                try {
                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
                    }

                    str = new String(bytes, "UTF-8");
                } catch (Exception ex) {
                    value = null;
                }
            }
            value = caesar.getDecryption(str);
        } else if (alg == Algorithm.NONE) {
            value = str;
        }
        return value;
    }

    public String toSecure(String str, Algorithm alg)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException, InvalidKeySpecException,
            FileNotFoundException, CertificateException,
            NoSuchProviderException, ShortBufferException {
        String value = null;
        if (alg == Algorithm.BASE64) {
            Charset cUtf8 = Charset.forName("UTF-8");
            Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
            byte[] valueBytes = str.getBytes(cUtf8);
            byte[] secureBytes = cipher.doFinal(valueBytes);
            // bsa 30.05.2011: Раскомментить вместо нижней строчки byte[] s64bytes = Base64.encode(secureBytes).getBytes();
            byte[] s64bytes = cipher.doFinal(valueBytes);

            value = new String(s64bytes, cUtf8);
        } else if (alg == Algorithm.CAESAR) {
            //Создать объект класса шифр Цезаря над данным алфавитом
            // igl 18_07_2012 - подключаем русские буквы
            // Caesar caesar = new Caesar(Alphabet.getForURL());
            Caesar caesar = new Caesar(Alphabet.getEverything());
            // Зашифровать строку
            String tmp = caesar.getEncryption(str);
            if (tmp != null) {
                try {
                    value = String.format("%x", new BigInteger(tmp.getBytes("utf-8"))).toUpperCase();
                } catch (Exception ex) {
                    value = null;
                }
            }
        } else if (alg == Algorithm.NONE) {
            value = str;
        }
        return value;
    }

    public String fromSecure(String str)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeySpecException,
            IllegalBlockSizeException, BadPaddingException,
            FileNotFoundException, CertificateException, NoSuchProviderException {
        return this.fromSecure(str, mode);
    }

    public String toSecure(String str)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException,
            InvalidKeyException, InvalidKeySpecException,
            FileNotFoundException, CertificateException,
            NoSuchProviderException, ShortBufferException {
        return this.toSecure(str, mode);
    }

    private Cipher getCipher(int mode)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, NoSuchProviderException {
        Charset cUtf8 = Charset.forName("UTF-8");
        Cipher cipher = Cipher.getInstance("AES");
//        byte[] key64Bytes = key.getBytes(cUtf8);
        // bsa 30.05.2011: Раскомментить вместо нижней строчки byte[] keyBytes = Base64.decode(new String(key64Bytes));
        byte[] keyBytes = key.getBytes(cUtf8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        cipher.init(mode, keySpec);
        return cipher;
    }
}
