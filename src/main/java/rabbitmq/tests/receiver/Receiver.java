package rabbitmq.tests.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.tests.utils.QueueDeclarator;

import java.nio.charset.StandardCharsets;

public class Receiver {

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();

    factory.setHost("localhost"); // host_name

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    System.out.println("The [connection] and [channel] are open? " + connection.isOpen() + "/" + channel.isOpen());

    QueueDeclarator queueDeclarator = new QueueDeclarator();

    if (!queueDeclarator.declare(channel)) {
      return;
    }

    System.out.println("\nWaiting for messages...\n");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

      System.out.println("Received: [" + message + "]");
    };

    channel.basicConsume(
      queueDeclarator.getQueueName(),
      true,
      deliverCallback,
      consumerTag -> {}
    );
  }
}