import org.jmock.internal.State;

import java.util.concurrent.TimeUnit;

public class Pump implements IPump, Runnable {
    //Constructor

    IState stateStartTransaction;
    IState stateEndTransaction;
    IState stateNoCustomer;
    IState statePumpingGas;
    IState stateReturnReceipt;
    IState state;

    //mike was here
    //final static int NO_CUSTOMER = 0;
    //final static int START_TRANSACTION = 1;
    //final static int PUMPING_GAS = 2;
    //final static int ENDING_TRANSACTION = 3;


    ITank tank89;
    ITank tank85;

    int pumpNumber;
    double price85 = 2.50;
    double price87 = 2.90;
    double price89 = 3.00;
    //boolean isPumping = false;
    double allowedAmount = 0;
    double currentPumpedAmount = 0;
    double gasIncrementPerSecond = 0.3; //.3 gallons
    IReceipt currReceipt = null;

    //This checks when to end the transaction
    int rateoftime = 1000;
    GradeEnum gradeChosen = GradeEnum.GRADE_85;


    //Stats
    double total85Pumped;
    double total87Pumped;
    double total89Pumped;

    int total85CustomerLost;
    int total87CustomerLost;
    int total89CustomerLost;

    double totalEarnings;
    int totalPumpCustomersServed;
    public double getAmountRequested(){
        return state.getAmountRequested();
    }

    ICustomer currentCustomer;
    IPumpCurrencyHandler currencyHandler;

    //hello world
    @Override
    public GradeEnum GetGradeChosen(){
        return gradeChosen;
    }

    @Override
    public double GetCurrentPumpedAmount(){
        return currentPumpedAmount;
    }

    @Override
    public double Get85GasAmountPumped(){
        return total85Pumped;
    }

    @Override
    public double Get87GasAmountPumped(){
         return total87Pumped;
    }

    @Override
    public double Get89GasAmountPumped(){
         return total89Pumped;
    }

    //return lost customers from having no gas in tank
    @Override
    public int Get85LostCustomers(){
        return total85CustomerLost;
    }

    @Override
    public int Get87LostCustomers(){
        return total87CustomerLost;
    }

    @Override
    public int Get89LostCustomers(){
        return total89CustomerLost;
    }

    public double GetPumpSpeed(){
        return gasIncrementPerSecond;
    }
    public void SetPumpSpeed(double speed){
        this.gasIncrementPerSecond = speed;
    }

    @Override
    public int getTotalCustomersServed(){
        return totalPumpCustomersServed;
    }




    public Pump(ITank tank85, ITank tank89, ITimeObservable clock, int pumpNumber){
        stateEndTransaction = new StateEndTransaction(this);
        stateNoCustomer = new StateNoCustomer(this);
        statePumpingGas = new StatePumpingGas(this);
        stateReturnReceipt = new StateReturnReceipt(this);

        state = stateNoCustomer;

        this.tank85 = tank85;
        this.tank89 = tank89;
        clock.subscribe(this);
        this.pumpNumber = pumpNumber;


        log("pump " + pumpNumber + " ready to go");
    }

    public boolean SetCustomer(ICustomer customer){
        currentCustomer = customer;
        return true;
    }


    @Override
    public double Retrieve87Grade(double amount) {
        //TODO: CHANGE IN THE FUTURE
        double temp=0;

        temp += tank85.RetrieveGasFromTank(amount/2);
        temp += tank89.RetrieveGasFromTank(amount/2);

        return temp;
    }
    @Override
    public double Retrieve89Grade(double amount) {
        return tank89.RetrieveGasFromTank(amount);
    }
    @Override
    public double Retrieve85Grade(double amount) {
        return tank85.RetrieveGasFromTank(amount);
    }

