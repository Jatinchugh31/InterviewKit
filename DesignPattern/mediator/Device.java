package DesignPattern.mediator;

abstract public class Device {
    protected SmartHomeMediator mediator;

    public Device(SmartHomeMediator mediator) {
        this.mediator = mediator;
    }
}
