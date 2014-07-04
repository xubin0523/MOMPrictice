package metaq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.taobao.metaq.client.MetaPushConsumer;

/**
 * Created by mark on 7/4/14.
 */
public class NoahMetaConsumer {
    private MetaPushConsumer consumer;

    public NoahMetaConsumer() throws MQClientException {
        consumer = new MetaPushConsumer("noah_consumer_group_name");
        consumer.start();
    }

    public void shutdown() {
        consumer.shutdown();
    }
}
