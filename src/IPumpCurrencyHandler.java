public interface IPumpCurrencyHandler {
    double availableAmount();
    boolean gasGiven(double gas);
    double amountCharged();
    CurrencyEnum paymentType();
}
