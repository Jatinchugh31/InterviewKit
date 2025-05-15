Great question! 🔥 In a real-world Spring Boot application, you might need to **connect to multiple databases** — for example, one for transactional data and another for reporting.

Let’s walk through **how to configure multiple `DataSource`s** in Spring Boot, the right way ✅

---

## ✅ Goal: Configure **Multiple DataSources** in Spring Boot

You typically define:
- **Primary DataSource** (default)
- **Secondary (or more) DataSources**

Each with:
- Their own properties
- Separate `EntityManager`, `TransactionManager`, and `JpaRepositories`

---

## 🔧 Step-by-Step Configuration

---

### 🔸 Step 1: Add Dependencies (if using JPA)

```xml
<!-- Spring Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Database drivers -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

---

### 🔸 Step 2: Define `application.properties` or `application.yml`

```properties
# PRIMARY DataSource (default)
spring.datasource.url=jdbc:mysql://localhost:3306/primarydb
spring.datasource.username=root
spring.datasource.password=pass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# SECONDARY DataSource
app.datasource.secondary.url=jdbc:mysql://localhost:3306/secondarydb
app.datasource.secondary.username=root
app.datasource.secondary.password=pass
app.datasource.secondary.driver-class-name=com.mysql.cj.jdbc.Driver
```

---

### 🔸 Step 3: Create Config for Primary DataSource (default)

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.primary.repo",
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.example.primary.model")
                .persistenceUnit("primary")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }
}
```

---

### 🔸 Step 4: Create Config for Secondary DataSource

```java
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.secondary.repo",
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {

    @Bean
    @ConfigurationProperties("app.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource())
                .packages("com.example.secondary.model")
                .persistenceUnit("secondary")
                .build();
    }

    @Bean
    public PlatformTransactionManager secondaryTransactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(secondaryEntityManagerFactory(builder).getObject());
    }
}
```

---

### ✅ Folder Structure Should Be Like:

```
com.example
 ├── primary
 │    ├── model
 │    └── repo
 └── secondary
      ├── model
      └── repo
```

Each entity goes into its respective model package, and JPA repositories go into the correct repo package.

---

## 🔍 Notes

- Use `@Primary` to mark the default DataSource.
- Use `@ConfigurationProperties` to bind custom datasource props.
- Make sure your **repositories** and **entities** are correctly separated per datasource.
- You can do similar setup for **Mongo**, **Redis**, or any other datasource.

---

## ✅ Summary

| Task                          | How to Do It                      |
|-------------------------------|-----------------------------------|
| Multiple DB URLs              | Define separate properties        |
| Separate DataSources          | Create config classes             |
| Entity/Repo separation        | Use different packages            |
| Transaction handling          | Define custom `TransactionManager` |
| Default DataSource            | Use `@Primary`                    |

---

Let me know if you'd like the same setup using **YAML**, **JDBC template**, or even **MongoDB + JPA mixed data sources**!