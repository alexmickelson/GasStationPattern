import java.util.ArrayList;
import java.util.Random;

public class DriverPump extends Thread {

    private ITimeObservable time;
    ITank tank89;
    ITank tank85;
    IPump p;
    ICustomer c;

    int total85CustomerLost = 0;
    int total87CustomerLost = 0;
    int total89CustomerLost = 0;


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
        if(r == false) {
            total85CustomerLost += 1;
            System.out.println("Station lost a customer: out of gas");
        }

        System.out.println("\n[Remaining Gas in Tank 85]: " + tank85.getLevel());
        System.out.println("[Remaining Gas in Tank 89]: " + tank89.getLevel());
    }


}
