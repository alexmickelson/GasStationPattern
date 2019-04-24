public class StateNoCustomer implements IState {
    Pump myPump;

    StateNoCustomer(Pump myPump)
    {
        this.myPump = myPump;
    }

    @Override
    public boolean isBusy() {
        return false;
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
        myPump.SetCustomer(myPump.currentCustomer);
        myPump.startTransaction();
        myPump.log("switching to pumping state") ;
        myPump.state = myPump.statePumpingGas;
    }

    @Override
    public double getAmountRequested() {
        return 0;
    }
}
