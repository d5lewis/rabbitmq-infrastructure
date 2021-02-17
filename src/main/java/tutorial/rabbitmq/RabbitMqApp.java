package tutorial.rabbitmq;

import tutorial.rabbitmq.logger.EmitLogConnection;
import tutorial.rabbitmq.logger.ReceiveLogConnection;
import util.Constants;

public class RabbitMqApp
{
    private static final String KEY = "object";

    public static void main(String[] argv) throws Exception
    {
        for (String arg : argv)
        {
            if (arg.contains(Constants.EMIT))
            {
                EmitLogConnection logSender = new EmitLogConnection(KEY);
                logSender.sendMessage("This is a test message");
                logSender.closeChannel();
            }

            if (arg.contains(Constants.RECEIVE))
            {
                ReceiveLogConnection logReceiver = new ReceiveLogConnection(KEY);
                logReceiver.consumeMessages();
                logReceiver.closeChannel();
            }
        }
    }
}
