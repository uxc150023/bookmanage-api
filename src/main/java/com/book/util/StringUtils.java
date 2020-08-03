package com.book.util;

import com.book.validator.MyValidator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。
 * <p>
 * File: StringUtils.java<br/>
 * Description: <br/>
 * <p>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-22
 */
public abstract class StringUtils {

    private StringUtils() {
    }

//  public static void main(String[] args) {
////    String line = ";;a;b;c;;;;d;;;;;;;;;;f;;;;";
////    String delimiters = ";";
////    String[] ss = StringUtils.lineParse(line, delimiters);
////    for (String s : ss) {
////      System.out.println(s);
////    }
//
////    System.out.println(isNumeric("1"));
//  }

    public static String[] lineParse(String line, String delimiters) {
        StringTokenizer st = new StringTokenizer(line, delimiters, true);

        ArrayList<String> fieldList = new ArrayList<String>();
        String[] ss = new String[st.countTokens()];
        String preField = delimiters;
        for (int i = 0; i < ss.length; i++) {
            String field = st.nextToken();
            if (preField.equals(delimiters) && field.equals(delimiters)) {
                fieldList.add("");
                preField = field;
            } else if (preField.equals(delimiters) && !field.equals(delimiters)) {
                fieldList.add(field);
                preField = field;
            } else if (!preField.equals(delimiters) && field.equals(delimiters)) {
                preField = field;
                continue;
            }
        }
        if (preField.equals(delimiters)) {
            fieldList.add("");
        }

        return fieldList.toArray(new String[fieldList.size()]);
    }

    /**
     * 把latin1转换成指定的编码
     *
     * @param s
     * @param charsetName "GBK" "UTF-8"
     * @return
     */
    public static String convertCharset(String s, String charsetName) {
        if (s != null) {
            try {
                int length = s.length();
                byte[] buffer = new byte[length];
                // 0x81 to Unicode 0x0081, 0x8d to 0x008d, 0x8f to 0x008f, 0x90
                // to 0x0090, and 0x9d to 0x009d.
                for (int i = 0; i < length; ++i) {
                    char c = s.charAt(i);
                    if (c == 0x0081) {
                        buffer[i] = (byte) 0x81;
                    } else if (c == 0x008d) {
                        buffer[i] = (byte) 0x8d;
                    } else if (c == 0x008f) {
                        buffer[i] = (byte) 0x8f;
                    } else if (c == 0x0090) {
                        buffer[i] = (byte) 0x90;
                    } else if (c == 0x009d) {
                        buffer[i] = (byte) 0x9d;
                    } else {
                        buffer[i] = Character.toString(c).getBytes("CP1252")[0];
                    }
                }
                String result = new String(buffer, charsetName);
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     *
     * @param obj
     * @return
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 检查指定的字符串列表是否不为空
     *
     * @param values
     * @return
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码
     *
     * @param unicode
     * @return
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     *
     * @param input
     * @return
     */
    public static String stripNonValidXMLCharacters(String input) {
        if (input == null || ("".equals(input)))
            return "";
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }


    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        java.util.Random random = new java.util.Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 隐藏电话号码
     *
     * @param mobile
     * @return
     */
    public static String disablePhone(String mobile) {
        if (isEmpty(mobile)) {
            return "";
        }
        if (!MyValidator.isMobile(mobile)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }


    public static String disableIdCard(String string) {
        if (isEmpty(string)) {
            return "";
        }
        if (string.length() < 8) {
            return string;
        }
        String reg = "(\\d{4})\\d+(\\d{4})";
        return string.replaceAll(reg, "$1****$2");
    }

    public static String disableName(String realname) {
        char[] r =  realname.toCharArray();
        String realname1 =null;
        if(r.length ==1){
            realname1 =  realname;
        }
        if(r.length == 2){
            realname1 =  realname.replaceFirst(realname.substring(1),"*");
        }
        if (r.length > 2) {
            realname1 =  realname.replaceFirst(realname.substring(1,r.length-1) ,"*");
        }
        return realname1;
    }

    public static String disableEmail(String string) {
        if (isEmpty(string)) {
            return "";
        }

        if (string.contains("@")) {
            String emailPrefix = string.substring(0, string.indexOf("@")-1);
            if (emailPrefix.length() >= 1){
                return string.substring(0,1)+"****"+string.substring(string.indexOf("@")-1);
            }
        }
        return string;
    }


    //########################特殊字符处理start########################
    /**
     * 特殊字符校验
     * @param str
     * @return
     */
    public static boolean specialCharCheck(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    /**
     * 去掉字符串中的特殊字符
     */
    public static String  specialCharDelete(String str) {
        String regEx= "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
//    System.out.println(m.replaceAll("").trim());
        return m.replaceAll("").trim();
    }
//########################特殊字符处理end########################
}
