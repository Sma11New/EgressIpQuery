package query;

public class Proxy {
    private static String proxySchame = null;
    private static String proxyIp = null;
    private static int proxyPort = 0;

    /**
     * 内部类、获取proxy参数
     */
    public static class getProxy {
        public static String schame() {
            return proxySchame;
        }

        public static String ip() {
            return proxyIp;
        }

        public static int port() {
            return proxyPort;
        }
    }

    /**
     * set proxy
     *
     * @param proxySchame 类型(socks、http)
     * @param proxyIp     代理IP
     * @param proxyPort   代理端口
     */
    public static void setProxy(String proxySchame, String proxyIp, int proxyPort) {
        Proxy.proxySchame = proxySchame;
        Proxy.proxyIp = proxyIp;
        Proxy.proxyPort = proxyPort;
    }
}
