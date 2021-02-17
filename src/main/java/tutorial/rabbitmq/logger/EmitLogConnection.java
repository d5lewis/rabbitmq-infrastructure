package tutorial.rabbitmq.logger;

import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Constants;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

public class EmitLogConnection extends ALogConnection
{
    private static final Logger logger = LoggerFactory.getLogger((MethodHandles.lookup().lookupClass()));

    public EmitLogConnection(String key)
    {
        super(key);
    }

    public void sendMessage(String message) throws IOException
    {
        channel.basicPublish(Constants.EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
        logger.info(" [x] Sent '{}':'{}'", routingKey, message);
    }
}