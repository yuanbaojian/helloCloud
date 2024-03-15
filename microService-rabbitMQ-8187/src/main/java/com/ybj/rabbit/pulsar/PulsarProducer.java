import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PulsarProducerService {

    @Autowired
    private PulsarProducer pulsarProducer;

    public void sendMessage(String message) {
        MessageChannel messageChannel = pulsarProducer.pulsarOutput();
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
