A **real-world example** of the **Factory Method Pattern** could be in a **payment processing system** where different
payment methods (e.g., Credit Card, PayPal, Bank Transfer) need to be processed. Each payment method requires different
logic for processing transactions, but the system should provide a uniform interface for interacting with any of them.

### **Example: Payment Processing System**

#### **Problem**:

Suppose you need to implement a payment gateway system where customers can pay via different methods like **Credit Card
**, **PayPal**, and **Bank Transfer**. Each payment method requires different processing logic, but you want to provide
a common interface for handling payments.

#### **Solution Using Factory Method**:

The **Factory Method** will help us create an instance of the correct payment processing class (like
`CreditCardPayment`, `PaypalPayment`, etc.) based on the payment type chosen by the user. This keeps the system flexible
and easy to extend by adding new payment methods in the future.

---

### **Code Example** (Java):

```java
// Step 1: Define the Payment interface
interface Payment {
    void processPayment(double amount);
}

// Step 2: Implement different types of payments

class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}

class PaypalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class BankTransferPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Bank Transfer payment of $" + amount);
    }
}

// Step 3: Create the PaymentFactory abstract class (the Factory Method)
abstract class PaymentFactory {
    public abstract Payment createPayment();
}

// Step 4: Create concrete factories for each payment method
class CreditCardPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}

class PaypalPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PaypalPayment();
    }
}

class BankTransferPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new BankTransferPayment();
    }
}

// Step 5: Client code to use the Factory Method
public class PaymentSystem {
    public static void main(String[] args) {
        // Simulate choosing a payment method
        String paymentMethod = "PayPal";  // This could come from user input, for example.

        PaymentFactory factory = null;

        switch (paymentMethod) {
            case "Credit Card":
                factory = new CreditCardPaymentFactory();
                break;
            case "PayPal":
                factory = new PaypalPaymentFactory();
                break;
            case "Bank Transfer":
                factory = new BankTransferPaymentFactory();
                break;
            default:
                System.out.println("Invalid Payment Method");
                return;
        }

        // Create the appropriate payment object using the factory
        Payment payment = factory.createPayment();
        payment.processPayment(100.0);  // Process payment of $100
    }
}
```

### **Explanation of the Code**:

1. **Payment Interface**:
    - The `Payment` interface defines a `processPayment()` method, which all payment classes will implement. This method
      is common to all payment types, allowing us to treat different types of payments uniformly.

2. **Concrete Payment Classes**:
    - `CreditCardPayment`, `PaypalPayment`, and `BankTransferPayment` implement the `Payment` interface. Each class
      contains its own implementation of how to process the payment for that specific payment type.

3. **PaymentFactory (Factory Method)**:
    - The `PaymentFactory` class defines the `createPayment()` method, which will be overridden in concrete factories to
      return an instance of the correct payment type.

4. **Concrete Factories**:
    - `CreditCardPaymentFactory`, `PaypalPaymentFactory`, and `BankTransferPaymentFactory` are concrete implementations
      of the `PaymentFactory`. Each one creates an instance of a specific payment type.

5. **Client Code**:
    - The client (`PaymentSystem`) chooses a payment method (like "PayPal" in the example) and uses the corresponding
      factory to create the appropriate `Payment` object.
    - The client then calls `processPayment()` on the created object to process the payment.

---

### **Benefits of Using the Factory Method**:

- **Flexibility**: The system can easily accommodate new payment methods by simply creating a new `Payment`
  implementation and a corresponding `PaymentFactory` without modifying existing code.
- **Separation of Concerns**: The client code doesn't need to know the details of how each payment method works. It just
  asks the factory to create a payment method.
- **Extensibility**: New payment types (e.g., `BitcoinPayment`, `ApplePayPayment`) can be added with minimal changes to
  existing code.

### **Example in the Real World**:

- **Payment Gateways**: Similar to how payment systems work (PayPal, Stripe, Square), where the payment processor is
  abstracted into different modules. Depending on the user's choice, the system creates a corresponding handler (factory
  method) for payment processing.

- **UI Elements**: If you are building a GUI framework, you might have different types of buttons (e.g.,
  `WindowsButton`, `MacButton`, `LinuxButton`). The factory method can help create platform-specific buttons without the
  client code needing to know the specifics of each type.

---

### **Conclusion**:

The **Factory Method Pattern** is used to **decouple object creation** from the client code, making it easier to
maintain, extend, and modify the system. By using factories, you can create objects dynamically without hard-coding the
class type, which is essential in scenarios where there are multiple possible classes or configurations, such as a *
*payment gateway system** with various payment methods.