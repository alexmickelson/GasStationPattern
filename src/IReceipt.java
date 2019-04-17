public interface IReceipt {
    double GetAmountCharged();
    double GetGasGiven();
    CurrencyEnum GetPaymentType();
    void printReceipt();
}
