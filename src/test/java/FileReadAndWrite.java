import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileReadAndWrite {
    public static List<String> readFile(String file) throws Exception {
        List<String> fileDataList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String data = br.readLine();
        while (data != null) {
            fileDataList.add(data);
            data = br.readLine();
        }
        br.close();
        return fileDataList;
    }

    public static void writeFile(List<String> fileDataList, String file) throws Exception {
        FileWriter fw = new FileWriter(file);
        for (String data : fileDataList) {
            fw.write(data + "\r\n");
        }
        fw.close();
    }


    public static void main(String[] args) throws Exception {
        String file = "src\\main\\resources\\queryInterface.list";
        List<String> ss = readFile(file);
        System.out.println(ss);
        for (String aa : ss) {
            System.out.println("接口：" + aa);
        }
        String file1 = "src\\main\\resources\\queryInterface.list1";
        writeFile(ss, file1);
    }
}
