依赖

```
<dependency>
	<groupId>cn.afterturn</groupId>
	<artifactId>easypoi-base</artifactId>
	<version>4.2.0</version>
</dependency>

<!--注解验证-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

限制文件大小

```
# application.properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB


# 处理全局异常
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError(MaxUploadSizeExceededException e) {
        return "文件大小超出限制";
    }
}
```

实体类

```
@Data
@ExcelTarget("UserEntity")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -8339672398564444277L;

    @Excel(name = "电话号码", orderNum = "0")
    private String phone;

    @Excel(name = "姓名", orderNum = "1")
    private String name;

    @Excel(name = "分组", orderNum = "2")
    private String group;

    @Excel(name = "备注", orderNum = "3")
    private String remark;

    @Excel(name = "出生日期", orderNum = "4", importFormat = "yyyy-MM-dd")
    private LocalDate birthday;
    
    /**
     * 注解验证
     */
    @Excel(name = "出生日期", orderNum = "4", importFormat = "yyyy-MM-dd")
    @NotNull
    private LocalDate birthday;
}
```

接口异常处理

```
@PostMapping("upload")
public String upload(HttpServletRequest request) {
	try {
		MultipartHttpServletRequest fileRequest =  (MultipartHttpServletRequest) request;
		MultipartFile file = fileRequest.getFile("file");
		if (file == null || file.isEmpty()) {
			return "没有文件";
		}

		String fileName = file.getOriginalFilename().toLowerCase();
		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
			return "文件类型错误";
		}

		....
	} catch (ClassCastException e) {
		System.out.println(e);
		return "请求错误";
	} catch (Exception e) {
		System.out.println(e);
		return e.toString();
	}
}
```

普通上传

```
ImportParams params = new ImportParams();
params.setTitleRows(1);
params.setKeyIndex(0);

List<UserEntity> list = 
ExcelImportUtil.importExcel(file.getInputStream(), UserEntity.class, params);
```

验证（注解）

```
ImportParams params = new ImportParams();
params.setTitleRows(1);
params.setNeedVerify(true);
ExcelImportResult<UserEntity> result = ExcelImportUtil.importExcelMore(file.getInputStream(), UserVerifyEntity.class, params);

List<UserEntity> list = result.getList();
List<UserEntity> failList = result.getFailList();
```

验证（自定义）

```
ImportParams params = new ImportParams();
params.setTitleRows(1);

params.setNeedVerify(true);
IExcelVerifyHandler<UserEntity> verifyHandler = (UserEntity item) -> {
	if (item.getBirthday() == null) {
		return new ExcelVerifyHandlerResult(false);
	}
	return new ExcelVerifyHandlerResult(true);
};
params.setVerifyHandler(verifyHandler);

// 过滤错误结果
List<UserEntity> list = 
ExcelImportUtil.importExcel(file.getInputStream(), UserEntity.class, params);
```

