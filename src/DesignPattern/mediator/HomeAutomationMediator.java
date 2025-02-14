package src.DesignPattern.mediator;

public class HomeAutomationMediator implements SmartHomeMediator {

    private Light light;
    private Thermostat thermostat;

    public void setDevices(Light light, Thermostat thermostat) {
        this.light = light;
        this.thermostat = thermostat;
    }


    @Override
    public void notify(Device sender, String event) {
        if (event.equals("MOTION_DETECTED")) {
            light.turnOn();
            thermostat.setTemperature(22);
        }
    }
}
