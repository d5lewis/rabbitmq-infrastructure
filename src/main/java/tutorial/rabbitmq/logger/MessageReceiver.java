package tutorial.rabbitmq.logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageReceiver
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    public static void consumeMessages(String key)
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.HOST);

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel())
        {
            channel.exchangeDeclare(Constants.EXCHANGE_NAME, Constants.TYPE);
            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName, Constants.EXCHANGE_NAME, key);
            logger.info(" [*] Waiting for messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

                logger.info(" [x] Received '{}':'{}'", delivery.getEnvelope().getRoutingKey(), message);
            };

            try
            {
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                });
            } catch (IOException e)
            {
                logger.warn("Error consuming message from queue", e);
            }
        } catch (IOException | TimeoutException e)
        {
            logger.warn("Error setting up connection", e);
        }
    }
}