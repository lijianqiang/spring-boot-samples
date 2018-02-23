package com.openplan.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils {

    public static String getRandomChineseString(int len) {
        if (len < 1) {
            return "";
        }
        String str = "";
        try {
            for (int i = 0; i < len; i++) {
                int hightPos, lowPos; // 定义高低位
                Random random = new Random();
                hightPos = (176 + Math.abs(random.nextInt(39)));// 获取高位值
                lowPos = (161 + Math.abs(random.nextInt(93)));// 获取低位值
                byte[] b = new byte[2];
                b[0] = (new Integer(hightPos).byteValue());
                b[1] = (new Integer(lowPos).byteValue());
                str = str + new String(b, "GBk");// 转成中文
            }
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * 检测字符串是否为空字符串
     * 
     * @param input
     *            待检测字符串
     * @return
     *         <li>true：字符串为空</li>
     *         <li>false：字符串不为空</li>
     */
    public static boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return (input.trim().length() == 0);
    }

    /**
     * 将对象转换为字符串对象
     * 
     * @param input
     *            待取得对象
     * @return 取得的字符串对象
     * @see #getString(Object, String)
     */
    public static String getString(Object input) {
        return getString(input, "");
    }

    /**
     * 将对象转换为字符串对象
     * 
     * @param input
     *            待取得对象
     * @param defVal
     *            对象为空时的默认返回值
     * @return 取得的字符串对象
     */
    public static String getString(Object input, String defVal) {
        if (input == null) {
            return defVal;
        }
        String str = input.toString();
        return (str == null) ? defVal : str.trim();
    }

    /**
     * 替换字符串中的指定字符
     * 
     * @param input
     *            待替换字符串
     * @param oldChar
     *            待替换字符
     * @param newChar
     *            替换后字符
     * @return
     *         <li>待替换字符串为null或长度为零时，返回待替换字符串</li>
     *         <li>其它，返回替换后结果</li>
     */
    public static String replaceAll(String input, char oldChar, char newChar) {
        if (input == null) {
            return input;
        }
        int len = input.length();
        if (input.length() == 0) {
            return input;
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char ch = input.charAt(i);
            if (ch == oldChar) {
                ch = newChar;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String urlEncode(String s) {
        try {
            String s1 = URLEncoder.encode(s, "gb2312");
            return s1;
        } catch (Exception ex) {
            return s;
        }
    }

    public static String urlDecode(String s) {
        try {
            String s1 = URLDecoder.decode(s, "gb2312");
            return s1;
        } catch (Exception ex) {
            return s;
        }
    }

    public static String vgetString(String str) {
        return str == null ? "" : str;
    }

    public static String displayString(String str) {
        StringBuffer buf = new StringBuffer();
        if (str == null)
            return buf.toString();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '<') {
                buf.append("&lt;");
            }
            else if (c == '>') {
                buf.append("&gt;");
            }
            else if (c == '\'') {
                buf.append("&#39;");
            }
            else if (c == '\"') {
                buf.append("&#34;");
            }
            else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String getText2Web(String str) {
        str = displayString(str);
        str = str.replaceAll("\r\n", "\r");
        str = str.replaceAll("\r", "<br>");
        str = str.replaceAll("\n", "<br>");
        str = str.replaceAll(" ", "&nbsp;");
        return str;
    }

    public static String inputdisplay(String str) {
        StringBuffer buf = new StringBuffer();
        if (str == null)
            return buf.toString();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c == '\'') {
                buf.append("&#39;");
            }
            else if (c == '\"') {
                buf.append("&#34;");
            }
            else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    public static String displayAltString(String str, int length) {
        StringBuffer buf = new StringBuffer();
        if (str == null) {
            return buf.toString();
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (length > 0) {
                if (i > 0 && i % 50 == 0) {
                    buf.append(" ");
                }
            }
            char c = str.charAt(i);
            if (c == '<') {
                buf.append("&lt;");
            }
            else if (c == '>') {
                buf.append("&gt;");
            }
            else if (c == '\'') {
                buf.append("\\'");
            }
            else if (c == '\r') {
                buf.append("<br>");
            }
            else if (c == '\n') {
                buf.append("<br>");
            }
            else if (c == '\\') {
                buf.append("\\\\");
            }
            else {
                buf.append(c);
            }
        }
        String value = buf.toString();
        while (value.indexOf("<br><br>") != -1) {
            value = value.replaceAll("<br><br>", "<br>");
        }
        if (length > 0 && value.length() > length) {
            value = value.substring(0, length) + " <font color=blue>[...]</font>";
        }
        return value;
    }

    // GBK转ISO
    public static String toISO(String input) {
        try {
            byte[] bytes = input.getBytes("GBK");
            return new String(bytes, "ISO8859-1");
        } catch (Exception ex) {
        }
        return input;
    }

    // IS0转GBK
    public static String toGBK(String input) {
        try {
            byte[] bytes = input.getBytes("ISO8859-1");
            return new String(bytes, "GBK");
        } catch (Exception ex) {
        }
        return input;
    }

    // utf-8转gbk
    public static String utftoGBK(String input) {
        try {
            byte[] bytes = input.getBytes("utf-8");
            return new String(bytes, "GBK");
        } catch (Exception ex) {
        }
        return input;
    }

    public static String toUTF8(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    char cc[] = new char[1];
                    cc[0] = c;
                    b = (new String(cc)).getBytes("utf-8");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static String byte2Binstr(byte bstr) {
        String sstr = Integer.toBinaryString(bstr);
        int length = sstr.length();
        if (length > 8) {
            sstr = sstr.substring(length - 8);
            length = 8;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 8; i > sstr.length(); i--) {
            sb.append("0");
        }
        sb.append(sstr);
        return sb.toString();
    }

    public static byte[] binstr2bytes(String bstr) {
        int length = bstr.length() / 8;
        if (length == 0)
            length = 1;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int bint = Integer.parseInt(bstr.substring(i * 8, (i + 1) * 8), 2);
            if (bint > 127) {
                bint = bint - 256;
            }
            result[i] = (byte) bint;
        }
        return result;
    }

    public static String str2Binary(String str) {
        StringReader sr = new StringReader(str);
        StringBuffer result = new StringBuffer();
        try {
            int ch = sr.read();
            while (ch != -1) {
                String chnum = Integer.toBinaryString(ch);
                int slength = chnum.length();
                int mlength = 8;
                if (slength > 8)
                    mlength = 16;
                for (int i = mlength; i > slength; i--) {
                    result.append("0");
                }
                result.append(chnum);
                ch = sr.read();
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (sr != null)
                    sr.close();
            } catch (Exception e) {
            }
        }
    }

    public static String Binary2str(String str) {
        int length = str.length() / 8;
        StringWriter sw = new StringWriter();
        String temp = "";
        int tempint = 0;
        String result = "";
        try {
            for (int i = 0; i < length; i++) {
                temp = str.substring(i * 8, (i + 1) * 8);
                tempint = Integer.parseInt(temp, 2);
                sw.write(tempint);
            }
            result = sw.toString();
        } catch (Exception e) {
        } finally {
            try {
                sw.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static String byte2str(byte bstr) {
        String bb = byte2Binstr(bstr);
        return Binary2str(bb);
    }

    public static byte[] str2bytes(String str) {
        return binstr2bytes(str2Binary(str));
    }

    public static String str2str(String source, int rlength, char c) {
        int slength = source.length();
        if (slength < rlength) {
            int length = rlength - slength;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                sb.append(c);
            }
            return source + sb.toString();
        }
        return source.substring(0, rlength);
    }

    public static String str2Hex(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i + 1);
            if (!isGBK(temp)) {
                result.append(temp);
            } else {
                int ch = Integer.parseInt(str2Binary(temp), 2);
                String chnum = Integer.toHexString(ch);
                result.append("\\u" + chnum.toUpperCase());
            }
        }
        return result.toString();
    }

    public static boolean isGBK(String str) {
        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;
                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;
    }

    public static Date strToDate(String datestr) {
        String patterns[];
        int i;
        patterns = (new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy:MM:dd HH:mm", "yyyy:MM:dd HH:mm:ss", "yyyy-MM-dd HH:mm", "dd.MM.yy HH:mm",
                "yyyyMMdd HHmmss", "yyyyMMdd HHmm", "MM/dd/yy hh:mm a", "HH:mm:ss dd.MM.yyyy", "yyyy:MM:dd", "yyyy-MM-dd", "dd.MM.yy", "yyyyMMdd",
                "MM/dd/yy", "yyyy:MM:dd HH:mm:sss", "yyyy/MM/dd" });
        for (i = 0; i < patterns.length; i++) {
            try {
                DateFormat parser = new SimpleDateFormat(patterns[i]);
                return parser.parse(datestr);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    public static int strToInt(String intstr) throws IOException {
        int result = 0;
        if (intstr != null && intstr.trim().length() > 0) {
            result = Integer.parseInt(intstr);
        }
        return result;
    }

    public static long str2Long(String longstr) throws IOException {
        long result = 0;
        if (longstr != null && longstr.trim().length() > 0) {
            result = Long.parseLong(longstr);
        }
        return result;
    }

    public static int boolean2int(boolean bo) throws IOException {
        return bo ? 1 : 0;
    }

    public static String[] split(String s, String separator) {
        if (s == null) {
            throw new NullPointerException("source String cannot be null");
        }
        if (separator == null) {
            throw new NullPointerException("separator cannot be null");
        }
        if (separator.length() == 0) {
            throw new IllegalArgumentException("separator cannot be empty");
        }

        ArrayList<String> buffer = new ArrayList<String>();

        int start = 0;
        int end = s.indexOf(separator);
        while (end != -1) {
            buffer.add(s.substring(start, end));
            start = end + separator.length();
            end = s.indexOf(separator, start);
        }
        buffer.add(s.substring(start, s.length()));

        return (String[]) buffer.toArray(new String[0]);
    }

    public static String strings2str(String[] values) {
        if (values == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            if (i != 0) {
                result.append(",");
            }
            result.append("'" + values[i] + "'");
        }
        return result.toString();
    }

    public static String list2str(List<String> values) {
        if (values == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        Iterator<String> valuesIter = values.iterator();
        for (int i = 0; valuesIter.hasNext(); i++) {
            if (i != 0) {
                result.append(",");
            }
            result.append("'" + valuesIter.next() + "'");
        }
        return result.toString();
    }

    public static byte[] getBytes(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(baos);
        os.writeObject(object);
        byte[] result = baos.toByteArray();
        os.close();
        baos.close();
        return result;
    }

    public static ByteArrayInputStream getStream(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(baos);
        os.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return bais;
    }

    public static Object getObject(InputStream bais) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(bais);
        return is.readObject();
    }

    public static long ip2Long(String ipAddress) throws IOException {
        String[] ip = ipAddress.split("\\.", 4);
        long ip0 = str2Long(ip[0]) * 256 * 256 * 256;
        long ip1 = str2Long(ip[1]) * 256 * 256;
        long ip2 = str2Long(ip[2]) * 256;
        long ip3 = str2Long(ip[3]);
        return ip0 + ip1 + ip2 + ip3;
    }

    public static boolean isContain(String srcIP, String beginIP, String endIP) throws IOException {
        return ip2Long(srcIP) >= ip2Long(beginIP) && ip2Long(srcIP) <= ip2Long(endIP) ? true : false;
    }

    // 计算两个字符串相似度
    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            // 容错，抛出的这个异常是表明在传参的时候，传递了一个不合法或不正确的参数。
            // 好像都这样用，illegal:非法。Argument:参数，证据。
            throw new IllegalArgumentException("Strings must not be null");
        }
        // 计算传入的两个字符串长度
        int n = s.length();
        int m = t.length();
        // 容错，直接返回结果。这个处理不错
        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }
        // 这一步是根据字符串长短处理，处理后t为长字符串，s为短字符串，方便后面处理
        if (n > m) {
            CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }
        // 开辟一个字符数组，这个n是短字符串的长度
        int p[] = new int[n + 1];
        int d[] = new int[n + 1];
        // 用于交换p和d的数组
        int _d[];
        int i;
        int j;
        char t_j;
        int cost;
        // 赋初值
        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            // t是字符串长的那个字符
            t_j = t.charAt(j - 1);
            d[0] = j;
            for (i = 1; i <= n; i++) {
                // 计算两个字符是否一样，一样返回0。
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // 可以将d的字符数组全部赋值。
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
            // 交换p和d
            _d = p;
            p = d;
            d = _d;
        }
        // 最后的一个值即为差异值
        return p[n];
    }

    // 替换特殊字符为null
    public static String strReplace(String str) {

        str = str.replace("{", "");
        str = str.replace("}", "");
        str = str.replace("[", "");
        str = str.replace("]", "");

        return str;
    }

    /*
     * private final static String[] hex = { "00", "01", "02", "03", "04", "05",
     * "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11",
     * "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D",
     * "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
     * "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35",
     * "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41",
     * "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D",
     * "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
     * "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65",
     * "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71",
     * "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D",
     * "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
     * "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95",
     * "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1",
     * "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD",
     * "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9",
     * "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5",
     * "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1",
     * "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD",
     * "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9",
     * "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5",
     * "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
     * 
     * private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
     * 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A,
     * 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
     * 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
     * 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };
     * 
     * public static String escape(String s) { StringBuffer sbuf = new
     * StringBuffer(); int len = s.length(); for (int i = 0; i < len; i++) { int
     * ch = s.charAt(i); if ('A' <= ch && ch <= 'Z') { sbuf.append((char) ch); }
     * else if ('a' <= ch && ch <= 'z') { sbuf.append((char) ch); } else if ('0'
     * <= ch && ch <= '9') { sbuf.append((char) ch); } else if (ch == '-' || ch
     * == '_' || ch == '.' || ch == '!' || ch == '~' || ch == '*' || ch == '\''
     * || ch == '(' || ch == ')') { sbuf.append((char) ch); } else if (ch <=
     * 0x007F) { sbuf.append('%'); sbuf.append(hex[ch]); } else {
     * sbuf.append('%'); sbuf.append('u'); sbuf.append(hex[(ch >>> 8)]);
     * sbuf.append(hex[(0x00FF & ch)]); } } return sbuf.toString(); }
     * 
     * public static String unescape(String s) { StringBuffer sbuf = new
     * StringBuffer(); int i = 0; int len = s.length(); while (i < len) { int ch
     * = s.charAt(i); if ('A' <= ch && ch <= 'Z') { sbuf.append((char) ch); }
     * else if ('a' <= ch && ch <= 'z') { sbuf.append((char) ch); } else if ('0'
     * <= ch && ch <= '9') { sbuf.append((char) ch); } else if (ch == '-' || ch
     * == '_'|| ch == '.' || ch == '!' || ch == '~' || ch == '*'|| ch == '\'' ||
     * ch == '(' || ch == ')') { sbuf.append((char) ch); } else if (ch == '%') {
     * int cint = 0; if ('u' != s.charAt(i + 1)) { cint = (cint << 4) |
     * val[s.charAt(i + 1)]; cint = (cint << 4) | val[s.charAt(i + 2)]; i += 2;
     * } else { cint = (cint << 4) | val[s.charAt(i + 2)]; cint = (cint << 4) |
     * val[s.charAt(i + 3)]; cint = (cint << 4) | val[s.charAt(i + 4)]; cint =
     * (cint << 4) | val[s.charAt(i + 5)]; i += 5; } sbuf.append((char) cint); }
     * else { sbuf.append((char) ch); } i++; } return sbuf.toString(); }
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareVersion(String oldVersion, String newVersion) {
        boolean isUpdate = false;
        if (oldVersion == null || newVersion == null)
            return isUpdate;

        String[] oldVersionArray = oldVersion.split("\\.");
        String[] newVersionArray = newVersion.split("\\.");
        final int oldArraySize = oldVersionArray.length;
        final int newArraySize = newVersionArray.length;

        int tempSize = oldArraySize >= newArraySize ? newArraySize : oldArraySize;

        for (int i = 0; i < tempSize; i++) {
            if (Integer.parseInt(oldVersionArray[i]) < Integer.parseInt(newVersionArray[i])) {
                isUpdate = true;
                break;
            }
        }
        return isUpdate;
    }

    // 过滤特殊字符
    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static boolean isNotEmpty(String cityName) {
        return !isEmpty(cityName);
    }

    // public static void main(String[] args) {
    // String stest = "1234 abcd[]()<+>,.~\\中国";
    // String stest = "中国";
    // System.out.println(stest);
    // System.out.println(escape(stest));
    // System.out.println(unescape(escape(stest)));
    // System.out.println(getLevenshteinDistance("tenger","tenger"));
    // System.out.println(getRandomChineseString(4));
    // System.out.println(StringUtil.isNumeric("12313123"));
    // }
}
