package src.DesignPattern.mediator;

public class Thermostat extends Device {

    int temperature;

    public Thermostat(SmartHomeMediator mediator) {
        super(mediator);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }


}
