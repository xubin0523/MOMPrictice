package metaq;

/**
 * Created by mark on 7/8/14.
 */
public class MetaqMsgExecutor implements MetaqMsgExecutorInterface {

    @Override
    public boolean execute(Object message, String tag) {
        return false;
    }

    @Override
    public boolean execute(Object message) {
        return false;
    }
}
