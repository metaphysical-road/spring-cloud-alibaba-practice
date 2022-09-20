package com.alibaba.cloud.youxia.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;
@Slf4j
public class HttpClientUtils {

    private static PoolingHttpClientConnectionManager connectionManager = null;
    private static HttpClientBuilder httpBulder = null;
    private static RequestConfig requestConfig = null;
    private static final int MAXCONNECTION = 10;
    private static final int DEFAULTMAXCONNECTION = 5;
    static {
        //设置http的状态参数,socketTimeOut 数据包间隔时间，三次握手时间，从连接池获取连接的时间
        requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000).build();
        connectionManager = new PoolingHttpClientConnectionManager();
        //最大连接数
        connectionManager.setMaxTotal(MAXCONNECTION);
        //同一个路由的最大连接数
        connectionManager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTION);
        httpBulder = HttpClients.custom();
        httpBulder.setConnectionManager(connectionManager);
    }

    public static CloseableHttpClient getConnection() {
        return httpBulder.setDefaultRequestConfig(requestConfig).build();
    }
    public static RestResult get(String uri, Map<String, String> params) throws Exception {
        return get(uri, params, null);
    }
    public static RestResult get(String uri, Map<String, String> params, String project) throws Exception {
        CloseableHttpClient httpClient = getConnection();
        URIBuilder uriTemp = new URIBuilder(uri);
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String head = entry.getKey();
                String param = entry.getValue();
                uriTemp.addParameter(head, param);
            }
        }
        URI uriResult = uriTemp.build();
        HttpGet httpGet = new HttpGet(uriResult);
        httpGet.setHeader("Content-Type", "application/json;charset=utf8");
//        if ("rocketmq".equals(project)) {
//            httpGet.setHeader("Cookie",
//                "nginx_auth=9bf3600e128bbc313f88e34e03eb2a01f552aad1f522dd7fc5aee8f574c5bd0e9933ea123e9334a151ccba5daf4403f0890398b0cdc3f7d435d3a34c3587f95cd54f2d4927ebc53caeef9dd54904bd11c4efe0109c4186faec504d6741c79334; td_cookie=470600967; NG_TRANSLATE_LANG_KEY=%22zh%22");
//        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            RestResult restResult = new RestResult();
            restResult.setCode(response.getStatusLine().getStatusCode());
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            restResult.setMessage(content);
            return restResult;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("请求response资源关闭失败");
                }
            }
            httpGet.releaseConnection();
        }
    }

    public static RestResult post(String url, String paramJson) throws Exception {
        CloseableHttpClient httpClient = getConnection();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("Cookie",
            "td_cookie=13965157; nginx_auth=9bf3600e128bbc313f88e34e03eb2a01f552aad1f522dd7fc5aee8f574c5bd0e75c2ae0be494720ec21860e2ad0dbe0f6f1eafda534e4159c57819386bd865e6d60b3e1e63bd5b017e798904cfa50ff5d7d0264ad8a03907c41b110de9d2314c");
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new StringEntity(paramJson, "UTF-8"));
            response = httpClient.execute(httpPost);
            RestResult restResult = new RestResult();
            restResult.setCode(response.getStatusLine().getStatusCode());
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            restResult.setMessage(content);
            return restResult;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("请求response资源关闭失败");
                }
            }
            httpPost.releaseConnection();
        }
    }

    public static void checkHttpResult(RestResult restResult) throws Exception {
        if (restResult.getCode() != 200) {
            throw new Exception(restResult.getMessage());
        }
    }
}


