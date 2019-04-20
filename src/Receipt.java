public class Receipt implements IReceipt {

    public Receipt(){

    }

    public Receipt(double gasAmount, CurrencyEnum moneyType, GradeEnum gasType ){
        AmountCharged = MoneyConverter(gasAmount);
        GasGiven = gasAmount;
        PaymentType = moneyType;
        GasType = gasType;
    }

    double AmountCharged;
    double GasGiven;
    CurrencyEnum PaymentType;
    GradeEnum GasType;

    public double MoneyConverter(double gasAmount){
        return gasAmount*2.76;
    }

    public void printReceipt(){
        System.out.println("\n>>>>>\tYour Receipt\t<<<<<\n> Amount Charged: $"+ Math.round(AmountCharged*100.0)/100.0 + "\n" +
                            "> Gas Given: "+ GasGiven + " Gallons\n" +
                            "> Payment Type: " + PaymentType.toString() +
                            "> Gas Type: " + GasType.toString());
    }

    public double GetAmountCharged(){
        return AmountCharged;
    }

    public double GetGasGiven(){
        return GasGiven;
    }

    public CurrencyEnum GetPaymentType(){
        return PaymentType;
    }

    @Override
    public GradeEnum GetGasType() {
        return GasType;
    }

}
