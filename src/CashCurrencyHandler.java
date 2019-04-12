import java.security.InvalidParameterException;

public class CashCurrencyHandler implements IPumpCurrencyHandler {
    private final double price85;
    private final double price87;
    private final double price89;
    ICustomer customer;
    double finalGasAmt;

    public CashCurrencyHandler(ICustomer customer, double price85, double price87, double price89){
        if(customer.GetMoneyType() != CurrencyEnum.CASH){
            throw new InvalidParameterException("Customer Money Type Was Not Cash");
        }
        this.customer = customer;
        this.price85 = price85;
        this.price87 = price87;
        this.price89 = price89;

    }

    @Override
    public double availableAmount() {
        double maxAmt = 0;

        switch (customer.DesiredGrade()){
            case GRADE_85:
                maxAmt = customer.GetMaxAvailableMoney()/price85;
                break;
            case GRADE_87:
                maxAmt = customer.GetMaxAvailableMoney()/price87;
                break;
            case GRADE_89:
                maxAmt = customer.GetMaxAvailableMoney()/price89;
                break;
        }

        if (maxAmt > customer.GetAmountOfGasDesired()){
            maxAmt = customer.GetAmountOfGasDesired();
        }
        return maxAmt;
    }

    @Override
    public boolean gasGiven(double gas) {
        finalGasAmt = gas;
        return true;
    }

    @Override
    public double amountCharged() {
        switch (customer.DesiredGrade()){
            case GRADE_85:
                return price85*finalGasAmt;
            case GRADE_87:
                return price87*finalGasAmt;
            case GRADE_89:
                return price89*finalGasAmt;
        }
        return -1;
    }

    @Override
    public CurrencyEnum paymentType() {
        return CurrencyEnum.CASH;
    }
}
