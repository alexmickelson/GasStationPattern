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
    public void endTransaction() {

    }

    @Override
    public void printReceipt(){
        myPump.currReceipt.printReceipt();
        if(myPump.currReceipt.GetGasGiven() > 0)
        {
            myPump.totalPumpCustomersServed++;
        }
        else if (myPump.currReceipt.GetGasGiven() == 0) {
            switch (myPump.currReceipt.GetGasType()) {
                case GRADE_85:
                    myPump.total85CustomerLost++;
                    myPump.log("Customer has been lost: " + myPump.currReceipt.GetGasType().toString() + " Amount of customers lost: " + myPump.total85CustomerLost);
                    break;
                case GRADE_87:
                    myPump.total87CustomerLost++;
                    myPump.log("Customer has been lost: " + myPump.currReceipt.GetGasType().toString() + " Amount of customers lost: " + myPump.total85CustomerLost);
                    break;
                case GRADE_89:
                    myPump.total89CustomerLost++;
                    myPump.log("Customer has been lost: " + myPump.currReceipt.GetGasType().toString() + " Amount of customers lost: " + myPump.total85CustomerLost);
                    break;
            }

        }
        myPump.currReceipt = null; //set the receipt to null
        myPump.state = myPump.stateNoCustomer;
    }

    @Override
    public void addCustomer(ICustomer customer) {

    }

    @Override
    public double getAmountRequested() {
        return myPump.currentCustomer.GetAmountOfGasDesired();
    }
}
