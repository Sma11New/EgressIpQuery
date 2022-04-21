package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdExecUtil {

    public static void execCommandAndGetOutput(String cmd) {
        try {
            Runtime runtime = Runtime.getRuntime();
            // Process process = runtime.exec("cmd.exe /c curl ");
            Process process = runtime.exec(cmd);
            // 输出结果，必须写在 waitFor 之前
            String outStr = getStreamStr(process.getInputStream());
            // 错误结果，必须写在 waitFor 之前
            String errStr = getStreamStr(process.getErrorStream());
            int exitValue = process.waitFor(); // 退出值 0 为正常，其他为异常
            System.out.println("outStr: " + outStr);
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getStreamStr(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) {
        execCommandAndGetOutput("cmd.exe");
    }

}
