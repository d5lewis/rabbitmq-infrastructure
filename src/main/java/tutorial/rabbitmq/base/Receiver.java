package tutorial.rabbitmq.base;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

public class Receiver
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    public static void main(String[] argv) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel;

        try (Connection connection = factory.newConnection())
        {
            channel = connection.createChannel();
        }

        channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);
        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.info(" [x] Received '{}'", message);
        };

        channel.basicConsume(Constants.QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}