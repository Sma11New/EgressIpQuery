public class IsIp {
    public static boolean isIp(String ip) {
        boolean isLegal = ip.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])" +
                "(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
        return isLegal;
    }

    public static boolean isPort(String port) {
        boolean isLegal = port.matches("[0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-5]{2}[0-3][0-5]");
        return isLegal;
    }

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        System.out.println(isIp(ip));
        String port = "65535";
        System.out.println(isPort(port));
    }
}
