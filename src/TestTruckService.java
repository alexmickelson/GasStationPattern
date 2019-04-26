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



public class TestTruckService {
    private TruckService testtruck;
    static Mockery context;
    static ITimeObservable observer;

    @BeforeClass
    public static void setup(){
        context = new JUnit4Mockery();
        observer = context.mock(ITimeObservable.class);

    }

    @Test
    public void TruckServiceTest_CallTruckGetsCalled(){
        context.checking(new Expectations() {{
            allowing(observer).subscribe(with(any(ITimeObserver.class)));
        }});
        testtruck = new TruckService(observer);
        Tank itank = new Tank(500);
        itank.RetrieveGasFromTank(500);
        testtruck.CallTruck(itank,500);
        int i = 0;
        while(itank.getLevel() < 500){
            testtruck.update(i++);
        }
        assertEquals(true, 500<=itank.getLevel());
    }


    @Test
    public void TruckServiceTest_MultipleTanksGetFilled(){
        context.checking(new Expectations() {{
            allowing(observer).subscribe(with(any(ITimeObserver.class)));
        }});
        testtruck = new TruckService(observer);
        Tank itank = new Tank(500);
        itank.RetrieveGasFromTank(500);
        Tank itank2 = new Tank(700);
        itank2.RetrieveGasFromTank(700);
        testtruck.CallTruck(itank,500);
        testtruck.CallTruck(itank2,700);
        int i = 0;
        while(itank.getLevel() < 500){
            testtruck.update(i++);
        }
        assertEquals(true, 500<=itank.getLevel() && 700 >= itank2.getLevel());
    }

}
