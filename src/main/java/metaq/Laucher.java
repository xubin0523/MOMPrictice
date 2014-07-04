package metaq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Created by mark on 7/4/14.
 */
public class Laucher {
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("metaq-consumer.xml");

    }
}
