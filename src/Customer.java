public class Customer implements ICustomer {
    Receipt personalReceipt;

    //TODO: Ask about this one!!
    double amount;

    public double DesiredAmountOfGas(double amount) {
        this.amount = amount;
        return this.amount;
    }

    public double GetAmountOfgasDesired(){
        return amount;
    }

    @Override
    public Double MaxAvailableMoney() {
        return null;
    }

    @Override
    public GradeEnum DesiredGrade() {
        //switch (1+Math.round(Math.random()*100)%2)
        return GradeEnum.GRADE_85;
    }

    @Override
    public CurrencyEnum GetMoneyType() {
        return CurrencyEnum.CASH;
    }

    @Override
    public boolean ReceiveReceipt(IReceipt receipt) {
        receipt.returnReceipt().printReceipt();
        return true;
    }

    @Override
    public IReceipt GetReceipt() {
        return personalReceipt;
    }
}
