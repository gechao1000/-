1. 通过java 接口注解的方式调用 http请求
```plain
# 依赖
spring-cloud-starter-openfeign
启动类 @EnableFeignClients
```
2. 使用
```plain
@FeignClient(value = "nacos-client-feign",   fallback = NacosClientHystrix.class)
public interface NacosClientFeign {
@GetMapping(value = "/stock")
String getStock(@RequestParam(value = "name") String name);
}
// 熔断
@Component
public class NacosClientHystrix implements NacosClientFeign {
@Override
public String getStock(String name) {
return "进入Hystrix熔断方法";
}
}
// 调用
@Autowired NacosClientFeign nacosClientFeign;
```
3. 负载均衡策略
```plain
nacos-client-feign:
ribbon:
NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```
4. 超时熔断
```plain
feign:
hystrix:
enabled: true

ribbon:
#建立连接之后，读取响应资源超时时间
ReadTimeout: 5000
#建立连接超时时间
ConnectTimeout: 500
hystrix:
command:
default:
execution:
timeout:
enabled: true
isolation:
thread:
timeoutInMilliseconds: 5000
```
