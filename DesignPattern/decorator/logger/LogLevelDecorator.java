package DesignPattern.decorator.logger;

public class LogLevelDecorator extends LoggerDecorator{
   private  String  logLevel;
    public LogLevelDecorator(Logger logger, String logLevel) {
        super(logger);
        this.logLevel = logLevel;
    }

    @Override
    public void log(String message) {
        String levelMessage = "[" + logLevel + "] " + message;
        super.log(levelMessage);
    }
}
