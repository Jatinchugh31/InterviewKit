package DesignPattern.mediator;

public class SmartHomeExample {
    public static void main(String[] args) {
        HomeAutomationMediator mediator = new HomeAutomationMediator();
        Light light = new Light(mediator);
        Thermostat thermostat = new Thermostat(mediator);

        mediator.setDevices(light, thermostat);
        mediator.notify(light, "MOTION_DETECTED");
    }

}
