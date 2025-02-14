Details explanation https://www.baeldung.com/hibernate-second-level-cache 

we have two type of cache  first Level cahce , second level cache , 

1. first level cache are session level (Transactions scope)
2. by default it is on
   Session session = sessionFactory.openSession();
   Employee emp1 = session.get(Employee.class, 1); // Hits DB
   Employee emp2 = session.get(Employee.class, 1); // Retrieved from Cache (no DB hit)
3. once session is close cache is clear 


Second level cache:
Conversely, a second-level cache is SessionFactory-scoped, meaning it’s shared by all sessions created with the same session factory. When an entity instance is looked up by its id (either by application logic or by Hibernate internally, e.g., when it loads associations to that entity from other entities), and second-level caching is enabled for that entity, the following happens:

If an instance is already present in the first-level cache, it’s returned from there.
If an instance isn’t found in the first-level cache, and the corresponding instance state is cached in the second-level cache, then the data is fetched from there and an instance is assembled and returned.
Otherwise, the necessary data are loaded from the database and an instance is assembled and returned.

Hibernate provide the org.hibernate.cache.spi.RegionFactory    which store all the details of second level cache 
as hibernate is not aware of second level cache  , it is depend on us which one we need to use 
we have few type of cache like
Uses third-party providers like Ehcache, Infinispan, Caffeine.



Enable second level cache ,

hibernate.cache.use_second_level_cache=true
hibernate.cache.region.factory_class=org.hibernate.cache.jcache.internal.JCacheRegionFactory
hibernate.javax.cache.uri=ehcache.xml
hibernate.javax.cache.provider=org.ehcache.jsr107.EhcacheCachingProvider



We can use @Cache annoation to mark the entity as cachable
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Foo {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
private long id;

    @Column(name = "NAME")
    private String name;
    
    // getters and setters
}

or
Query query = session.createQuery("FROM Employee");
query.setCacheable(true);



Cache Concurrency Strategy
Based on use cases, we’re free to pick one of the following cache concurrency strategies:

READ_ONLY: Used only for entities that never change (an exception is thrown if an attempt to update such an entity is made). It’s very simple and performative. It’s suitable for static reference data that doesn’t change.
NONSTRICT_READ_WRITE: Cache is updated after the transaction that changed the affected data has been committed. Thus, strong consistency isn’t guaranteed, and there’s a small time window in which stale data may be obtained from the cache. This kind of strategy is suitable for use cases that can tolerate eventual consistency.
READ_WRITE: This strategy guarantees strong consistency, which it achieves by using ‘soft’ locks. When a cached entity is updated, a soft lock is stored in the cache for that entity as well, which is released after the transaction is committed. All concurrent transactions that access soft-locked entries will fetch the corresponding data directly from the database.
TRANSACTIONAL: Cache changes are done in distributed XA transactions. A change in a cached entity is either committed or rolled back in both the database and cache in the same XA transaction.


collction are not cachable by default we need annoate Collections as well for cache
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@OneToMany
private Collection<Bar> bars;
