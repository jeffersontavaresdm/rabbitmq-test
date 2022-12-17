package rabbitmq.tests.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;

import java.io.IOException;

public class QueueDeclarator {

  private final static String QUEUE_NAME = "QUEUE_TEST";

  public boolean declare(Channel channel) {
    AMQP.Queue.DeclareOk queue;

    try {
      queue = channel.queueDeclare(
        QUEUE_NAME,
        false,
        false,
        false,
        null
      );
    } catch (IOException exception) {
      System.out.println("Error declaring queue. Error: {" + exception.getMessage() + "}");
      return false;
    }

    System.out.println("Queue declared successfully! Queue name: [" + queue.getQueue() + "]");
    return true;
  }

  public String getQueueName() {
    return QUEUE_NAME;
  }
}
