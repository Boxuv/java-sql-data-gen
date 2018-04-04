public class Customer {
    private static int counter = 1;
    private int id;
    private String name;

    public Customer(){
        id = counter++;
        name = Utility.randomString(Utility.randomNumberAtoB(4,12));
    }
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("(").append(id).append(",")
                .append("'").append(name).append("'")
                .append(")");
        return returnString.toString();
    }
}