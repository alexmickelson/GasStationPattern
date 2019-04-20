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
    public IReceipt endTransaction() {
        receipt = myPump.endTransaction();
        myPump.state = myPump.stateNoCustomer;
        return receipt;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }
}
