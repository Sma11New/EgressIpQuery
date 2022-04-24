package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 读文件
     *
     * @param file 待读取文件
     * @return 一行一个元素
     */
    public static List<String> readFile(String file) {
        List<String> fileDataList = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String data = br.readLine();
            while (data != null) {
                fileDataList.add(data);
                data = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDataList;
    }

    /**
     * 写文件，接收List类型数据
     *
     * @param fileDataList 待写入内容，List，一元素一行
     * @param file         写入目标文件
     */
    public static void writeFile(List<String> fileDataList, String file) {
        try {
            FileWriter fw = new FileWriter(file);
            for (String data : fileDataList) {
                fw.write(data + "\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收String类型数据
     *
     * @param fileDataStr 待写入内容
     * @param file        目标文件
     */
    public static void writeFile(String fileDataStr, String file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(fileDataStr);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除文件
     *
     * @param file 待删除文件
     */
    public static void deleteFile(String file) {
        new File(file).delete();
    }
}
