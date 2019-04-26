public interface IState {
    boolean isBusy();
    boolean isPumping();
    void pumpGas();
    void endTransaction();
    void addCustomer(ICustomer customer);
    double getAmountRequested();
    void printReceipt();
}


