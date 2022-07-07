
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andriy
 */
public class ProductRepository {
    private Connection con;

    public ProductRepository(String name){
        initialization(name);
    }
    public void initialization(String name){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:"+name);
            PreparedStatement st = con.prepareStatement("create table if not exists 'product' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'price' double, 'amount' double );");
            st.executeUpdate();
        }catch(ClassNotFoundException e){
            System.out.println("Не знайшли драйвер JDBC");
            e.printStackTrace();
            System.exit(0);
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
    }

    public Product insertData(Product item){
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO product(name, price, amount) VALUES (?, ?, ?)");
            //statement.setInt(1, 1);
            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setDouble(3, item.getAmount());

            statement.executeUpdate();

            ResultSet resSet = statement.getGeneratedKeys();
            item.setId(resSet.getInt("last_insert_rowid()"));

//            int result = statement.executeUpdate();

            //statement.close();
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит на вставку");
            e.printStackTrace();
        }
        return item;
    }

    public void updateName(String newName, int id) throws SQLException {
        try {
            PreparedStatement statement = con.prepareStatement("update product set name=(?) where id = (?)");
            //statement.setInt(1, 1);
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
    }

    public void updatePrice(double newPrice, int id) throws SQLException {
        try {
            PreparedStatement statement = con.prepareStatement("update product set price=(?) where id = (?)");
            //statement.setInt(1, 1);
            statement.setDouble(1, newPrice);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
    }

    public void updateAmount(double newAmount, int id) throws SQLException {
        try {
            PreparedStatement statement = con.prepareStatement("update product set amount=(?) where id = (?)");
            //statement.setInt(1, 1);
            statement.setDouble(1, newAmount);
            statement.setInt(2, id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
    }

    public void delete(Product item) throws SQLException {
        try {
            PreparedStatement statement = con.prepareStatement("delete from product where id = (?)");
            //statement.setInt(1, 1);
            statement.setInt(1, item.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
    }

    public void deleteAll() throws SQLException {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from product");
        } catch (SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
    }

    public void showAllData(){
        try{
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM product");
            while (res.next()) {
                String name = res.getString("name");
                System.out.println (res.getShort("id")+" "+name);
            }
            res.close();
            st.close();
        }catch(SQLException e){
            System.out.println("Не вірний SQL запит на вибірку даних");
            e.printStackTrace();
        }
    }

    public List<Product> listByName(String prodName){
        List<Product> productList = new ArrayList<>();
        Product item;
        try{
            PreparedStatement statement = con.prepareStatement("SELECT * FROM product WHERE name=(?)");
            statement.setString(1, prodName);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                item = new Product( res.getString(2), res.getDouble(3), res.getDouble(4));
                item.setId((res.getInt(1)));
                productList.add(item);
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> listByPrice(String price){
        List<Product> productList = new ArrayList<>();
        Product item;
        try{
            PreparedStatement statement = con.prepareStatement("SELECT * FROM product WHERE price "+ price);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                item = new Product( res.getString(2), res.getDouble(3), res.getDouble(4));
                item.setId((res.getInt(1)));
                productList.add(item);
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> listByAmount(String amount){
        List<Product> productList = new ArrayList<>();
        Product item;
        try{
            PreparedStatement statement = con.prepareStatement("SELECT * FROM product WHERE amount "+ amount);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                item = new Product( res.getString(2), res.getDouble(3), res.getDouble(4));
                item.setId((res.getInt(1)));
                productList.add(item);
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Не вірний SQL запит");
            e.printStackTrace();
        }
        return productList;
    }


}
