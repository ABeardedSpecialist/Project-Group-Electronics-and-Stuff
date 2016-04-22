package Products;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-22.
 */
@Named
@SessionScoped
public class Cart implements Serializable {

    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private List<product> cartList = new ArrayList<>();
    private List<Integer> ID = new ArrayList<>(Arrays.asList(2,3,8,6));
    private int totalPrice;
    private int numberOfProducts;


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void addProductToCart(int id) {
        ID.add(id);
    }

    public void removeProductFromCart(int id) {
        ID.remove(ID.indexOf(id));
    }

    public List<Integer> getID() {
        return ID;
    }

    public List<Integer> getQuantity(int quantity){
        List<Integer> Quantity = new ArrayList<>();
        for (int i = 1; i <= quantity; i++){
            Quantity.add(i);
        }
        return Quantity;
    }

    public List<product> getCartList() {
        cartList.removeAll(cartList);
        totalPrice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            for (int id : ID) {
                try {
                    String quary = "SELECT * FROM productview WHERE productID = " + id;
                    PreparedStatement statement = conn.prepareStatement(quary);
                    statement.execute();
                    ResultSet rs = statement.getResultSet();

                    while (rs.next()) {
                        product pr = new product();
                        pr.setProductID(rs.getInt(1));
                        pr.setProductName(rs.getString(2));
                        pr.setProductPrice(rs.getInt(3));
                        pr.setProductQuantity(rs.getInt(4));
                        pr.setProductImage(rs.getString(5));
                        pr.setProductDescription(rs.getString(6));
                        pr.setProductCategory(rs.getString(7));
                        pr.setProductSubcategory(rs.getString(8));
                        cartList.add(pr);
                        totalPrice += pr.getProductPrice();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return cartList;
    }
}
