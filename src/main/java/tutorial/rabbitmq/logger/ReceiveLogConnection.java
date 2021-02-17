package tutorial.rabbitmq.logger;

import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

public class ReceiveLogConnection extends ALogConnection
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    private String queueName;

    public ReceiveLogConnection(String key)
    {
        super(key);

        try
        {
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, Constants.EXCHANGE_NAME, key);
        } catch (IOException e)
        {
            logger.warn("Error getting queue", e);
        }
    }

    public void consumeMessages()
    {
        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

            logger.info(" [x] Received '{}':'{}'", delivery.getEnvelope().getRoutingKey(), message);
        };

        try
        {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } catch (IOException e)
        {
            logger.warn("Error consuming message from queue", e);
        }
    }
}