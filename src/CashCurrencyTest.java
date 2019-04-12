import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;


public class CashCurrencyTest {
    Mockery context = new JUnit4Mockery();


    @Test
    public void InvalidMoneyTypeInCustomer_ThrowsError(){
        double price = 2.0;

        ICustomer mockCustomer = context.mock(ICustomer.class);
        var credit = CurrencyEnum.CREDIT_CARD;

        context.checking(new Expectations(){{
            oneOf(mockCustomer).GetMoneyType(); will(returnValue(CurrencyEnum.CREDIT_CARD));
        }});
        var a = mockCustomer.GetMoneyType();
/*
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(InvalidParameterException.class);
        exceptionRule.expectMessage("Customer Money Type Was Not Cash");*/

        //new CashCurrencyHandler(mockCustomer, price, price, price);
    }


}
