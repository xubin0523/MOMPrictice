package metaq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.taobao.metaq.client.MetaProducer;

/**
 * 应用唯一的Producer
 * Created by mark on 7/4/14.
 */
public class NoahMetaProducer {
    private MetaProducer metaProducer;
    private String       producer_group_name;

    public void setProducer_group_name(String producer_group_name) {
        this.producer_group_name = producer_group_name;
    }

    public void setMetaProducer(MetaProducer metaProducer) {
        this.metaProducer = metaProducer;
    }

    public void start() throws MQClientException {
        metaProducer = new MetaProducer(producer_group_name);
        metaProducer.start();
    }

    public SendResult send(Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        return metaProducer.send(msg);
    }

    public void shutdown() {
        if (null != metaProducer) {
            metaProducer.shutdown();
        }
    }
}
