public class Invoice {
    private static int counter = 1;
    private int id;
    private int year;
    private int month;
    private int day;
    private int customer_id;
    private int product_id;
    private int quantity;
    private int summa;

    public Invoice (Product product, int invoiceMonth){
        id = counter++;
        year = product.getYear();
        month = invoiceMonth;

        if (product.getStart_Month() == product.getEnd_Month()){
            day = Utility.randomNumberAtoB(product.getStart_Day(),product.getEnd_Day());
        } else if (month == product.getStart_Month()){
            day = Utility.randomDayInMonthBounded(product.getStart_Day(),invoiceMonth,product.getYear());
        } else if (month == product.getEnd_Month()){
            day = Utility.randomNumberAtoB(1,product.getEnd_Day());
        } else {
            day = Utility.randomDayInMonthBounded(1,invoiceMonth,product.getYear());
        }

        customer_id = Utility.randomNumberAtoB(1,10000);
        product_id = product.getId();
        quantity = Utility.randomNumberAtoB(1,10);
        summa = quantity * Utility.randomNumberAtoB(1,30);
    }
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("(").append(id).append(",")
                .append("'").append(year).append(Utility.numberFiller(month)).append(Utility.numberFiller(day)).append("'").append(",")
                .append(customer_id).append(",")
                .append(product_id).append(",")
                .append(quantity).append(",")
                .append(summa)
                .append(")");
        return returnString.toString();
    }
}