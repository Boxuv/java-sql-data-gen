import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateSQLScript {

    public static void SQLScriptFile (int year, int how_Many_Customers,int how_Many_Products, int how_Many_Invoices_Per_Month){

        List<Product> products = GenerateSQLScript.generateProducts(how_Many_Products, year);
        List<List<Product>> productsByMonth = GenerateSQLScript.separateProductsIntoMonths(products);

        File file = new File("script.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(generateCustomerScript(how_Many_Customers));
            writer.write(generateProductScript(products));
            writer.write(generateInvoiceScript(productsByMonth,how_Many_Invoices_Per_Month));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
        }
        System.out.printf("File is located at %s%n", file.getAbsolutePath());
    }

    private static String generateCustomerScript(int amount) {

        int cycles = (amount / 500);
        int remainder = (amount % 500);

        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i < cycles; i++) {
            returnString.append(System.getProperty("line.separator"));
            returnString.append("INSERT INTO CUSTOMER (id, name) VALUES ");
            for (int j = 0; j < 500; j++) {
                returnString.append(System.getProperty("line.separator"));
                returnString.append(new Customer().toString());
                returnString.append(",");
            }
            returnString.deleteCharAt(returnString.length()-1);
        }

        if (remainder > 0) {
            returnString.append(System.getProperty("line.separator"));
            returnString.append("INSERT INTO CUSTOMER (id, name) VALUES ");
            for (int j = 0; j < remainder; j++) {
                returnString.append(new Customer().toString());
                returnString.append(",");
            }
            returnString.deleteCharAt(returnString.length()-1);
        }
        return returnString.toString();
    }

    private static List<Product> generateProducts(int amount,int productYear) {
        List<Product> products = new ArrayList<Product>();
        for (int i=0;i<amount;i++){
            products.add(new Product(productYear));
        }
        return products;
    }

    private static String generateProductScript(List<Product> products) {

        int listSize = products.size();
        int cycles = (listSize / 500);
        int remainder = (listSize % 500);

        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < cycles; i++) {
            returnString.append(System.getProperty("line.separator"));
            returnString.append("INSERT INTO PRODUCT (id, name, start_date, end_date) VALUES ");
            for (int j = 0; j < 500; j++) {
                returnString.append(System.getProperty("line.separator"));
                returnString.append(products.get(j + i*500).toString());
                returnString.append(",");
            }
            returnString.deleteCharAt(returnString.length()-1);
        }

        if (remainder > 0){
            returnString.append(System.getProperty("line.separator"));
            returnString.append("INSERT INTO PRODUCT (id, name, start_date, end_date) VALUES ");
            for (int i = 0; i < remainder; i++) {
                returnString.append(System.getProperty("line.separator"));
                returnString.append(products.get(i + cycles*500).toString());
                returnString.append(",");
            }
            returnString.deleteCharAt(returnString.length()-1);
        }

        return returnString.toString();
    }

    private static List<List<Product>> separateProductsIntoMonths(List<Product> products) {

        List<Product> jan = new ArrayList<>(),feb = new ArrayList<>(),mar = new ArrayList<>(),
                apr = new ArrayList<>(),may = new ArrayList<>(),jun = new ArrayList<>(),
                jul = new ArrayList<>(),aug = new ArrayList<>(),sep = new ArrayList<>(),
                oct = new ArrayList<>(),nov = new ArrayList<>(),dec = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            for (int month = product.getStart_Month(); month<product.getEnd_Month()+1;month++){
                switch (month) {
                    case 1:  jan.add(product); break;
                    case 2:  feb.add(product); break;
                    case 3:  mar.add(product); break;
                    case 4:  apr.add(product); break;
                    case 5:  may.add(product); break;
                    case 6:  jun.add(product); break;
                    case 7:  jul.add(product); break;
                    case 8:  aug.add(product); break;
                    case 9:  sep.add(product); break;
                    case 10: oct.add(product); break;
                    case 11: nov.add(product); break;
                    case 12: dec.add(product); break;
                }
            }
        }

        List<List<Product>> productsByMonth = new ArrayList<>();
        productsByMonth.add(jan); productsByMonth.add(feb); productsByMonth.add(mar); productsByMonth.add(apr);
        productsByMonth.add(may); productsByMonth.add(jun); productsByMonth.add(jul); productsByMonth.add(aug);
        productsByMonth.add(sep); productsByMonth.add(oct); productsByMonth.add(nov); productsByMonth.add(dec);

        return productsByMonth;
    }

    private static String generateInvoiceScript(List<List<Product>> productsByMonth,int how_Many_Invoices_Per_Month) {

        StringBuilder returnString = new StringBuilder();

        for (int month=1; month<13;month++){

            List<Product> products = productsByMonth.get(month-1);
            int maxProducts = products.size();

            int listSize = how_Many_Invoices_Per_Month;
            int cycles = (how_Many_Invoices_Per_Month / 500);
            int remainder = (how_Many_Invoices_Per_Month % 500);


            for (int cycle= 0;cycle<cycles;cycle++){
                returnString.append(System.getProperty("line.separator"));
                returnString.append("INSERT INTO INVOICE (invoice_number ,invoice_date ,customer_id ,product_id, quantity,summa) VALUES ");
                for (int i= 0;i<500;i++){
                    Product product = products.get(Utility.randomNumberAtoB(0,maxProducts-1));
                    returnString.append(System.getProperty("line.separator"));
                    returnString.append(new Invoice(product,month).toString());
                    returnString.append(",");
                }
                returnString.deleteCharAt(returnString.length()-1);
            }

            if (remainder > 0){
                returnString.append(System.getProperty("line.separator"));
                returnString.append("INSERT INTO INVOICE (invoice_number ,invoice_date ,customer_id ,product_id, quantity,summa) VALUES ");
                for (int i = 0; i < remainder; i++) {
                    Product product = products.get(Utility.randomNumberAtoB(0,maxProducts-1));
                    returnString.append(System.getProperty("line.separator"));
                    returnString.append(new Invoice(product,month).toString());
                    returnString.append(",");
                }
                returnString.deleteCharAt(returnString.length()-1);
            }
        }
        return returnString.toString();
    }
}