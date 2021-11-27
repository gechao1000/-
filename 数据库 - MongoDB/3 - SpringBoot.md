https://spring.io/projects/spring-data-mongodb

https://spring.io/guides/gs/accessing-data-mongodb/

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

https://github.com/bitnami/bitnami-docker-mongodb

```yml
version: '2'

services:
  mongodb:
    image: bitnami/mongodb:4.4
    ports:
      - "27017:27017"
    volumes:
      - 'mongodb_data:/bitnami/mongodb'

volumes:
  mongodb_data:
    driver: local
```



实体类

```java
@Data
@NoArgsConstructor
public class Customer {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
```

Repo

```java
@Repository
public interface CustomerRepo extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
```

Repo 测试用例

```
repo.deleteAll();

repo.save(new Customer("Alice", "Smith"));
repo.save(new Customer("Bob", "Smith"));

repo.findAll().forEach(System.out::println);
System.out.println("--------------------------------");

System.out.println(repo.findByFirstName("Alice"));
System.out.println(repo.findByLastName("Smith"));
```



Template

```java
@Repository
private MongoTemplate mongoTemplate;

public List<User> searchUsers(Page page, SearchUsersQuery query) {
    List<AggregationOperation> operations = new ArrayList<>();

    List<Criteria> criteriaList = new ArrayList<>();
    if (!StringUtils.isEmpty(query.getId())) {
        criteriaList.add(Criteria.where("id").is(new ObjectId(query.getId())));
    }
    if (!StringUtils.isEmpty(query.getUsername())) {
        criteriaList.add(Criteria.where("username").regex(query.getUsername(), "i"));
    }
    if (!StringUtils.isEmpty(query.getFullName())) {
        criteriaList.add(Criteria.where("fullName").regex(query.getFullName(), "i"));
    }
    if (!criteriaList.isEmpty()) {
        Criteria userCriteria = new Criteria()
            .andOperator(criteriaList.toArray(new Criteria[0]));
        operations.add(match(userCriteria));
    }

    operations.add(sort(Sort.Direction.DESC, "createdAt"));
    operations.add(skip((page.getNumber() - 1) * page.getLimit()));
    operations.add(limit(page.getLimit()));

    TypedAggregation<User> aggregation = newAggregation(User.class, operations);
    AggregationResults<User> results = mongoTemplate.aggregate(aggregation, User.class);
    return results.getMappedResults();
}
```





Model

```java
@Document(collection = "authors") @Data
public class Author implements Serializable {

    @Id
    private ObjectId id;

    @CreatedBy
    private ObjectId creatorId;
    @LastModifiedBy
    private ObjectId modifierId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private String fullName;
    private String about;
    private String nationality;
    private Set<String> genres = new HashSet<>();

    private Set<ObjectId> bookIds = new HashSet<>();

}
```

