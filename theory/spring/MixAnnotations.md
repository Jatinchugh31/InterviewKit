1. Core Annotations
   These annotations are used for general configuration, dependency injection, and component scanning.

@Component: Marks a class as a Spring-managed component. The class will be detected and registered as a bean during classpath scanning.

@Service: A specialization of the @Component annotation used to define a service layer in a Spring application.

@Repository: A specialization of the @Component annotation, used to define a DAO (Data Access Object) class in Spring.

@Controller: Marks a class as a Spring MVC controller.

@RestController: A convenience annotation for creating RESTful controllers. It combines @Controller and @ResponseBody annotations.

@Autowired: Marks a field, setter, or constructor for dependency injection. Spring will inject the required bean automatically.

@Value: Injects values into fields from configuration files (e.g., application.properties or application.yml).

@Qualifier: Used alongside @Autowired to specify which bean should be injected when there are multiple candidates.

@Bean: Defines a Spring bean within a configuration class.

@Configuration: Marks a class as a source of bean definitions. This is used with Java-based configuration.

@Scope: Specifies the scope of a Spring bean (e.g., singleton, prototype, session, request).

@Lazy: Indicates that the bean should be lazily initialized. This is typically used for beans that are expensive to create and should only be initialized when they are actually needed.

@PostConstruct: Marks a method to be executed after the bean's properties have been set (i.e., after dependency injection).

@PreDestroy: Marks a method to be executed just before a bean is destroyed.



2. Spring MVC / Web Annotations
   These annotations are used for web applications, especially with Spring MVC and Spring WebFlux.

@RequestMapping: The base annotation for mapping HTTP requests to handler methods of MVC controllers.

@GetMapping: A shorthand for @RequestMapping(method = RequestMethod.GET). It is used to handle GET HTTP requests.

@PostMapping: A shorthand for @RequestMapping(method = RequestMethod.POST). It is used to handle POST HTTP requests.

@PutMapping: A shorthand for @RequestMapping(method = RequestMethod.PUT). It is used to handle PUT HTTP requests.

@DeleteMapping: A shorthand for @RequestMapping(method = RequestMethod.DELETE). It is used to handle DELETE HTTP requests.

@PatchMapping: A shorthand for @RequestMapping(method = RequestMethod.PATCH). It is used to handle PATCH HTTP requests.

@RequestParam: Binds request parameters to method parameters in controller methods.

@PathVariable: Binds URI template variables to method parameters in controller methods.

@RequestBody: Binds the HTTP request body to a method parameter in controller methods. Used for RESTful services.

@ResponseBody: Indicates that the return value of a method should be used as the response body of the HTTP request.

@ResponseStatus: Marks a method or exception class to specify the HTTP status code for the response.

@ModelAttribute: Binds a method parameter or method return value to a model attribute, which is then available in the view.

@SessionAttributes: Stores model attributes in the session.

@ExceptionHandler: Used to handle specific exceptions in controller methods.

@CrossOrigin: Enables Cross-Origin Resource Sharing (CORS) for specific controllers or handler methods.




These annotations are used to define aspects in your application for cross-cutting concerns like logging, transactions, and security.

@Aspect: Marks a class as an aspect in Spring AOP, which contains the cross-cutting logic (advice).

@Before: Specifies that the annotated method should be executed before the join point (method execution) in an aspect.

@After: Specifies that the annotated method should be executed after the join point, regardless of whether the method execution was successful or not.

@AfterReturning: Specifies that the annotated method should be executed after the join point completes successfully.

@AfterThrowing: Specifies that the annotated method should be executed if the join point throws an exception.

@Around: Specifies that the annotated method should surround the join point, meaning it runs both before and after the method execution.

@EnableAspectJAutoProxy: Enables support for handling aspects in Spring.


4. Transaction Management Annotations
   These annotations are used to manage transactions in Spring.

@Transactional: Marks a method or class for transaction management. It ensures that the method runs in a transactional context, and commits or rolls back depending on the outcome.

@EnableTransactionManagement: Enables annotation-driven transaction management in Spring.




5. Spring Boot Specific Annotations
   These annotations are specific to Spring Boot applications, designed to simplify configuration and setup.

@SpringBootApplication: A convenience annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan to bootstrap a Spring Boot application.

@EnableAutoConfiguration: Tells Spring Boot to automatically configure beans based on the dependencies present in the classpath.

@ComponentScan: Tells Spring where to search for annotated components like @Component, @Service, @Repository, etc.

@ConfigurationProperties: Binds external configuration properties (e.g., from application.properties) to a Spring bean.



. Spring Security Annotations
These annotations are used in Spring Security to handle authentication, authorization, and security-related concerns.

@EnableWebSecurity: Enables Spring Security’s web security support.

@Secured: Restricts access to methods or classes based on roles or permissions.

@PreAuthorize: Allows for expression-based authorization, where the access control is based on SpEL (Spring Expression Language).

@RolesAllowed: Specifies that a method can only be accessed by users with one or more roles.

@PermitAll: Specifies that a method is accessible by all users, regardless of roles or permissions.

@DenyAll: Specifies that a method is restricted to all users.

