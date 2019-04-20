public interface IState {
    boolean isBusy();
    boolean isPumping();
    void pumpGas();
    IReceipt endTransaction();
    void addCustomer(ICustomer customer);

}


