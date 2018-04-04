public class Product {
    private static int counter = 1;
    private int id;
    private String name;
    private int year;
    private int start_Month;
    private int end_Month;
    private int start_Day;
    private int end_Day;

    public Product(int productYear){
        id = counter++;
        year = productYear;
        start_Month = Utility.randomNumberAtoB(1,12);
        end_Month = Utility.randomNumberAtoB(start_Month,12);
        start_Day = Utility.randomDayInMonthBounded(1,start_Month,year);
        name = Utility.randomString(Utility.randomNumberAtoB(4,12));
        if (end_Month==start_Month){
            end_Day = Utility.randomDayInMonthBounded(start_Day,end_Month,year);
        } else {
            end_Day = Utility.randomDayInMonthBounded(1,end_Month,year);
        }
    }
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("(").append(id).append(",")
                .append("'").append(name).append("'").append(",")
                .append("'").append(year).append(Utility.numberFiller(start_Month)).append(Utility.numberFiller(start_Day)).append("'").append(",")
                .append("'").append(year).append(Utility.numberFiller(end_Month)).append(Utility.numberFiller(end_Day)).append("'")
                .append(")");
        return returnString.toString();
    }
    public int getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public int getStart_Month() {
        return start_Month;
    }
    public int getEnd_Month() {
        return end_Month;
    }
    public int getStart_Day() {
        return start_Day;
    }
    public int getEnd_Day() {
        return end_Day;
    }
}