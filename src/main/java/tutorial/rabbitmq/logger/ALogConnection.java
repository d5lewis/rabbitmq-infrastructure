package tutorial.rabbitmq.logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.TimeoutException;

public class ALogConnection
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    protected final String routingKey;
    protected Channel channel;

    protected ALogConnection(String key)
    {
        routingKey = key;
        setupConnection();
    }

    private void setupConnection()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.HOST);

        try
        {
        Connection connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(Constants.EXCHANGE_NAME, Constants.TYPE);
        } catch (IOException | TimeoutException e)
        {
            logger.warn("Error setting up connection", e);
        }
    }

    public void closeChannel()
    {
        try
        {
            channel.close();
        } catch (IOException | TimeoutException e)
        {
            logger.warn("Error closing channel", e);
        }
    }
}
