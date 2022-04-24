package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class HttpUtil {
    /**
     * Get请求url，可配置代理
     *
     * @param interUrl    查询接口地址
     * @param proxyScheme 代理类型(socks、http)
     * @param proxyIp     代理IP地址
     * @param proxyPort   代理端口
     * @return 成功：出口IP、失败：null
     */
    public static String doGet(String interUrl, String proxyScheme, String proxyIp, int proxyPort) {
        HttpURLConnection httpURLConnection = null;
        try {
            // 1. 得到访问地址的URL
            URL url = new URL(interUrl);
            // 配置代理
            Proxy proxy = null;
            if (proxyIp != null) {
                if (proxyScheme == "http") {
                    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
                } else if (proxyScheme == "socks") {
                    proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 7890));
                }
                httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
            } else {
                // 2. 得到网络访问对象java.net.HttpURLConnection
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            /* 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接 */
            // 设置是否向HttpURLConnection输出
            httpURLConnection.setDoOutput(false);
            // 设置是否从httpUrlConnection读入
            httpURLConnection.setDoInput(true);
            // 设置请求方式　默认为GET
            httpURLConnection.setRequestMethod("GET");
            // 设置是否使用缓存
            httpURLConnection.setUseCaches(true);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            httpURLConnection.setInstanceFollowRedirects(true);
            // 设置超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 连接
            httpURLConnection.connect();
            // 4. 得到响应状态码的返回值 responseCode
            int code = httpURLConnection.getResponseCode();
            // 5. 如果返回值正常，数据在网络中是以流的形式得到服务端返回的数据
            String msg = null;
            if (code == 200) { // 正常响应
                // 从流中读取响应信息
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "gbk"));
                String line = null;
                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg += line + "\n";
                }
                reader.close(); // 关闭流
            }
            return msg;
        } catch (IOException e) {
            // System.out.println("转发出错，错误信息："+e.getLocalizedMessage()+";"+e.getClass());
            return "连接失败";

        } finally {
            // 6. 断开连接，释放资源
            if (null != httpURLConnection) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    // System.out.println("httpURLConnection 流关闭异常："+ e.getLocalizedMessage());
                }
            }
        }
    }
}


