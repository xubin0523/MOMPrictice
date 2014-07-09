package metaq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by mark on 7/4/14.
 */
public class MetaMsgListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
        MessageExt msg = msgs.get(0);
        if (msg.getTopic().equals("noahTestTopic")) {
            // 执行TopicTest1的消费逻辑
            if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                // 执行TagA的消费
                System.out.println("noahTestTopic TagA");
            }

        }
        else if (msg.getTopic().equals("noahTestTopic2")) {
            System.out.println("noahTestTopic2");
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
