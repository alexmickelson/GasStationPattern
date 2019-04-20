public interface IReceipt {
    double GetAmountCharged();
    double GetGasGiven();
    CurrencyEnum GetPaymentType();
    GradeEnum GetGasType();
    void printReceipt();
}
