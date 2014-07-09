package metaq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.taobao.metaq.client.MetaPushConsumer;

import java.util.List;

/**
 * Created by mark on 7/8/14.
 */
public class MetaqMsgReceiver {
    private MetaPushConsumer                metaPushConsumer;
    public  String                          groupName = "noah_consumer_group_name";
    private List<String>                    topicList;
    private MetaMsgListener                 metaMsgListener;
    private int                             consumeMessageBatchMaxSize = 1;

    public void init() throws MQClientException {
        metaPushConsumer = new MetaPushConsumer(groupName);
        if (topicList == null || topicList.isEmpty()) {
            throw new RuntimeException("topicList=" + topicList);
        }
        for (String topic : topicList) {
            try {
                metaPushConsumer.subscribe(topic, "*");
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
        metaPushConsumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        metaPushConsumer.registerMessageListener(metaMsgListener);
        metaPushConsumer.start();

    }

    public void showdown() {
        if (null != metaPushConsumer) {
            metaPushConsumer.shutdown();
        }
    }

    public void setMetaPushConsumer(MetaPushConsumer metaPushConsumer) {
        this.metaPushConsumer = metaPushConsumer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTopicList(List<String> topicList) {
        this.topicList = topicList;
    }

    public void setMetaMsgListener(MetaMsgListener metaMsgListener) {
        this.metaMsgListener = metaMsgListener;
    }

    public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }
}
