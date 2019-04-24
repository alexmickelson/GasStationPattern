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

        //mike you never print reciept
        myPump.state = myPump.stateNoCustomer;
        return receipt;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }

    @Override
    public double getAmountRequested() {
        return myPump.currentCustomer.GetAmountOfGasDesired();
    }
}
