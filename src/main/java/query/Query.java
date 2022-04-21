package query;

import utils.HttpUtil;
import utils.RegUtil;

public class Query {
    public static String doQuery(String interUrl) {
        String egressIp = null;
        String reqResult = HttpUtil.doGetRequest(interUrl, Proxy.getProxy.schame(), Proxy.getProxy.ip(), Proxy.getProxy.port());
        if (reqResult != null) {
            // String egressIp = RegUtil.findIp(reqResult);
             egressIp = RegUtil.findIp(reqResult);
        }
        return egressIp;
    }

    private String readConfigFile(String configFile) {
        return "";
    }
}
