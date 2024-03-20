package com.baltinfo.radius.encrypt;

import java.io.Serializable;

public class Alphabet implements Serializable {

    static String alphabet;

    /**
     * Метод, возвращающий алфавит заглавных
     * английских букв
     *
     * @return Алфавит заглавных английских букв
     */
    public static String getEnglishU() {
        alphabet = "";
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращающий алфавит строчных
     * английских букв
     *
     * @return Алфавит строчных английских букв
     */
    public static String getEnglishL() {
        alphabet = "";
        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращающий алфавит заглавных
     * русских букв
     *
     * @return Алфавит заглавных русских букв
     */
    public static String getRussianU() {
        alphabet = "";
        for (char ch = 'А'; ch <= 'Я'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращающий алфавит строчных
     * русских букв
     *
     * @return алфавит строчных русских букв
     */
    public static String getRussianL() {
        alphabet = "";
        for (char ch = 'а'; ch <= 'я'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращающий строку цифр
     *
     * @return строку цифр
     */
    public static String getNumbers() {
        alphabet = "";
        for (char ch = '0'; ch <= '9'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращающий скобки и знаки препинания
     *
     * @return строка скобок и знаков препинания
     */
    public static String getSpecialCharacters() {
        alphabet = "";
        alphabet += "_";
        for (char ch = ':'; ch <= '?'; ch++) {
            alphabet += ch;
        }
        for (char ch = ' '; ch <= '/'; ch++) {
            alphabet += ch;
        }
        return alphabet;
    }

    /**
     * Метод, возвращает набор всех возможных символов
     *
     * @return строка всех символов
     */
    public static String getEverything() {
        alphabet = "";
        alphabet += Alphabet.getRussianL();
        alphabet += Alphabet.getRussianU();
        alphabet += Alphabet.getEnglishL();
        alphabet += Alphabet.getEnglishU();
        alphabet += Alphabet.getNumbers();
        alphabet += Alphabet.getSpecialCharacters();
        return alphabet;
    }

    /**
     * Метод, возвращает набор всех возможных символов
     *
     * @return строка всех символов
     */
    public static String getForURL() {
        alphabet = "";
        alphabet += Alphabet.getRussianL();
        alphabet += Alphabet.getRussianU();
        alphabet += Alphabet.getEnglishL();
        alphabet += Alphabet.getEnglishU();
        alphabet += Alphabet.getNumbers();
        alphabet += Alphabet.getSpecialCharacters();
        //System.out.println("alphabet = " + alphabet);
        return alphabet;
    }
}
