A subclass method can throw:
The same checked exception as the superclass method.

A more specific checked exception (a subclass of the superclass exception).

No exception (if it handles the exception inside the method).

A subclass method cannot throw:
A more general checked exception (a superclass exception).

Any checked exception not declared in the superclass method.
Unchecked exceptions (those extending RuntimeException) are not restricted in inheritance. The subclass can throw any
unchecked exception regardless of what the superclass method throws.