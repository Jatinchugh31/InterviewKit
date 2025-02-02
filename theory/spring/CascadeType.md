Cascade Types Overview
JPA provides several cascade types that can be applied using the cascade attribute in association annotations (e.g.,
@OneToOne, @OneToMany, @ManyToOne, @ManyToMany). The common cascade types are:

CascadeType.PERSIST
Behavior: When the parent entity is persisted (i.e., saved), the associated child entities are also persisted.
Use Case: Use this when you want new entities to be saved automatically along with the parent.
CascadeType.MERGE
Behavior: When the parent entity is merged (i.e., updated), the associated child entities are also merged.
Use Case: Useful for synchronizing the state of detached entities with the current persistence context.
CascadeType.REMOVE
Behavior: When the parent entity is removed (i.e., deleted), the associated child entities are also removed.
Use Case: Use this to automatically delete children when the parent is deleted. Be cautious with this cascade as it can
remove more data than intended.
CascadeType.REFRESH
Behavior: When the parent entity is refreshed (i.e., reloaded from the database), the associated child entities are also
refreshed.
Use Case: Ensures that the entire object graph is updated with the latest state from the database.
CascadeType.DETACH
Behavior: When the parent entity is detached from the persistence context, the associated child entities are also
detached.
Use Case: Useful when you want to remove all objects from the current persistence context.
CascadeType.ALL
Behavior: A shorthand that applies all of the above cascade types (i.e., PERSIST, MERGE, REMOVE, REFRESH, and DETACH).
Use Case: Convenient when you want all operations on the parent to cascade to its children.
