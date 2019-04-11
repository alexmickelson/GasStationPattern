enum GradeEnum{
    GRADE_87, GRADE_85, GRADE_89

}
enum CurrencyEnum{
    CREDIT_CARD, CASH, PAYPAL

}

public interface ICustomer {
    double DesiredAmountOfGas(double amount);
    double GetAmountOfgasDesired();
    Double MaxAvailableMoney();
    GradeEnum DesiredGrade();
    CurrencyEnum GetMoneyType();
    boolean ReceiveReceipt (IReceipt receipt);
    IReceipt GetReceipt();
}
