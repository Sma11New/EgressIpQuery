package utils;

import java.util.Arrays;
import java.util.List;

public class TypeTransUtil {

    /**
     * listToString：add
     * @param data
     * @return
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
     * @param data
     * @return
     */
    public static List<String> stringToList(String data) {
        String[] tmp = data.split("\n");
        List<String> resDataList = Arrays.asList(tmp);
        return resDataList;
    }
}
