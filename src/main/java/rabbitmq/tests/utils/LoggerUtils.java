package rabbitmq.tests.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

  public static Logger loggerFor(Object obj) {
    return LoggerFactory.getLogger(obj.getClass());
  }
}
