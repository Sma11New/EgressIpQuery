package utils;

import java.util.Arrays;
import java.util.List;

public class TypeTransUtil {

    /**
     * listToString：add
     *
     * @param data List数据
     * @return list2ToString
     */
    public static String listToString(List<String> data) {
        StringBuilder resDataSb = new StringBuilder();
        for (String each : data) {
            resDataSb.append(each).append("\n");
        }
        return resDataSb.toString();
    }

    /**
     * stringToList：split分割、Arrays.asList转换
     *
     * @param data String数据
     * @return StringToList
     */
    public static List<String> stringToList(String data) {
        return Arrays.asList(data.split("\n"));
    }
}
