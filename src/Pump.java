public class Pump implements IPump {
    //Constructor
    Tank tank89;
    Tank tank85;

    Receipt receipt;


    public Pump(Tank tank85, Tank tank89){
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

    //In case Receipt is an external entity.
    public Receipt CreateReceipt(double amount, CurrencyEnum moneyType){
        return new Receipt(amount, moneyType);
    }

    public Receipt getReceipt(){
        if(receipt != null) {
            return receipt;
        }

        return null;
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
    public boolean PumpTransaction(ICustomer costumer) {
        switch (costumer.DesiredGrade()){
            case GRADE_85:{
                receipt.GasGiven = Retrieve85Grade(costumer.GetAmountOfgasDesired());
                break;
            }
            case GRADE_87:{
                receipt.GasGiven = Retrieve87Grade(costumer.GetAmountOfgasDesired());
                break;
            }
            case GRADE_89:{
                receipt.GasGiven = Retrieve89Grade(costumer.GetAmountOfgasDesired());
                break;
            }
        }

        receipt.PaymentType = costumer.GetMoneyType();
        receipt.AmountCharged = receipt.MoneyConverter(receipt.GasGiven);

        return true;
    }

    @Override
    public boolean IsBusy() {
        return false;
    }
}
