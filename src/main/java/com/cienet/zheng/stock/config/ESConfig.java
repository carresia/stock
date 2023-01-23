//package com.cienet.zheng.stock.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.erhlc.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author zhengshan
// * @description
// * @create 2022/12/26
// */
//@Configuration
//public class ESConfig extends AbstractElasticsearchConfiguration {
//
//    private String passwd;
//    private String username;
//
//    private CredentialsProvider defaultCredentialsProvider(String ... tag) {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        if(tag != null && tag.length > 0 && tag[0].equals("log")){
//            username = "mi_es_public";
//            passwd = "d47e47676d79c6d6";
//        }
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, passwd));
//        return credentialsProvider;
//    }
//
//    private RequestConfig defaultRequestConfig() {
//        return RequestConfig.custom().setSocketTimeout(2 * 60 * 1000).setConnectTimeout(10 * 1000).build();
//    }
//    @Bean
//    public RestHighLevelClient newElasticsearchClient() {
//        HttpHost httpHost = new HttpHost("c3middle.api.es.srv", 80);
//        return new RestHighLevelClient(RestClient.builder(httpHost)
//                .setHttpClientConfigCallback(httpAsyncClientBuilder ->
//                        httpAsyncClientBuilder
//                                .setMaxConnPerRoute(500)
//                                .setMaxConnTotal(500)
//                                .setDefaultRequestConfig(defaultRequestConfig())
//                                .setDefaultCredentialsProvider(defaultCredentialsProvider())
//                                .setKeepAliveStrategy((response, context) -> TimeUnit.MINUTES.toMillis(2))
//                )
//        );
//    }
//
////    @Bean("newElasticsearchRestTemplate")
////    public ElasticsearchRestTemplate newElasticsearchRestTemplate() {
////        return new ElasticsearchRestTemplate(newElasticsearchClient(), elasticsearchConverter(), resultsMapper());
////    }
//
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        return null;
//    }
//}
