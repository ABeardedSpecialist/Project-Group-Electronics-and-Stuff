package Order;

import Cart.Cart;
import Cart.cartItem;
import Products.product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@ManagedBean(name = "orderBean")
@Named
@SessionScoped
public class OrderData implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private Order order;

    public Order getTemp() {
        return temp;
    }

    public void setTemp(Order temp) {
        this.temp = temp;
    }

    private Order temp = new Order();
    private List<cartItem> cartItemsList;
    private List<Order> orderList;
    private int id;

    @Inject
    Cart cart;

    public OrderData() {
        order = new Order();
        id = 0;
        getOrderList();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<cartItem> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<cartItem> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    public String createOrder() {
        try {
            cartItemsList = cart.getID();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "INSERT INTO webshop.orderid (OrderName, OrderAddress, OrderPhone," +
                    " OrderEmail, OrderTotalPrice, OrderNumberOfProducts, OrderStatus)" + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(quary, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getOrderName());
            statement.setString(2, order.getOrderAddress());
            statement.setInt(3, order.getOrderPhone());
            statement.setString(4, order.getOrderEmail());
            statement.setInt(5, cart.getTotalPrice());
            statement.setInt(6, cart.getNumberOfProducts());
            statement.setString(7, "NEW");
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            for (cartItem item : cartItemsList) {
                String query = "INSERT INTO webshop.orders (OrderID, OrderProduct, OrderQuantity, OrderProductPrice)" + " VALUES(?,?,?,?)";
                PreparedStatement itemQuery = conn.prepareStatement(query);
                itemQuery.setInt(1, id);
                itemQuery.setString(2, item.getItem().getProductName());
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
        try {
            cartItemsList = new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT OrderProduct, OrderProductPrice, OrderQuantity FROM webshop.orders WHERE OrderID = " + id;
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            if (!rs.next()) {
                return "InvalidOrderNumber";
            }
            while (rs.next()) {
                cartItem ci = new cartItem();
                product pr = new product();
                pr.setProductName(rs.getString(1));
                pr.setProductPrice(rs.getInt(2));
                ci.setItem(pr);
                ci.setQuantity(rs.getInt(3));
                cartItemsList.add(ci);
            }
            conn.close();
        } catch (
                SQLException e
                )

        {
            e.printStackTrace();
        } catch (
                ClassNotFoundException e
                )

        {
            e.printStackTrace();
        }

        return "OrderStatusPage";
    }

    public int getNumberOfProducts() {
        int quantity = 0;
        for (cartItem cartitem : cartItemsList) {

            quantity += cartitem.getQuantity();

        }
        return quantity;
    }

    public int getTotalPrice() {
        int total = 0;
        for (cartItem cartitem : cartItemsList) {

            total += cartitem.getItem().getProductPrice();

        }
        return total;
    }

    public List<Order> getOrderList() {
        orderList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT * FROM webshop.orderid";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt(1));
                o.setOrderName(rs.getString(2));
                o.setOrderAddress(rs.getString(3));
                o.setOrderPhone(rs.getInt(4));
                o.setOrderEmail(rs.getString(5));
                o.setStatus(rs.getString(8));
                orderList.add(o);
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return orderList;
    }
    public List<String> getOrderProducts(Order ord){
        List<String> products = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT OrderProduct FROM webshop.orders WHERE OrderID ='"+ord.getOrderID()+"'";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                products.add(rs.getString(1));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return products;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void editOrder(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String query = "UPDATE webshop.orderid SET OrderStatus=? WHERE OrderID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, temp.getStatus());
            statement.setInt(2, temp.getOrderID());
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setOrderEdit(Order ord){
        ord.setEditable(true);
    }
    public String Edit(Order ord){
        this.temp = ord;
        return "EditOrderTest";
    }
}
