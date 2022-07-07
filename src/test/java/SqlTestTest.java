import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SqlTestTest {

    private ProductRepository productRepository = new ProductRepository("HelloProduct");
    @Test
    public void insertThenFindByCriteria(){
        Product item1 = new Product("Milk", 30, 15);
        Product item2 = new Product("Bread", 25.7, 40);
        // insert in db
        productRepository.insertData(item1);
        productRepository.insertData(item2);

        Product item1ActualAmount = productRepository.listByAmount("<16").get(0);
        Assert.assertEquals(item1.getName(), item1ActualAmount.getName());

    }
}
