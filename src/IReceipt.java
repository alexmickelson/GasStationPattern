public interface IReceipt {
    double AmountCharged();
    double GasGiven();
    CurrencyEnum PaymentType();
}
