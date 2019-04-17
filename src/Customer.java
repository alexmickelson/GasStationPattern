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
        //TODO: Check if it works before removing comments
//        switch ((int) (Math.round(Math.random()*100)%3)){
//            case 0:
//            {
//                return GradeEnum.GRADE_85;
//            }
//            case 1:
//            {
//                return GradeEnum.GRADE_87;
//            }
//            case 2:
//            {
//                return GradeEnum.GRADE_89;
//            }
//        }

        return GradeEnum.GRADE_85;
    }

    @Override
    public CurrencyEnum GetMoneyType() {
        return CurrencyEnum.CASH;
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
