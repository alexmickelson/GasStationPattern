public class Customer implements ICustomer {
    Receipt personalReceipt;
    GradeEnum grade;
    CurrencyEnum currencyType;
    //TODO: Ask about this one!!
    double gasAmt;
    double moneyAmt;

    public Customer(){
        this.grade = GradeEnum.GRADE_85;
    }

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
        return grade;
    }

    @Override
    public void SetDesiredGrade(GradeEnum grade) {
        this.grade = grade;
    }

    @Override
    public CurrencyEnum GetMoneyType() {
        return currencyType;
    }

    public void SetMoneyType(CurrencyEnum currency) {
        this.currencyType = currency;
    }
    @Override
    public boolean ReceiveReceipt(IReceipt receipt) {
        receipt.printReceipt();
        return true;
    }

    @Override
    public IReceipt GetReceipt() {
        return personalReceipt;
    }
}
