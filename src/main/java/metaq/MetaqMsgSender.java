package metaq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

/**
 * Message Sender
 * Created by mark on 7/4/14.
 */
public class MetaqMsgSender implements MetaqMsgSenderInterface {
    private NoahMetaProducer noahMetaProducer;
    private String topic;
    private int _1MB = 1024 * 1024;

    @Override
    public boolean send(Object object, String tag, String key) {
        byte[] body = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            body = bos.toByteArray();
            oos.close();
            bos.close();

            if (body.length >= _1MB) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Message msg = new Message(topic, tag, key, body);
            SendResult sendResult = noahMetaProducer.send(msg);
            System.out.println(sendResult);
            if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean send(Object object, String tag) {
        String key = UUID.randomUUID().toString();
        return send(object, tag, key);
    }

    public NoahMetaProducer getNoahMetaProducer() {
        return noahMetaProducer;
    }

    public void setNoahMetaProducer(NoahMetaProducer noahMetaProducer) {
        this.noahMetaProducer = noahMetaProducer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
