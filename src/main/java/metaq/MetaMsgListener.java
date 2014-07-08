package metaq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by mark on 7/4/14.
 */
public class MetaMsgListener implements MessageListenerConcurrently{
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
        MessageExt msg = msgs.get(0);
        if (msg.getTopic().equals("TopicTest1")) {
            // 执行TopicTest1的消费逻辑
            if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                // 执行TagA的消费
            }
            else if (msg.getTags() != null && msg.getTags().equals("TagC")) {
                // 执行TagC的消费
            }
            else if (msg.getTags() != null && msg.getTags().equals("TagD")) {
                // 执行TagD的消费
            }
        }
        else if (msg.getTopic().equals("TopicTest2")) {
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
