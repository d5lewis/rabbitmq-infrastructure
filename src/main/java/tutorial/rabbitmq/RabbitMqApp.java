package tutorial.rabbitmq;

import tutorial.rabbitmq.logger.EmitLogConnection;
import tutorial.rabbitmq.logger.ReceiveLogConnection;

public class RabbitMqApp
{
    private static final String KEY = "test-key";

    public static void main(String[] argv) throws Exception
    {
        EmitLogConnection logSender = new EmitLogConnection(KEY);
        logSender.sendMessage("This is a test message");
        logSender.closeChannel();

        ReceiveLogConnection logReceiver = new ReceiveLogConnection(KEY);
        logReceiver.consumeMessages();
        logReceiver.closeChannel();
    }
}
