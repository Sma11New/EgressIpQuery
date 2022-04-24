package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class RegUtil {
    /**
     * 判断是否为合法ip
     *
     * @param ip 待检查IP
     * @return boolean
     */
    public static boolean isIp(String ip) {
        return ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])" +
                "(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
    }

    /**
     * 判断是否为合法端口
     *
     * @param port 待检查端口
     * @return boolean
     */
    public static boolean isPort(String port) {
        return port.matches("[0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5]");
    }

    /**
     * 判断合法URL
     *
     * @param str 待检查url
     * @return boolean
     */
    public static boolean isURL(String str) {
        str = str.toLowerCase();
        String regex = "^((https|http)?://)"  //https、http、ftp、rtsp、mms
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 例如：199.194.52.184
                + "|" // 允许IP和DOMAIN(域名)
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,5})?" // 端口号最大为65535,5位数
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        return str.matches(regex);
    }

    /**
     * 正则匹配IP
     *
     * @param data 待检索数据
     * @return String ip
     */
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

    /**
     * 正则匹配归属地
     *
     * @param data 待检索数据
     * @return String addr
     */
    public static String findAddr(String data) {
        String IPADDRESS_PATTERN = "((?<=addr\":\")[\\s\\S]*?(?=\",\"regionNames))";
        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
}
