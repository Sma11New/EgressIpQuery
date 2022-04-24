import utils.RegUtil;

import java.util.List;

public class Addr {
    public static void main(String[] args) {
        String ip = "{\"ip\":\"220.181.41.23\",\"pro\":\"北京市\",\"proCode\":\"110000\",\"city\":\"北京市\",\"cityCode\":\"110000\",\"region\":\"\",\"regionCode\":\"0\",\"addr\":\"北京市 电信\",\"regionNames\":\"\",\"err\":\"\"}";
        System.out.println(RegUtil.findAddr(ip));
    }
}
