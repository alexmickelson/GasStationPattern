public interface IPump extends ITimeObserver, Runnable {
    double Retrieve89Grade(double amount);
    double Retrieve87Grade(double amount);
    double Retrieve85Grade(double amount);

    IReceipt PumpTransaction(ICustomer costumer);
    boolean IsBusy();

    boolean SetCustomer(ICustomer customer);
    public GradeEnum GetGradeChosen();

    public double GetCurrentPumpedAmount();

    public double Get85GasAmountPumped();

    public double Get87GasAmountPumped();

    public double Get89GasAmountPumped();

    //return lost customers from having no gas in tank
    public int Get85LostCustomers();

    public int Get87LostCustomers();

    public int Get89LostCustomers();

    public double GetPumpSpeed();
    public void SetPumpSpeed(double speed);

    double getAmountRequested();

}
