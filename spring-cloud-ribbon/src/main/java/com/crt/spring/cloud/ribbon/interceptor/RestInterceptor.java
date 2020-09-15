package com.crt.spring.cloud.ribbon.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author crt
 * @date 2020/9/14 3:38 下午
 */
public class RestInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    private DiscoveryClient discoveryClient;

    private volatile Map<String, List<String>> registryList = new HashMap<String, List<String>>();
    private Random random = new Random();

    /**
     * 注册网关
     * 定时去注册中心获取实例列表
     */
    @Scheduled(fixedRate = 60 * 1000)
    private void discovery(){
        discoveryClient.getServices().forEach(service -> {
            List<String> addressList = discoveryClient.getInstances(service).stream().map(instance -> {
               return instance.isSecure() ?
                        String.format("https://%s:%s", instance.getHost(), instance.getPort())
                        : String.format("http://%s:%s", instance.getHost(), instance.getPort());
            }).collect(Collectors.toList());
            registryList.put(service, addressList);
        });
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        URI requestURI = httpRequest.getURI();
        String serviceName = requestURI.getHost();
        String uri = requestURI.getPath();

        List<String> addressList = registryList.get(serviceName);
        int nextInt = random.nextInt(addressList.size());
        String address = addressList.get(nextInt);
        String actualURL = address + uri + "?" + requestURI.getQuery();

        //restTemplate.getForObject(actualURL, String.class);
        //自定义实现
        URL url = new URL(actualURL);
        URLConnection urlConnection = url.openConnection();
        // 响应头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 响应主体
        InputStream responseBody = urlConnection.getInputStream();
        return new SimpleClientHttpResponse(httpHeaders, responseBody);
    }

    private static class SimpleClientHttpResponse implements ClientHttpResponse {

        private HttpHeaders headers;

        private InputStream body;

        public SimpleClientHttpResponse(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 200;
        }

        @Override
        public String getStatusText() throws IOException {
            return "OK";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
