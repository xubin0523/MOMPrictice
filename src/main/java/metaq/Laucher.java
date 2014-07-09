package metaq;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by mark on 7/4/14.
 */
public class Laucher {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");

        /**
         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
         */
        try {
            {
//                Message msg = new Message("TopicTest1",// topic
//                        "TagA",// tag
//                        "OrderID001",// key，消息的Key字段是为了唯一标识消息的，方便运维排查问题。如果不设置Key，则无法定位消息丢失原因。
//                        ("Hello MetaQ").getBytes());// body
                MetaqMsgSender producer = (MetaqMsgSender) context.getBean("testSender");
                producer.send("Hello MetaQ", "TagA", "OrderID001");
            }

            {
                MetaqMsgSender producer = (MetaqMsgSender) context.getBean("testSender2");
                producer.send("Hello MetaQ", null, "OrderID001");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
