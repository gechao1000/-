## No Authorization Server Support

> https://spring.io/blog/2019/11/14/spring-security-oauth-2-0-roadmap-update

OAuth 2.0 Migration Guide

> https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide

moving OAuth 2.0 Clients and Resource Servers from Spring Security OAuth 2.x to Spring Security 5.2.x

## JWT,JWS与JWE区别

传统web认证方式：基于服务器session+浏览器cookie

单点登陆模式SSO：基于OAuth2等开放授权协议

JWT：Json Web Token

#### 1. JSON Web Signature（JWS）

> jwt的一种实现，保证数据完整性

```
头部（Header）
JWT的最基本的信息，例如其类型以及签名所用的算法等
JSON内容要经Base64 编码生成字符串成为Header
		
载荷（PayLoad）
iss:签发者
sub:所面向的用户
aud:收该JWT的一方
exp:什么时候过期，这里是一个Unix时间戳
iat:在什么时候签发的
自定义数据：比如用户id或用户名

签名（signature）
通过header中声明的加密方式，使用密钥secret进行加密，生成签名
```

#### 2. JSON Web Encryption（JWE）

> jwt的一种实现，同时保证了数据完整性和安全性
>
> 计算过程相对繁琐，不够轻量级，因此适合与数据传输而非token认证

```
JOSE：与JWS头部相同
 
CEK：生成一个随机的Content Encryption Key

JWE Encrypted Key：使用RSAES-OAEP 加密算法，用公钥加密CEK

生成JWE初始化向量

使用AES GCM加密算法对明文部分进行加密生成密文Ciphertext,算法会随之生成一个128位的认证标记Authentication Tag

对五个部分分别进行base64编码
```