    @Override
    public boolean PumpTransaction(ICustomer customer) {

        state.addCustomer(customer); //This will call for a customer if we are in NO_CUSTOMER state


        while (state.isPumping()) {
            try {
                Thread.sleep(rateoftime*3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state.endTransaction();
        state.printReceipt();
        if(currReceipt != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean IsBusy() {
        return state.isBusy();
    }

    public IPumpCurrencyHandler CurrencyHandlerFactory(ICustomer customer){
        if(customer.GetMoneyType() == CurrencyEnum.CASH){
            return new CashCurrencyHandler(customer, price85,price87,price89);
        }
        else{
            return new CreditCurrencyHandler(customer,price85,price87,price89);
        }
    }

    @Override
    public void update(int ticks) {
        state.pumpGas();


    }

    public boolean pumpGas() {

        double gasReceived;
        boolean isPumping = true;
        //we can do our regular increment up to our money max
        if(currentPumpedAmount + gasIncrementPerSecond < allowedAmount)
        {
            //Get as much gas as the tank gives us from our request
            gasReceived = RequestGas(gasIncrementPerSecond);
            //we received some gas so update amount
            if (gasReceived > 0)
            {
                currentPumpedAmount += gasReceived;
            }
            //we didn't get gas so we can't pump
            else
            {
                isPumping = false;
            }
            //if we hit this we also know we would have an empty tank
            if (gasReceived < gasIncrementPerSecond)
            {
                //Set IsTankEmpty so our pump doesn't try to pump and displays a message or something
                currentPumpedAmount += gasReceived;
                isPumping = false;
            }
            log("Current Total: " + currentPumpedAmount + "gallons. Pumped: " + gasReceived + "gallons of gas. ");
            log("Total Earnings: " + totalEarnings);
        }
        else if (currentPumpedAmount < allowedAmount) //We hit here if we are just topping off from our money amount requested
        {
            gasReceived = RequestGas(allowedAmount - currentPumpedAmount); //get gas from tank what it requests
            currentPumpedAmount += gasReceived;
            isPumping = false; //We hit our requested amount (and possibly hit empty tank just stop pumping both ways)
            log("Current Total: " + currentPumpedAmount + "gallons");
        }
        else
        {
            isPumping = false;
        }
        return isPumping;
    }

    public void startTransaction(){
        log("Transaction started");
        //move reciept to here
        allowedAmount = 0;
        currentPumpedAmount = 0;
        //not sure if this is the right way to handle it but we want it to not be any of the values on init

        currencyHandler = CurrencyHandlerFactory(currentCustomer);

        allowedAmount = currencyHandler.availableAmount();
        log("Max Gallons allowed: " + allowedAmount);
        //money before

        double gasGiven = 0;

        //set our variable for this transaction to get the right type of grade
        switch (currentCustomer.DesiredGrade()) {
            case GRADE_85: {
                gradeChosen = GradeEnum.GRADE_85;
                break;
            }
            case GRADE_87: {
                gradeChosen = GradeEnum.GRADE_87;
                break;
            }
            case GRADE_89: {
                gradeChosen = GradeEnum.GRADE_89;
                break;
            }
        }


    }

    //Get our gas using our selected grade
    public double RequestGas(double amt)
    {
        double returnedAmt;
        switch (gradeChosen){
            case GRADE_85:{
                returnedAmt = Retrieve85Grade(amt);
                total85Pumped += returnedAmt;
                return returnedAmt;
            }
            case GRADE_87:{
                returnedAmt = Retrieve87Grade(amt);
                total87Pumped += returnedAmt;
                return returnedAmt;

            }
            case GRADE_89:{
                returnedAmt = Retrieve89Grade(amt);
                total89Pumped += returnedAmt;
                return returnedAmt;
            }
        }
        //we didn't hit anything so we just return 0, we got no gas
        return 0;
    }

    public void log(String s){
        System.out.println("Pump " + pumpNumber + ":   " + s);
    }


    @Override
    public void run() {
        var hasReceipt = PumpTransaction(currentCustomer);
        if (!hasReceipt) {
            if (currReceipt.GetGasGiven() == 0) {
                switch (currReceipt.GetGasType()) {
                    case GRADE_85:
                        total85CustomerLost += 1;
                        log("Customer has been lost: " + currReceipt.GetGasType().toString() + " Amount of customers lost: " + total85CustomerLost);
                        break;
                    case GRADE_87:
                        total87CustomerLost += 1;
                        log("Customer has been lost: " + currReceipt.GetGasType().toString() + " Amount of customers lost: " + total85CustomerLost);
                        break;
                    case GRADE_89:
                        total89CustomerLost += 1;
                        log("Customer has been lost: " + currReceipt.GetGasType().toString() + " Amount of customers lost: " + total85CustomerLost);
                        break;
                }

            } else{
                //currReceipt.printReceipt();
            }
        }
    }

    public void changespeed(int time) {
        rateoftime = time;
    }
}
