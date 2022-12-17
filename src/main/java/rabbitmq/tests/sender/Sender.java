package rabbitmq.tests.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.tests.utils.QueueDeclarator;

public class Sender {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();

    factory.setHost("127.0.0.1"); // host_IP

    try (
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel()
    ) {
      System.out.println("\nThe [connection] and [channel] are open? " + connection.isOpen() + "/" + channel.isOpen());

      QueueDeclarator queueDeclarator = new QueueDeclarator();

      if (!queueDeclarator.declare(channel)) {
        return;
      }

      String message = "a message";
      channel.basicPublish("", queueDeclarator.getQueueName(), null, message.getBytes());

      System.out.println("Sent: [" + message + "]");
    }
  }
}