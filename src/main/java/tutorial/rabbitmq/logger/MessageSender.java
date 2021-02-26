package tutorial.rabbitmq.logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageSender
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    public static void sendMessage(String key, String message)
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.HOST);

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel())
        {
            channel.exchangeDeclare(Constants.EXCHANGE_NAME, Constants.TYPE);
            channel.basicPublish(Constants.EXCHANGE_NAME, key, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            logger.info(" [x] Sent '{}':'{}'", key, message);
        } catch (IOException | TimeoutException e)
        {
            logger.warn("Error setting up connection", e);
        }
    }
}