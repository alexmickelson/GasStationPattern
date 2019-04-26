public class StateEndTransaction implements IState {
    Pump myPump;
    IReceipt receipt;

    StateEndTransaction(Pump myPump)
    {
        this.myPump = myPump;
    }

    @Override
    public boolean isBusy() {
        return true;
    }
    @Override
    public boolean isPumping() {
        return false;
    }

    @Override
    public void pumpGas() {

    }

    @Override
    public void endTransaction() {
        myPump.currencyHandler.gasGiven(myPump.currentPumpedAmount);
        myPump.totalEarnings += myPump.currencyHandler.amountCharged();
        Receipt receipt = new Receipt();
        receipt.GasGiven = myPump.currentPumpedAmount;
        receipt.AmountCharged = myPump.currencyHandler.amountCharged();
        receipt.PaymentType = myPump.currencyHandler.paymentType();
        receipt.GasType = myPump.gradeChosen;
        myPump.currReceipt = receipt;
        myPump.state = myPump.stateReturnReceipt;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }

    @Override
    public double getAmountRequested() {
        return myPump.currentCustomer.GetAmountOfGasDesired();
    }

    @Override
    public void printReceipt() {

    }
}
