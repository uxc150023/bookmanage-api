package com.book.validator;

import com.book.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 我的常用验证器
 * <p>
 * File: MyValidater.java<br/>
 * Description: <br/>
 * <p>
 * Copyright: Copyright (c) 2012 ecbox.com<br/>
 * Company: ECBOX,Inc.<br/>
 *
 * @author WangHui
 * @version 1.0
 * @date 2013-4-22
 */
public abstract class MyValidator {

    private MyValidator() {
    }

    /**
     * 验证邮箱
     */
    private static Pattern PATTERN_EMAIL = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    /**
     * 验证手机号
     */
    private static Pattern PATTERN_MOBILE = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
    private static final int FLAG_MOBILE_LENGTH = 11;
    /**
     * 数字
     */
    private static Pattern PATTERN_NUMERIC  = Pattern.compile("[0-9]*");

    /**
     * 是否是email地址
     *
     * @param input
     * @return
     */

    public static boolean isEmail(String email) {
        email = email.toLowerCase();
        if (email.endsWith(".con")) {
            return false;
        } else if (email.endsWith(".cm")) {
            return false;
        } else if (email.endsWith("@gmial.com")) {
            return false;
        } else if (email.endsWith("@gamil.com")) {
            return false;
        } else if (email.endsWith("@gmai.com")) {
            return false;
        }
        return PATTERN_EMAIL.matcher(email).matches();
    }

    /**
     * 是否是手机号码
     *
     * @param input
     * @return
     */

    public static boolean isMobile(String input) {
        if (input.length() != FLAG_MOBILE_LENGTH) {
            return false;
        }
//    return isNumeric(input);
        Matcher m = null;
        m = PATTERN_MOBILE.matcher(input);
        return m.matches();
    }


    /**
     * 全数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String input) {
        Matcher isNum = PATTERN_NUMERIC.matcher(input);
        return isNum.matches();
    }

    /**
     * 是否是网络设备物理地址格式
     *
     * @param input
     * @return
     */
    public static boolean isMac(String input) {
        return isPattern("[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}", input);
    }

    /**
     * 是否是固定电话号码
     *
     * @param input
     * @return
     */
    public static boolean isTelephone(String input) {
        return isPattern("(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})?", input);
    }

    /**
     * 是否是url
     *
     * @param input
     * @return
     */
    public static boolean isUrl(String input) {
        return isPattern("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", input);
    }

    /**
     * 是否是qq号码
     *
     * @param input
     * @return
     */
    public static boolean isQQ(String input) {
        return isPattern("(^\\s*[1-9]+[0-9]{4,9}\\s*$)", input);
    }

    /**
     * 是否是身份证号码
     *
     * @param input
     * @return
     */
    public static boolean isIdCard(String input) {
        return IdCardValidator.validId18(input);
    }

    /**
     * 数值符合范围
     *
     * @param min
     * @param max
     * @param input
     * @return
     */
    public static boolean isRange(long min, long max, long input) {
        if (input <= max && input >= min) {
            return true;
        }
        return false;
    }

    /**
     * 正则匹配
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isPattern(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    /**
     * 是否是过去的时间
     *
     * @param input
     * @return
     */
    public static boolean isPast(long input) {
        if (System.currentTimeMillis() / 1000 > input) {
            return true;
        }
        return false;
    }

    /**
     * 不是空值
     *
     * @param input
     * @return
     */
    public static boolean isNotNull(Object input) {
        return input != null;
    }

    /**
     * 不是空字符串
     *
     * @param input
     * @return
     */
    public static boolean isNotEmpty(String input) {
        return !StringUtils.isEmpty(input);
    }

    /**
     * 符合最小值
     *
     * @param min
     * @param input
     * @return
     */
    public static boolean isMin(long min, long input) {
        if (min < input) {
            return true;
        }
        return false;
    }

    /**
     * 符合最大值
     *
     * @param max
     * @param input
     * @return
     */
    public static boolean isMax(long max, long input) {
        if (max > input) {
            return true;
        }
        return false;
    }

    /**
     * 符合字符串长度范围
     *
     * @param min
     * @param max
     * @param input
     * @return
     */
    public static boolean isLength(int min, int max, String input) {
        int length = input.length();
        if (length >= min && length <= max) {
            return true;
        }
        return false;
    }

    /**
     * 是一个未来的时间
     *
     * @param input
     * @return
     */
    public static boolean isFuture(long input) {
        return System.currentTimeMillis() / 1000 < input;
    }

    /**
     * 是否符合数字范围
     *
     * @param integer
     * @param fraction
     * @param input
     * @return
     */
    public static boolean isDigits(int integer, int fraction, String input) {
        String[] arr = input.split(".");
        // 有小数部分
        if (arr.length == 2) {
            if (arr[1].length() > fraction) {
                return false;
            }
        }
        if (arr[0].length() > integer) {
            return false;
        }
        return true;
    }

    /**
     * 是否符合密码格式<br>
     * 目前密码规则是6-16位长度字符串
     *
     * @param input
     * @return
     */
    public static boolean isPassword(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        }
        if (input.length() < 6 || input.length() > 16) {
            return false;
        }
        return isPattern("^\\w+$", input);
    }

    public static boolean isPassword2(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        }
//    if (input.length() < 6 || input.length() > 16) {
//      return false;
//    }
//    return isPattern("^(?![0-9]*$)[a-zA-Z0-9]{6,20}$",input);
        return isPattern("((?!^[0-9]{0,10}$)(?!^[a-z]{0,10}$)(?!^[A-Z]{0,10}$))^[0-9A-Za-z\\u0020-\\u007e]{6,20}$", input);
    }


//  /**
//   * @param args
//   */
//  public static void main(String[] args) {
////    System.out.println(isMobile("17921103751"));
//    System.out.println(isPassword2("1wwwwwwwwww"));
//  }

}
