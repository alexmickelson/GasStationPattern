enum GradeEnum{
    GRADE_87, GRADE_85, GRADE_89

}
enum CurrencyEnum{
    CREDIT_CARD, CASH, PAYPAL

}

public interface ICustomer {
    double DesiredAmountOfGas();
    Double MaxAvailableMoney();
    GradeEnum DediredGrade();
    CurrencyEnum GetMoneyType();
    boolean RecieveReceipt (IReceipt receipt);
    IReceipt GetReceipt();
}
