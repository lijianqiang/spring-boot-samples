package com.openplan.server.util;

import java.util.Random;

public class RandomUtils {

    /** 随机数对象 */
    private static final Random random = new Random();
    /** 数字与字母字典 */
    private static final char[] LETTER_AND_DIGIT = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    /** 数字与字母字典长度 */
    private static final int LETTER_AND_DIGIT_LENGTH = LETTER_AND_DIGIT.length;
    /** 数字字典 */
    private static final char[] DIGIT_CHARS = ("0123456789").toCharArray();
    /** 数字字典长度 */
    private static final int DIGIT_CHARS_LENGTH = DIGIT_CHARS.length;

    public static int getInt(int min, int max) {
        Random random = new Random(System.currentTimeMillis());
        int temp = random.nextInt(max) % (max - min + 1) + min;
        return temp;
    }

    public static String getIntString(int min, int max) {
        int i = getInt(min, max);
        return String.valueOf(i);
    }

    /**
     * 取得随机数
     * 
     * @param len
     *            随机数长度
     * @return 生成的随机数
     */
    public static String getRandomString(final int len) {
        if (len < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(LETTER_AND_DIGIT[random.nextInt(LETTER_AND_DIGIT_LENGTH)]);
        }
        return sb.toString();
    }

    public static String getRandomInt(int len) {
        if (len < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(DIGIT_CHARS[random.nextInt(DIGIT_CHARS_LENGTH)]);
        }
        return sb.toString();
    }

}
