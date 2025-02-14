package src.DesignPattern.mediator;

public class Light extends Device {
    public Light(SmartHomeMediator mediator) {
        super(mediator);
    }

    public void turnOn() {
        System.out.println("Light turned on.");
    }

}
