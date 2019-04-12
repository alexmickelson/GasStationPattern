enum GradeEnum{
    GRADE_87,
    GRADE_85,
    GRADE_89

}
enum CurrencyEnum{
    CREDIT_CARD,
    CASH,
    PAYPAL

}

public interface ICustomer {
    boolean SetDesiredAmountOfGas(double gas);
    double GetAmountOfGasDesired();
    Double GetMaxAvailableMoney();
    boolean SetMaxAvailableMoney(double amt);
    GradeEnum DesiredGrade();
    CurrencyEnum GetMoneyType();
    boolean ReceiveReceipt (IReceipt receipt);
    IReceipt GetReceipt();
}
