
public class Receipt implements IReceipt {

    public Receipt(){

    }

    public Receipt(double gasAmount, CurrencyEnum moneyType ){
        AmountCharged = MoneyConverter(gasAmount);
        GasGiven = gasAmount;
        PaymentType = moneyType;
    }

    double AmountCharged;
    double GasGiven;
    CurrencyEnum PaymentType;

    public double MoneyConverter(double gasAmount){
        return gasAmount*2.76;
    }

    public void printReceipt(){
        System.out.println("\n>>>>>\tYour Receipt\t<<<<<\n> Amount Charged: $"+ Math.round(AmountCharged*100.0)/100.0 + "\n" +
                            "> Gas Given: "+ GasGiven + " Gallons\n" +
                            "> Payment Type: " + PaymentType.toString());
    }

    public Receipt returnReceipt() {
        return this;
    }

}
