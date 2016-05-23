package Order;

import Cart.Cart;
import Cart.cartItem;
import Products.DatabaseConnection;
import Products.product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class OrderData implements Serializable {

    private static final long serialVersionUID = 1L;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Order order;
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

    public Order getTemp() {
        return temp;
    }

    public void setTemp(Order temp) {
        this.temp = temp;
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
        String createOrderFormQuery = "INSERT INTO webshop.orderid (OrderName, OrderAddress, OrderPhone," +
                " OrderEmail, OrderTotalPrice, OrderNumberOfProducts, OrderStatus)" + " VALUES (?,?,?,?,?,?,?)";
        try {
            cartItemsList = cart.getID();
            PreparedStatement statement = databaseConnection.connect().prepareStatement(createOrderFormQuery, Statement.RETURN_GENERATED_KEYS);
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
                PreparedStatement itemQuery = databaseConnection.connect().prepareStatement(query);
                itemQuery.setInt(1, id);
                itemQuery.setString(2, item.getItem().getProductName());
                itemQuery.setInt(3, item.getQuantity());
                itemQuery.setInt(4, item.getItem().getProductPrice());
                itemQuery.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return "OrderStatusPage";
    }

    public String checkOrder() {
        String query = "SELECT OrderProduct, OrderProductPrice, OrderQuantity FROM webshop.orders WHERE OrderID = " + id;
        try {
            cartItemsList = new ArrayList<>();
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
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
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
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
        String query = "SELECT * FROM webshop.orderid";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();

        }
        return orderList;
    }
    public List<String> getOrderProducts(Order ord){
        List<String> products = new ArrayList<>();
        String query = "SELECT OrderProduct FROM webshop.orders WHERE OrderID ='"+ord.getOrderID()+"'";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                products.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return products;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void editOrder(){
        String query = "UPDATE webshop.orderid SET OrderStatus=? WHERE OrderID = ?";
        try {

            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.setString(1, temp.getStatus());
            statement.setInt(2, temp.getOrderID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }
    public String Edit(Order ord){
        this.temp = ord;
        return "EditOrderTest";
    }
}
