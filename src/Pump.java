public class Pump implements IPump {
    //Constructor
    ITank tank89;
    ITank tank85;

    double price85 = 2.50;
    double price87 = 2.90;
    double price89 = 3.00;

    Receipt receipt;




    public Pump(ITank tank85, ITank tank89){
        this.tank85 = tank85;
        this.tank89 = tank89;
        this.receipt = new Receipt();
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
        //move reciept to here

        IPumpCurrencyHandler currencyHandler = CurrencyHandlerFactory(customer);

        double allowedAmount = currencyHandler.availableAmount();
        //money before

        double gasGiven = 0;

        switch (customer.DesiredGrade()){
            case GRADE_85:{
                gasGiven = Retrieve85Grade(allowedAmount);
                break;
            }
            case GRADE_87:{
                gasGiven = Retrieve87Grade(allowedAmount);
                break;
            }
            case GRADE_89:{
                gasGiven = Retrieve89Grade(allowedAmount);
                break;
            }
        }


        currencyHandler.gasGiven(gasGiven);
        Receipt receipt = new Receipt();
        receipt.GasGiven = gasGiven;
        receipt.AmountCharged = currencyHandler.amountCharged();
        receipt.PaymentType = currencyHandler.paymentType();


        //money after



        return receipt;
    }

    @Override
    public boolean IsBusy() {
        return false;
    }


    private IPumpCurrencyHandler CurrencyHandlerFactory(ICustomer customer){
        return new CashCurrencyHandler(customer, price85,price87,price89);
    }
}
