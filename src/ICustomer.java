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
