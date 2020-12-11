###### 环境

```
# 依赖
elasticsearch-rest-high-level-client

@Bean(destroyMethod = "close")
public RestHighLevelClient highLevelClient() {
    RestClientBuilder builder = RestClient.builder(
            new HttpHost("192.168.115.128", 9200, "http")
    );
    return new RestHighLevelClient(builder);
}
```

