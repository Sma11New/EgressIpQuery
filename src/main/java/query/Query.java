package query;

import utils.HttpUtil;
import utils.RegUtil;

public class Query {
    /**
     * 查询出口IP
     *
     * @param interUrl 查询接口地址
     * @return ip
     */
    public static String doQueryIp(String interUrl) {
        String egressIp = null;
        String reqResult = HttpUtil.doGet(interUrl, Proxy.getProxy.schame(), Proxy.getProxy.ip(), Proxy.getProxy.port());
        if (reqResult != null) {
            egressIp = RegUtil.findIp(reqResult);
        }
        return egressIp;
    }

    /**
     * 查询归属
     *
     * @param ip 待查询ip
     * @return addr
     */
    public static String doQueryAddr(String ip) {
        String addr = null;
        String url = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true";
        String reqResult = HttpUtil.doGet(url, null, null, 0);
        if (reqResult != null) {
            addr = RegUtil.findAddr(reqResult);
        }
        return addr;
    }
}
