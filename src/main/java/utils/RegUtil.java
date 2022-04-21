package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class RegUtil {
    /**
     * 判断是否为合法ip
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        boolean isLegal = ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])" +
                "(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        return isLegal;
    }

    /**
     * 判断是否为合法端口
     * @param port
     * @return
     */
    public static boolean isPort(String port) {
        boolean isLegal = port.matches("[0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5]");
        return isLegal;
    }

    public static String findIp(String data) {
        String IPADDRESS_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
