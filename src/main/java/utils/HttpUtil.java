package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpUtil {
    /**
     * Get请求url，可配置代理
     * @param url
     * @param proxyIp
     * @param proxyPort
     * @param proxyScheme
     * @return String
     */
    public static String doGetRequest(String url, String proxyScheme, String proxyIp, int proxyPort) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 代理
            RequestConfig requestConfig;
            if (proxyIp != null) {
                // 代理信息
                HttpHost proxy = new HttpHost(proxyIp, proxyPort, proxyScheme);

                // httpGet.setHeader("Authorization", "Bearer "+ authori);
                // 设置配置请求参数
                requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                        .setProxy(proxy)
                        .setConnectionRequestTimeout(3500)// 请求超时时间
                        .setSocketTimeout(6000)// 数据读取超时时间
                        .build();
            } else {
                requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                        .setConnectionRequestTimeout(3500)// 请求超时时间
                        .setSocketTimeout(6000)// 数据读取超时时间
                        .build();
            }
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                // 从响应对象中获取响应内容
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            // Assert.assertTrue(false,e.getMessage());
            // e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    // Assert.assertTrue(false,e.getMessage());
                    // e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    // Assert.assertTrue(false,e.getMessage());
                    // e.printStackTrace();
                }
            }
        }
        return result;
    }
}


