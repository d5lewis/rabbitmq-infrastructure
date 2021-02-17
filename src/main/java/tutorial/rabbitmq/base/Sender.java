package tutorial.rabbitmq.base;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

public class Sender
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    public static void main(String[] argv) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel())
        {
            channel.queueDeclare(Constants.QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";

            channel.basicPublish("", Constants.QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

            logger.info(" [x] Sent '{}'", message);
        }
    }
}