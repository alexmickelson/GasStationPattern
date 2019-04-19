public interface IPump extends ITimeObserver, Runnable {
    double Retrieve89Grade(double amount);
    double Retrieve87Grade(double amount);
    double Retrieve85Grade(double amount);

    IReceipt PumpTransaction(ICustomer costumer);
    boolean IsBusy();

    boolean SetCustomer(ICustomer customer);

}
