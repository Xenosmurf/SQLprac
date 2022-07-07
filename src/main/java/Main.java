import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ProductRepository productRepository = new ProductRepository("HelloProduct");
        productRepository.deleteAll();

        Product item1 = new Product("Milk", 30, 15);
        Product item2 = new Product("Bread", 25.7, 40);
        Product item3 = new Product("Beer", 45, 100);
//        sqlTest.initialization("HelloProduct");
        item1 = productRepository.insertData(item1);
        item2 = productRepository.insertData(item2);
        productRepository.insertData(item1);
        productRepository.insertData(item2);
        productRepository.insertData(item3);
        productRepository.showAllData();
        System.out.println("oooooooooooooooooo");
        productRepository.delete(item3);
        productRepository.showAllData();

        System.out.println(productRepository.listByPrice("<30"));

//        sqlTest.delete(item1);
//        sqlTest.showAllData();
    }
}
