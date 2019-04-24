public class StateReturnReceipt implements IState {
    Pump myPump;

    public StateReturnReceipt(Pump myPump)
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
    public IReceipt endTransaction() {
        return null;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }

    @Override
    public double getAmountRequested() {
        return myPump.currentCustomer.GetAmountOfGasDesired();
    }
}
