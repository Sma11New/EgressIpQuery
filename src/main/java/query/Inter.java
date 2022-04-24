package query;

import utils.FileUtil;
import utils.TypeTransUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inter {
    // 整个运行中使用的接口变量
    private static List<String> interList;
    // 出口IP
    private static List<String> egressIpList = new ArrayList<>();
    // 自定义接口的配置保存文件
    private static final String interConFile = "EIQconfig.list";
    // 默认接口，用于初始化和重置恢复
    private static final List<String> defaultInter = Arrays.asList(
            // "http://ip.cn",
            "http://cip.cc",
            "http://myip.ipip.net",
            // "http://ifconfig.me",
            // "http://members.3322.org/dyndns/getip",
            "http://vv.video.qq.com/checktime",
            // "http://ip-api.com/json",
            "http://pv.sohu.com/cityjson",
            "http://txt.go.sohu.com/ip/soip"
    );

    // 初始化接口列表
    static {
        try {
            File interFile = new File(interConFile);
            // 存在自定义接口配置文件，表示用户修改并自定义接口，则加载dialog时读取文件中的接口
            // 否则读取默认接口
            if (interFile.exists()) {
                interList = FileUtil.readFile(interConFile);
            } else {
                interList = defaultInter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取接口列表
     *
     * @return 接口List
     */
    public static List<String> getInterList() {
        return interList;
    }

    /**
     * 获取默认接口列表，用于重置和初始化
     *
     * @return 默认接口List
     */
    public static List<String> getDefaultInterList() {
        return defaultInter;
    }

    /**
     * set接口，并判断是否与默认接口相同，相同删除配置文件，不同写入配置文件
     * 接收String
     *
     * @param interList 接口List
     */
    public static void setInterList(String interList) {
        Inter.interList = TypeTransUtil.stringToList(interList);

        if (!Inter.interList.equals(Inter.defaultInter)) {
            FileUtil.writeFile(interList, interConFile);
        } else {
            FileUtil.deleteFile(interConFile);
        }
    }

    /**
     * set接口，接收List
     *
     * @param interList 接口List
     */
    public static void setInterList(List<String> interList) {
        Inter.interList = interList;
        // if (Arrays.equals(Inter.interList, Inter.))
        FileUtil.writeFile(interList, interConFile);
    }

    /**
     * get 出口IP
     *
     * @return 出口IPList
     */
    public static List<String> getEgressIpList() {
        return egressIpList;
    }

    /**
     * set 出口IP、判断是否存在并加入
     *
     * @param egressIp String ip
     */
    public static void setEgressIpList(String egressIp) {
        if (!egressIpList.contains(egressIp) && egressIp != null) {
            egressIpList.add(egressIp);
        }
    }

    /**
     * 将出口IP列表置空
     */
    public static void setEgressIpListNull() {
        egressIpList.clear();
    }
}
