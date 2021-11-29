import com.clever.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "exceptions")
    public static Object[][] initForExceptions(){

        Customer customer = new Customer();

        ArrayList<Product> products = new ArrayList<>(Arrays.asList(new Product("Milk", 4),
                new Product("Bread", 10),
                new Product("Salt", 2),
                new DiscountProduct(10, "BubbleGum", 5),
                new DiscountProduct(20, "Cake", 100)));

        return new Object[][]{{customer, products}};
    }

}
