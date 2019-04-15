public interface IReceipt {
    Receipt returnReceipt();
    double GetAmountCharged();
    double GetGasGiven();
    CurrencyEnum GetPaymentType();
}
