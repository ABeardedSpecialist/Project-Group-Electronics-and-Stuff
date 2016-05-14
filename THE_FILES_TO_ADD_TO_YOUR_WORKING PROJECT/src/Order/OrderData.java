package Order;

import Cart.Cart;
import Cart.cartItem;
import Products.product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michael Johansson(mj223gn) on 2016-05-08.
 */
@ManagedBean(name = "order")
@Named
@SessionScoped
public class OrderData implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private Order ord;
    private List<cartItem> cartItemsList;
    private int id;

    @Inject
    Cart cart;

    public OrderData() {
        ord = new Order();
        ord.setStatus("NEW");
        id = 0;
    }

    public Order getOrd() {
        return ord;
    }

    public void setOrd(Order ord) {
        this.ord = ord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String createOrder() {
        try {
            cartItemsList = cart.getID();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "INSERT INTO webshop.orderid (OrderName, OrderAddress, OrderPhone," +
                    " OrderEmail, OrderTotalPrice, OrderNumberOfProducts, OrderStatus)" + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(quary, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ord.getOrderName());
            statement.setString(2, ord.getOrderAddress());
            statement.setInt(3, ord.getOrderPhone());
            statement.setString(4, ord.getOrderEmail());
            statement.setInt(5, cart.getTotalPrice());
            statement.setInt(6, cart.getNumberOfProducts());
            statement.setString(7, ord.getStatus());
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            for (cartItem item : cartItemsList) {
                String query = "INSERT INTO webshop.orders (OrderID, OrderProduct, OrderQuantity, OrderProductPrice)" + " VALUES(?,?,?,?)";
                PreparedStatement itemQuery = conn.prepareStatement(query);
                itemQuery.setInt(1, id);
                itemQuery.setInt(2, item.getItem().getProductID());
                itemQuery.setInt(3, item.getQuantity());
                itemQuery.setInt(4, item.getItem().getProductPrice());
                itemQuery.execute();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "OrderStatusPage";
    }

    public String checkOrder() {
        cartItemsList = new ArrayList<>();
        try {
            cartItemsList = cart.getID();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT OrderProduct FROM webshop.orders WHERE OrderID = " + id;
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int proID = rs.getInt(1);
                String getProduct = "SELECT productID, productName, productImage, productDescription FROM webshop.products WHERE productID = '" + proID + "'";
                PreparedStatement preparedStatement = conn.prepareStatement(getProduct);
                preparedStatement.execute();
                ResultSet proRs = preparedStatement.getResultSet();
                while (proRs.next()) {
                    product pr = new product();
                    pr.setProductID(proRs.getInt(1));
                    pr.setProductName(proRs.getString(2));
                    pr.setProductImage(proRs.getString(3));
                    pr.setProductDescription(proRs.getString(4));

                    String getPriceAndQuantity = "SELECT OrderProductPrice, OrderQuantity FROM webshop.orders WHERE OrderID = "+ id +" AND OrderProduct = " + proID;
                    PreparedStatement pAndQ = conn.prepareStatement(getPriceAndQuantity);
                    pAndQ.execute();
                    ResultSet PQ = pAndQ.getResultSet();

                    if(PQ.next()) {
                        pr.setProductPrice(PQ.getInt(1));

                        cartItem item = new cartItem();
                        item.setQuantity(PQ.getInt(2));
                        item.setItem(pr);
                        cartItemsList.add(item);

                    }
                }
            }

        conn.close();
    }

    catch(
    SQLException e
    )

    {
        e.printStackTrace();
    }

    catch(
    ClassNotFoundException e
    )

    {
        e.printStackTrace();
    }

    return"OrderStatusPage";
}

}
