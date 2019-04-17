import java.util.ArrayList;
import java.util.Random;

public class DriverPump extends Thread {

    private ITimeObservable time;
    ITank tank89;
    ITank tank85;
    IPump p;
    ICustomer c;


    public DriverPump(ITank tank85amount, ITank tank89amount, ICustomer customer, ITimeObservable time, int pumpNumber) {
        tank85 = tank85amount;
        tank89 = tank89amount;
        this.time = time;
        p = new Pump(tank85, tank89, time, pumpNumber);
        c= customer;
    }

    @Override
    public void run() {
        var r = p.PumpTransaction(c);
        r.printReceipt();


        System.out.println("\n[Remaining Gas in Tank 85]: " + tank85.getLevel());
        System.out.println("[Remaining Gas in Tank 89]: " + tank89.getLevel());
    }


}
