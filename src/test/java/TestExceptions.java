import com.clever.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestExceptions {
    private static Logger rootLogger = LogManager.getRootLogger();

    @Test(dataProvider = "exceptions", dataProviderClass = DataProvider.class)
    public void testException(Customer customer, ArrayList<Product> products){
        try{
            customer.getCart().fillCart(products, customer.getCard(), "src/main/resources/");//"src/main/resources/"
        } catch (IncorrectFileNameException e){
            rootLogger.error("Unable to find a File : " + e.getMessage());
            Assert.assertTrue(e.getMessage().contains("Incorrect filename"));
        }
    }

    @Test(dataProvider = "exceptions", dataProviderClass = DataProvider.class)
    public void testException2(Customer customer, ArrayList<Product> products){
        try{
            customer.getCart().fillCart(products, customer.getCard(), "src/main/resources/data1.txt");
        } catch (IncorrectFileNameException e){
            rootLogger.error("Unable to find a File : " + e.getMessage());
            Assert.assertTrue(e.getMessage().contains("Incorrect filename"));
        } catch (IncorrectDataException e){
            rootLogger.error(e.getMessage());
            Assert.assertTrue(e.getMessage().contains("Invalid data"));
        }
    }

    @Test(dataProvider = "exceptions", dataProviderClass = DataProvider.class)
    public void testException3(Customer customer, ArrayList<Product> products){
        try{
            customer.getCart().fillCart(products, customer.getCard(), "src/main/resources/data2.txt");
        } catch (IncorrectFileNameException e){
            rootLogger.error("Unable to find a File : " + e.getMessage());
            Assert.assertTrue(e.getMessage().contains("Incorrect filename"));
        } catch (IncorrectDataException e){
            rootLogger.error(e.getMessage());
            Assert.assertTrue(e.getMessage().contains("Missing data"));
        }
    }

}
