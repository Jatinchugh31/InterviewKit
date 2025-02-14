package src.DesignPattern.decorator.logger;

import java.time.LocalDateTime;

public class TimeStampLoggerDecorator extends LoggerDecorator {

    public TimeStampLoggerDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String message) {
        String timestampedMessage = LocalDateTime.now() + " - " + message;
        super.log(timestampedMessage);
    }
}
