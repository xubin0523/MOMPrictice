package metaq;

/**
 * Created by mark on 7/8/14.
 */
public interface MetaqMsgExecutorInterface {

    boolean execute(Object message, String tag);

    boolean execute(Object message);
}
