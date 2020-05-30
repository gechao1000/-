密码

```
PasswordEncoderFactories.createDelegatingPasswordEncoder();

if (!passwordEncoder.matches(rawPassword,encodedPassword)){
	throw new BadCredentialsExceptionBase("账号或密码不正确");
}
```

依赖

```
<dependency>
  <groupId>org.springframework.security.oauth.boot</groupId>
  <artifactId>spring-security-oauth2-autoconfigure</artifactId>
  <version>2.3.0.RELEASE</version>
</dependency>
```
