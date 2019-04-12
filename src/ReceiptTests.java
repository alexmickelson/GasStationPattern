import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JMock.class)
public class ReceiptTests {
    Mockery context = new JUnit4Mockery();

    @Test
    public void ReceiveReceiptTest() {

        // set up
        //ICustomer cust = context.mock(ICustomer.class);

        // expectations
        //We need
        //1 amount charged
        //2 gasgiven
        //3 payment type
        /*context.checking(new Expectations() {{

            oneOf (custReceipt).RetrieveGasFromTank(35); will(returnValue(35.0));
        }});*/


        IReceipt receipt = new Receipt(30,CurrencyEnum.CASH);
        double amount = Math.round(((Receipt) receipt).AmountCharged*100.0)/100.0;
        //cust.ReceiveReceipt(receipt);



        assertEquals((30*2.76*100.0)/100.0, amount);
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