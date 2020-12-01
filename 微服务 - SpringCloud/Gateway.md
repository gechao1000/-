1. 概念
```plain
# Route
基础元素，由ID、目标URI、断言、过滤器组成
请求到达网关，根据断言匹配路由
# Predicate（断言）
jdk8 函数式接口
匹配条件
# Filter
请求鉴权
```
2. 场景
```plain
请求鉴权，异常处理
# 依赖
spring-cloud-starter-gateway
```
3. 配置文件
```plain
spring:
cloud:
gateway:
default-filters:
# 设置全局的默认过滤器（请求头、响应头、header等，但不会覆盖局部过滤器，并存）
- AddResponseHeader=czbk,welcome
routes:
- id: service-order
uri: lb://service-order
predicates:
- Path=/czbk/v1/order/**
filters:
- AddResponseHeader=czbk,order
- StripPrefix=1
```
4. 自定义过滤器
```plain
# 全局 GlobalFilter, Ordered
# 局部 GatewayFilter, Ordered
# 验证 token
String token = exchange.getRequest().getQueryParams().getFirst(TOKEN);
if (StringUtils.isEmpty(token)) {
log.error("非法访问>>>>>>>>");
exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
return exchange.getResponse().setComplete();
}
return chain.filter(exchange);
```
