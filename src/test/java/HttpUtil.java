// import org.apache.http.HttpEntity;
// import org.apache.http.HttpHost;
// import org.apache.http.HttpStatus;
// import org.apache.http.client.config.RequestConfig;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class HttpUtil {
    // /**
    //  * Get请求url，可配置代理
    //  * @param url
    //  * @param proxyIp
    //  * @param proxyPort
    //  * @param proxyScheme
    //  * @return String
    //  */
    // public static String doGetRequest(String url, String proxyScheme, String proxyIp, int proxyPort) {
    //     CloseableHttpClient httpClient = null;
    //     CloseableHttpResponse response = null;
    //     String result = "";
    //     try {
    //         // 通过址默认配置创建一个httpClient实例
    //         httpClient = HttpClients.createDefault();
    //         // 创建httpGet远程连接实例
    //         HttpGet httpGet = new HttpGet(url);
    //         // 代理
    //         RequestConfig requestConfig;
    //         if (proxyIp != null) {
    //             // 代理信息
    //             HttpHost proxy = new HttpHost(proxyIp, proxyPort, proxyScheme);
    //
    //             // httpGet.setHeader("Authorization", "Bearer "+ authori);
    //             // 设置配置请求参数
    //             requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
    //                     .setProxy(proxy)
    //                     .setConnectionRequestTimeout(3500)// 请求超时时间
    //                     .setSocketTimeout(6000)// 数据读取超时时间
    //                     .build();
    //         } else {
    //             requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
    //                     .setConnectionRequestTimeout(3500)// 请求超时时间
    //                     .setSocketTimeout(6000)// 数据读取超时时间
    //                     .build();
    //         }
    //         // 为httpGet实例设置配置
    //         httpGet.setConfig(requestConfig);
    //         // 执行get请求得到返回对象
    //         response = httpClient.execute(httpGet);
    //         int statusCode = response.getStatusLine().getStatusCode();
    //         if (statusCode == HttpStatus.SC_OK) {
    //             // 从响应对象中获取响应内容
    //             HttpEntity entity = response.getEntity();
    //             result = EntityUtils.toString(entity);
    //         }
    //     } catch (IOException e) {
    //         // Assert.assertTrue(false,e.getMessage());
    //         // e.printStackTrace();
    //     } finally {
    //         // 关闭资源
    //         if (null != response) {
    //             try {
    //                 response.close();
    //             } catch (IOException e) {
    //                 // Assert.assertTrue(false,e.getMessage());
    //                 // e.printStackTrace();
    //             }
    //         }
    //         if (null != httpClient) {
    //             try {
    //                 httpClient.close();
    //             } catch (IOException e) {
    //                 // Assert.assertTrue(false,e.getMessage());
    //                 // e.printStackTrace();
    //             }
    //         }
    //     }
    //     return result;
    // }


    public static String doGet(String interUrl, String proxyScheme, String proxyIp, int proxyPort) {
        HttpURLConnection httpURLConnection = null;

        try {
            // 1. 得到访问地址的URL
            URL url = new URL(interUrl);
            // 配置代理
            Proxy proxy = null;
            if (proxyIp != null) {
                if (proxyScheme == "http"){
                    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
                } else if (proxyScheme == "socks"){
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) { // 循环从流中读取
                    msg += line + "\n";
                }
                reader.close(); // 关闭流
            }
            // 显示响应结果
            // System.out.println(msg);
            return msg;
        } catch (IOException e) {
            // System.out.println("转发出错，错误信息："+e.getLocalizedMessage()+";"+e.getClass());
            return "连接失败";

        }finally {
            // 6. 断开连接，释放资源
            if (null != httpURLConnection){
                try {
                    httpURLConnection.disconnect();
                }catch (Exception e){
                    // System.out.println("httpURLConnection 流关闭异常："+ e.getLocalizedMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://pv.sohu.com/cityjson";
        // // test(url);
        //
        // System.out.println(doGet(url, null, 7890, "http"));
        System.out.println(doGet(url, "http", "127.0.0.1", 7890));
    }

}
