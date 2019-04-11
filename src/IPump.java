public interface IPump {
    double Retrieve89Grade(double amount);
    double Retrieve87Grade(double amount);
    double Retrieve85Grade(double amount);

    boolean PumpTransaction(ICustomer costumer);
    boolean IsBusy();
}
