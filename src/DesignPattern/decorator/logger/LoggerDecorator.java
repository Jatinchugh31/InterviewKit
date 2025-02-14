package src.DesignPattern.decorator.logger;

public abstract class LoggerDecorator extends ConsoleLogger{
  protected Logger logger;
  public LoggerDecorator(Logger logger) {
      this.logger = logger;
  }

    @Override
    public void log(String message) {
         logger.log(message);
    }
}
