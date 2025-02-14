1. One-to-One Association
   A one-to-one association means that one entity instance is related to exactly one instance of another entity. You can model this with the @OneToOne annotation. Typically, you designate an owning side that contains the foreign key.

Example: User and UserProfile
Imagine each user has one unique profile.


import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    // Owning side: User holds the foreign key reference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile profile;

    // Getters and setters
}

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String bio;
    
    // Optionally, you can map back to User for bidirectional mapping
    @OneToOne(mappedBy = "profile")
    private User user;

    // Getters and setters
}

Key Points:

Owning Side: The User entity is the owner (it has the @JoinColumn), which stores the foreign key.
Cascade: Using cascade = CascadeType.ALL ensures that operations (persist, remove) are cascaded.
Bidirectional: The optional mappedBy attribute in UserProfile defines the inverse side.



. One-to-Many and Many-to-One Associations
A one-to-many relationship means that one entity instance is related to multiple instances of another entity, while the many-to-one side is its inverse.

Example: User and Orders
Each user can have multiple orders.



@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    // One-to-Many: A user can have many orders
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    // Getters and setters
}

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Date orderDate;
    
    // Many-to-One: Each order is associated with one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
}


@OneToMany Side: In the User class, the mappedBy attribute indicates that the Order entity owns the relationship.
@ManyToOne Side: In the Order class, @JoinColumn defines the foreign key column (user_id) linking back to User.
Fetch Type: Often set to LAZY to avoid loading all orders when a user is fetched.


3. Many-to-Many Association
   A many-to-many relationship means that multiple instances of one entity can be associated with multiple instances of another entity. This is implemented using a join table.

Example: Student and Course
A student can enroll in multiple courses, and each course can have many students.


@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // Getters and setters
}

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String courseName;
    
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Getters and setters
}

Key Points:

Join Table: The @JoinTable annotation in the Student entity specifies the join table and join columns.
Bidirectional: In the Course entity, mappedBy indicates that the Student entity owns the relationship.
Set vs. List: Sets are commonly used to avoid duplicate associations.


Additional Considerations
Cascading:
Cascade types (like CascadeType.ALL, CascadeType.PERSIST, etc.) control how operations on one entity affect its related entities.

Fetch Types:

EAGER: Loads related entities immediately.
LAZY: Loads related entities on-demand (default for many associations).
Bidirectional vs. Unidirectional:
Decide if you need navigation from both sides of the relationship. Use mappedBy to indicate the inverse side in bidirectional associations.

Join Columns and Tables:
Use @JoinColumn for one-to-one and many-to-one associations, and @JoinTable for many-to-many associations to specify how the tables relate.

