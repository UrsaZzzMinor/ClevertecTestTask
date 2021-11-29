import com.clever.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestResults {
    private  static  Logger rootLogger = LogManager.getRootLogger();
    private Customer customer;
    private CashReceipt cashReceipt;

    @BeforeMethod
    public void init(){
        ArrayList<Product> products = new ArrayList<>(Arrays.asList(new Product("Milk", 4),
                new Product("Bread", 10),
                new Product("Salt", 2),
                new DiscountProduct(10, "BubbleGum", 5),
                new DiscountProduct(20, "Cake", 100)));

        Card[] cards = {new Card(1233, 20),
                new Card(1234, 10)};

        customer = new Customer();
        try {
            customer.getCart().fillCart(products, customer.getCard());
        } catch (IncorrectFileNameException e) {
            rootLogger.error("Incorrect filename : " + e.getMessage());
        }
        customer.checkCard(cards);
        cashReceipt = new CashReceipt();
        cashReceipt.countTotalPrice(customer);
    }

    @Test
    public void test1(){
        Assert.assertNotNull(customer);
    }

    @Test
    public void test2(){
        //Given
        double expected = 855;
        //When
        double result = cashReceipt.getTotalPrice();
        //Then
        Assert.assertEquals(result, expected);
    }

    @Test
    public void test3(){
        //Given
        String expectedName = "Cake";
        double expectedPrice = 100;
        int expectedQuantity = 10;
        int expectedDiscount = 20;
        //When
        Product resultProduct;
        resultProduct = customer.getCart().getProductsInCart().get(2);

        String resultName = resultProduct.getName();
        double resultPrice = resultProduct.getPrice();
        int resultQuantity = resultProduct.getQuantity();
        int resultDiscount;

        if (resultProduct instanceof DiscountProduct){
            resultDiscount = ((DiscountProduct) resultProduct).getDiscount();
        } else {
            resultDiscount = -1;
        }
        //Then
        Assert.assertEquals(resultName, expectedName);
        Assert.assertEquals(resultPrice, expectedPrice);
        Assert.assertEquals(resultQuantity, expectedQuantity);
        Assert.assertEquals(resultDiscount, expectedDiscount);
    }

    @Test
    public void test4(){
        //Given
        double expected = 800;
        //When
        double result = customer.getCart().getProductsInCart().get(2).countCost();
        //Then
        Assert.assertEquals(result, expected);
    }



}
