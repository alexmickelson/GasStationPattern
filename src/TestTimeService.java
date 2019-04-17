import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


public class TestTimeService {

    static Mockery context;
    static ITimeObserver observer;

    @BeforeClass
    public static void setup(){
        context = new JUnit4Mockery();
        observer = context.mock(ITimeObserver.class);
    }

    @Test
    public void getInstance_getsInstance_SameWhenCalledTwice(){
        var instace1 = TimeService.getInstance();
        var instace2 = TimeService.getInstance();

        assertEquals(instace1, instace2);
        context.checking(new Expectations() {{
            allowing(observer).update(with(any(int.class)));
        }});
        instace1.subscribe(observer);


        assertEquals(true, instace2.unsubscribe(observer));
    }
    @Test
    public void getInstance_Subscribe_Unsubscribe(){
        var instace1 = TimeService.getInstance();
        var instace2 = TimeService.getInstance();

        context.checking(new Expectations() {{
            allowing(observer).update(with(any(int.class)));
        }});
        instace1.subscribe(observer);


        assertEquals(true, instace2.unsubscribe(observer));
    }
}
