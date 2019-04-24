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
public class TestGasStation {
    static Mockery context;
    static GasStation mockStation;
    static ITank tank;
    static Pump pump;
    static ICustomer customer;

    @BeforeClass
    public static void setup(){
        context = new JUnit4Mockery();
        mockStation = context.mock(GasStation.class);
        tank = context.mock(ITank.class);
        customer = context.mock(ICustomer.class);
        var clock = TimeService.getInstance();
        pump = new Pump(tank, tank, clock, 1);
    }

    @Test
    public void GasIsPumpedFromTheTanks_TotalPumpedIsUpdated_ReturnsTotalGasPumpedInTheStation(){
        double price = 2.0;

        var credit = CurrencyEnum.CREDIT_CARD;

        context.checking(new Expectations(){{
            oneOf(mockStation).; will(returnValue(CurrencyEnum.CREDIT_CARD));
        }});
        var a = mockCustomer.GetMoneyType();
/*
        ExpectedException exceptionRule = ExpectedException.none();
        exceptionRule.expect(InvalidParameterException.class);
        exceptionRule.expectMessage("Customer Money Type Was Not Cash");*/

        //new CashCurrencyHandler(mockCustomer, price, price, price);
    }
}
