package tutorial.rabbitmq;

import tutorial.rabbitmq.logger.MessageSender;
import tutorial.rabbitmq.logger.MessageReceiver;
import util.Constants;

public class RabbitMqApp
{
    private static final String KEY = "object";

    public static void main(String[] argv)
    {
        for (String arg : argv)
        {
            if (arg.contains(Constants.EMIT))
            {
                MessageSender.sendMessage(KEY, "This is a test message");
            }

            if (arg.contains(Constants.RECEIVE))
            {
                MessageReceiver.consumeMessages(KEY);
            }
        }
    }
}
