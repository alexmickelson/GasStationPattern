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

@RunWith(JMock.class)
public class TestPump {
    static Mockery context = new JUnit4Mockery();
    static ITank tank;
    static Pump pump;
    static ICustomer customer;

    @BeforeClass
    public static void setup(){

        tank = context.mock(ITank.class);
        customer = context.mock(ICustomer.class);
        var clock = TimeService.getInstance();
        pump = new Pump(tank, tank, clock, 1);
    }

    @Test
    public void retrieve85FromPump() {

        // expectations
        context.checking(new Expectations() {{
            allowing(tank).RetrieveGasFromTank(35);
            will(returnValue(35.0));
        }});

        var retrieve= pump.Retrieve85Grade(35);



        assertEquals(35.0, retrieve);
    }


    @Test
    public void Retrieve87_retrievesHalfFromEachTak(){
        var amt = 70.0;
        // expectations
        context.checking(new Expectations() {{
            allowing(tank).RetrieveGasFromTank(35);
            will(returnValue(amt/2));
        }});


        assertEquals(amt, pump.Retrieve87Grade(amt));
    }

    @Test
    public void Retrieve89_retrieves89(){
        var amt = 70.0;
        // expectations
        context.checking(new Expectations() {{
            allowing(tank).RetrieveGasFromTank(amt);
            will(returnValue(amt));
        }});


        assertEquals(amt, pump.Retrieve89Grade(amt));
    }


    @Test
    public void pumpTransaction_Customer_Grade85_Cash(){
        var gasAmt = 30.0;
        var price = 2.95;

        pump.price85 = price;
        //set up customer
        context.checking(new Expectations() {{
            allowing(tank).RetrieveGasFromTank(gasAmt);
                will(returnValue(gasAmt));
            allowing(customer).DesiredGrade();
                will(returnValue(GradeEnum.GRADE_85));
            allowing(customer).GetAmountOfGasDesired();
                will(returnValue(gasAmt));
            allowing(customer).GetMoneyType();
                will(returnValue(CurrencyEnum.CASH));
            allowing(customer).GetMaxAvailableMoney();
                will(returnValue(500.0));
        }});

        var reciept = pump.PumpTransaction(customer);

        assertEquals(gasAmt*price , reciept.GetAmountCharged());
        assertEquals(gasAmt ,reciept.GetGasGiven());
        assertEquals(CurrencyEnum.CASH ,reciept.GetPaymentType());
    }

    @Test
    public void CurrencyHandlerFactory_MakesACurrencyHandler_Cash_grade87(){
        var gasAmt = 25.0;
        var price=3.6;

        pump.price87=price;


        context.checking(new Expectations() {{
            allowing(tank).RetrieveGasFromTank(gasAmt);
            will(returnValue(gasAmt));
            allowing(customer).DesiredGrade();
            will(returnValue(GradeEnum.GRADE_87));
            allowing(customer).GetAmountOfGasDesired();
            will(returnValue(gasAmt));
            allowing(customer).GetMoneyType();
            will(returnValue(CurrencyEnum.CASH));
        }});

        var currencyHandler = pump.CurrencyHandlerFactory(customer);

        assertThat(currencyHandler, instanceOf(CashCurrencyHandler.class));
    }

}

/*
1. click on the Project view or unhide it by clicking on the "1: Project" button on the left border of the window or by pressing Alt + 1
2. find your project or sub-module and click on it to highlight it, then press F4, or right click and choose "Open Module Settings" (on IntelliJ 14 it became F12)
3. click on the dependencies tab
4. Click the "+" button on the right and select "Jars or directories..."
5. Find your path and click OK
6. In the dialog with "Choose Categories of Selected File", choose Classes (even if it's properties), press OK and OK again
7. You can now run your application and it will have the selected path in the class path
* */