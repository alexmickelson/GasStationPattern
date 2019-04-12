public class Customer implements ICustomer {
    Receipt personalReceipt;

    //TODO: Ask about this one!!
    double gasAmt;
    double moneyAmt;

    public boolean SetDesiredAmountOfGas(double amount) {
        this.gasAmt = amount;
        return true;
    }

    @Override
    public double GetAmountOfGasDesired() {
        return gasAmt;
    }

    @Override
    public Double GetMaxAvailableMoney() {
        return moneyAmt;
    }

    @Override
    public boolean SetMaxAvailableMoney(double amt) {
        moneyAmt = amt;
        return true;
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
