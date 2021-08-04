https://www.baeldung.com/jackson-xml-serialization-and-deserialization



依赖

```
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.11.1</version>
</dependency>

```



简单 Bean

```
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class SimpleBean {
	private int x;
	private int y;
}
```



复杂 Bean

```
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class DbSet {

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns:i")
    public final String ns = "http://www.w3.org/2001/XMLSchema-instance";

    @JacksonXmlProperty(isAttribute = true, localName = "xmlns")
    public final String xmlns = "http://schemas.datacontract.org/2004/07/KNet.Data";

    @JacksonXmlElementWrapper(localName = "Rows")
    @JacksonXmlProperty(localName = "DbRow")
    private List<DbRow> rows = new LinkedList<>();
    private int start;
    private int count;  
}
```



测试用例

```
@GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)

@RequestMapping(value = "/similar",
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})

###
GET http://localhost:8080/
Accept: application/xml

###
GET http://localhost:8080/
Accept: application/json
```

