import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PulsarMessageListener {

    @Autowired
    private PulsarConfig pulsarConfig;

    public PulsarMessageListener() {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(pulsarConfig.getServiceUrl())
                .build();

        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .subscriptionName("my-subscription")
                .subscribe(pulsarConfig.getTopic());

        while (true) {
            try {
                Message<String> msg = consumer.receive();
                String message = msg.getValue();
                // 在这里处理消息，例如将其记录到日志或进行其他操作
                consumer.acknowledge(msg);
            } catch (PulsarClientException e) {
                e.printStackTrace();
            }
        }
    }
}
