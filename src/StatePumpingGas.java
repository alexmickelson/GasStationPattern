public class StatePumpingGas implements IState {
    Pump myPump;

    StatePumpingGas(Pump myPump)
    {
        this.myPump = myPump;
    }
    @Override
    public boolean isBusy() {
        return true;
    }

    @Override
    public boolean isPumping() {
        return true;
    }

    @Override
    public void pumpGas() {
        boolean ret = myPump.pumpGas();
        if(ret == false)
        {
            myPump.log("Done Pumping----Ending Transaction-Request Receipt");
            myPump.state = myPump.stateEndTransaction;
        }
    }

    @Override
    public IReceipt endTransaction() {
        return null;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }
}