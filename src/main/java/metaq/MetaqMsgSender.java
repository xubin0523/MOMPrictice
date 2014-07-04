package metaq;

/**
 * Created by mark on 7/4/14.
 */
public interface MetaqMsgSender {
    /**
     * send
     * @param object 消息对象
     * @param tag 消息业务类型
     * @param key 消息唯一标示，方便运维排查问题。如果不设置Key，则无法定位消息丢失原因。
     * @return
     */
    boolean send(Object object, String tag, String key);


    /**
     * send
     * @param object 消息对象
     * @param tag 消息业务类型
     * @return
     */
    boolean send(Object object, String tag);
}
