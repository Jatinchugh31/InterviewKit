we are building a logger , using a decorator design pattern ,


decorator design pattern add a new behaviour during the run time without changing the existing one ,
Decorator design pattern use Aggregation for implementation .


we need one , Component Interface   -> Concrete Component
Base Decorator -> Concrete Decorators



Example

Base Component: The core notification interface or class that defines a method to send notifications.
Concrete Component: The primary implementation of the base notification system, e.g., basic message notifications.
Decorator: Abstract class or interface that extends the base notification functionality and adds additional features (e.g., email, SMS).
Concrete Decorators: Specific implementations of additional features like email, SMS, and push notifications.


Design Steps
1. Define the Component Interface
This interface declares a common method, such as send(String message).

2. Create the Base Component
The base class implements the Notification interface with basic functionality.

3. Create the Abstract Decorator
The decorator class implements the Notification interface and contains a reference to the base component.

4. Create Concrete Decorators
Each decorator extends the base functionality (e.g., adding email, SMS, or push notifications).

5. Dynamically Combine Decorators
Using composition, multiple decorators can wrap the base component.

