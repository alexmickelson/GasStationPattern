import java.util.Random;

public class MockDriver{

    ITank tank89;
    ITank tank85;
    IPump p;


    public MockDriver(double tank85amount, double tank89amount) {
        tank85 = new Tank(tank85amount);
        tank89 = new Tank(tank89amount);
        p = new Pump(tank85, tank89);
    }
    public void doStuff(){

        ICustomer c = new Customer();

        //Set how much costumer wants
        //Gallons
        c.SetDesiredAmountOfGas(1+Math.round(Math.random()*100)%17);
        c.SetMaxAvailableMoney(50);
        c.ReceiveReceipt(p.PumpTransaction(c));

        System.out.println("\n[Remaining Gas in Tank 85]: " + tank85.getLevel());
        System.out.println("[Remaining Gas in Tank 89]: " + tank89.getLevel());
    }
}
