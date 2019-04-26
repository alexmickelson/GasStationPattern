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
        myPump.endTransaction();
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
