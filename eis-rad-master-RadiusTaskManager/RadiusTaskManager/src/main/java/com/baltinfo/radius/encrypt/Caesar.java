package com.baltinfo.radius.encrypt;

import java.io.Serializable;

/**
 * @author lia
 */
public class Caesar implements Serializable {

    String alphabet;                // Алфавит, заданный пользователем
    private final int SHIFT = 3;    // Смещение

    /**
     * Конструктор класса
     *
     * @param _alphabet Алфавит шифрования
     */
    public Caesar(String _alphabet) {
        alphabet = _alphabet;
    }

    /**
     * Метод Цезаря шифрование текста
     *
     * @param text     Исходный текст
     * @param position Количество позиций для сдвига
     * @return Зашифрованный текст
     */
    public String getEncryption(String text, int position) {
        String cipher = "";
        for (int i = 0; i < text.length(); i++) {
            cipher += alphabet.charAt((alphabet.indexOf(text.charAt(i)) + position) % alphabet.length());
        }
        cipher += ";";
        return cipher;
    }

    /**
     * Метод Цезаря де шифрование текста
     *
     * @param cipher   Зашифрованный текст
     * @param position Количество позиций для сдвига
     * @return Исходный текст
     */
    public String getDecryption(String cipher, int position) {
        String text = "";
        for (int i = 0; i < cipher.length() - 1; i++) {
            if (alphabet.indexOf(cipher.charAt(i)) - position < 0) {
                text += alphabet.charAt(alphabet.length() - 1 +
                        (alphabet.indexOf(cipher.charAt(i)) - position + 1) % alphabet.length());
            } else {
                text += alphabet.charAt((alphabet.indexOf(cipher.charAt(i)) - position) % alphabet.length());
            }
        }
        return text;
    }

    /**
     * Метод Цезаря шифрование текста
     *
     * @param text Исходный текст
     * @return Зашифрованный текст
     */
    public String getEncryption(String text) {
        return this.getEncryption(text, SHIFT);
    }

    /**
     * Метод Цезаря де шифрование текста
     *
     * @param cipher Зашифрованный текст
     * @return Исходный текст
     */
    public String getDecryption(String cipher) {
        return this.getDecryption(cipher, SHIFT);
    }
}
