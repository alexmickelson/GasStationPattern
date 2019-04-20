import java.util.concurrent.TimeUnit;

public class Pump implements IPump, Runnable {
    //Constructor

    final static int NO_CUSTOMER = 0;
    final static int START_TRANSACTION = 1;
    final static int PUMPING_GAS = 2;
    final static int ENDING_TRANSACTION = 3;
    int state = NO_CUSTOMER;

    ITank tank89;
    ITank tank85;

    int pumpNumber;
    double price85 = 2.50;
    double price87 = 2.90;
    double price89 = 3.00;
    boolean isPumping = false;
    double allowedAmount = 0;
    double currentPumpedAmount = 0;
    double gasIncrementPerSecond = .3; //.3 gallons
    int sleepAmt = 500;
    GradeEnum gradeChosen = GradeEnum.GRADE_85;

    ICustomer currentCustomer;






    public Pump(ITank tank85, ITank tank89, ITimeObservable clock, int pumpNumber){
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
    public IReceipt PumpTransaction(ICustomer customer) {
        if (state == NO_CUSTOMER) {
            state = START_TRANSACTION;
            log("Transaction started");
            //move reciept to here
            allowedAmount = 0;
            currentPumpedAmount = 0;
            //not sure if this is the right way to handle it but we want it to not be any of the values on init

            IPumpCurrencyHandler currencyHandler = CurrencyHandlerFactory(customer);

            allowedAmount = currencyHandler.availableAmount();
            log("Max Gallons allowed: " + allowedAmount);
            //money before

            double gasGiven = 0;

            //set our variable for this transaction to get the right type of grade
            switch (customer.DesiredGrade()) {
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


            isPumping = true;
            state = PUMPING_GAS;
            log("pumping started");

            while (isPumping) {
                try {
                    Thread.sleep(sleepAmt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            state = ENDING_TRANSACTION;
            log("Ending Transaction-Request Receipt");

            currencyHandler.gasGiven(gasGiven);
            Receipt receipt = new Receipt();
            receipt.GasGiven = gasGiven;
            receipt.AmountCharged = currencyHandler.amountCharged();
            receipt.PaymentType = currencyHandler.paymentType();

            state = NO_CUSTOMER;
            return receipt;

        }
        return null;
    }

    @Override

    public boolean IsBusy() {
        if(state == NO_CUSTOMER)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public IPumpCurrencyHandler CurrencyHandlerFactory(ICustomer customer){
        return new CashCurrencyHandler(customer, price85,price87,price89);
    }

    @Override
    public void update(int ticks) {

        if(isPumping)
        {
            double gasReceived;

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
                log("Current Total: " + currentPumpedAmount + "gallons. Pumped: " + gasReceived + "gallons of gas. Tick: " + ticks);
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

            if(isPumping == false)
            {
                log("Done Pumping");
            }
        }
    }

    //Get our gas using our selected grade
    public double RequestGas(double amt)
    {
        switch (gradeChosen){
            case GRADE_85:{
                return Retrieve85Grade(amt);
            }
            case GRADE_87:{
                return Retrieve85Grade(amt);
            }
            case GRADE_89:{
                return Retrieve85Grade(amt);
            }
        }
        //we didn't hit anything so we just return 0, we got no gas
        return 0;
    }

    private void log(String s){
        System.out.println("Pump " + pumpNumber + ":   " + s);
    }


    @Override
    public void run() {
        PumpTransaction(currentCustomer);
    }
}
