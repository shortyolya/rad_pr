package com.baltinfo.radius.navigation;

import com.baltinfo.radius.encrypt.Algorithm;
import com.baltinfo.radius.encrypt.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Suvorina Aleksandra
 * @since 09.04.2018
 */
public class NavigationUtils {

    private static Logger logger = LoggerFactory.getLogger(NavigationUtils.class);

    private static SecurityUtil encryptor;

    static {
        encryptor = new SecurityUtil();
    }

    /**
     * Кодирование строки
     *
     * @param v
     * @return
     * @throws Throwable
     */
    public static String encrypt(String v) throws Throwable {
        logger.info("encrypt REQ_PARM = " + v);
        return v == null ? null : encryptor.toSecure(v);
    }

    /**
     * Кодирование строки
     *
     * @param v
     * @param alg - алгоритм шифрования
     * @return
     * @throws Throwable
     */
    public static String encrypt(String v, Algorithm alg) throws Throwable {
        logger.info("encrypt REQ_PARM = " + v);
        return v == null ? null : encryptor.toSecure(v, alg);
    }

    /**
     * Декодирование строки
     *
     * @param v
     * @return
     * @throws Throwable
     */
    public static Map<String, String> decrypt(String v) throws Throwable {
        logger.debug("befor decrypt REQ_PARM = " + v);
        if (v == null) {
            return new HashMap<>();
        }
        v = encryptor.fromSecure(v);
        logger.debug("after decrypt REQ_PARM = " + v);

        return getParmMap(v);
    }

    /**
     * Декодирование строки
     *
     * @param v   - строка
     * @param alg - алгоритм шифрования
     * @return
     * @throws Throwable
     */
    public static Map<String, String> decrypt(String v, Algorithm alg) throws Throwable {
        logger.debug("befor decrypt REQ_PARM = " + v);
        if (v == null) {
            return null;
        }
        v = encryptor.fromSecure(v, alg);
        logger.debug("after decrypt REQ_PARM = " + v);

        return getParmMap(v);
    }

    public static String decryptString(String v, Algorithm alg) throws Throwable {
        return encryptor.fromSecure(v, alg);
    }

    /**
     * Из строки формирует коллекцию Map объектов-параметров URL
     *
     * @param v
     * @return
     * @throws Throwable
     */
    private static Map<String, String> getParmMap(String v) throws Throwable {
        Map<String, String> m = new HashMap<>();

        StringTokenizer st = new StringTokenizer(v, "=;");
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            String value = st.nextToken();
            m.put(key, value);
        }
        return m;
    }

}
