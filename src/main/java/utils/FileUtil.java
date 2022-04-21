package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 读文件
     * @param file
     * @return
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDataList;
    }

    /**
     * 接收List类型数据
     *
     * @param fileDataList
     * @param file
     * @throws Exception
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
     * @param fileDataStr
     * @param file
     * @throws Exception
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
     * 接收class，调用getResourceAsStream
     * @param cls
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> jarReadFile(Class cls, String file) throws IOException {
        List<String> fileDataList = new ArrayList();
        InputStream inputStream = cls.getResourceAsStream("/" + file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String data = reader.readLine();
        while (data != null) {
            fileDataList.add(data);
            data = reader.readLine();
        }
        return fileDataList;
    }

    /**
     * 删除文件
     * @param file
     */
    public static void deleteFile(String file) {
        new File(file).delete();
    }
}
