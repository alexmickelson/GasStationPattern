import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.security.InvalidParameterException;


public class TestCashCurrency {
    static Mockery context;
    static ICustomer mockCustomer;
    static double price85;
    static double price87;
    static double price89;

    @BeforeClass
    public static void setup(){
        context = new JUnit4Mockery();
        mockCustomer = context.mock(ICustomer.class);
    }

    @Before
    public static void beforeTest(){
        price85 = 2.0;
        price87 = 2.7;
        price89=2.9;
    }

    @Test
    public void InvalidMoneyTypeInCustomer_ThrowsError(){
        double price = 2.0;

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

    @Test
    public void amountCharged_returnsPriceTimesGasAmt(){
        //CashCurrencyHandler currencyHandler = new CashCurrencyHandler(Customer, price85, price87, price89);
    }

}
