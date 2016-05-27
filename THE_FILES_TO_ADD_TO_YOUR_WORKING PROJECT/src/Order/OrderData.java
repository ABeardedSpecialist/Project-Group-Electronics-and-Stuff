package Order;

import Cart.Cart;
import Cart.cartItem;
import Products.DatabaseConnection;
import Products.product;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Order order;
    private List<cartItem> cartItemsList;
    private List<Order> orderList;
    private int id;

    @Inject
    Cart cart;

    
    
    public OrderData() {
        order = new Order();
        id = 0;
        LoadTheList();
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
            order.setStatus("NEW");
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderID(rs.getInt(1));
            }
            for (cartItem item : cartItemsList) {
                String query = "INSERT INTO webshop.orders (OrderID, OrderProduct, OrderQuantity, OrderProductPrice)" + " VALUES(?,?,?,?)";
                PreparedStatement itemQuery = databaseConnection.connect().prepareStatement(query);
                itemQuery.setInt(1, order.getOrderID());
                itemQuery.setString(2, item.getItem().getProductName());
                itemQuery.setInt(3, item.getQuantity());
                itemQuery.setInt(4, item.getItem().getProductPrice());
                itemQuery.execute();
            }
            for (cartItem ci : cartItemsList) {
                int quantity = getQuantityFromDatabase(ci.getItem());

                String changeQuantity = "UPDATE webshop.products SET ProductQuantity = ? WHERE ProductID = ?";
                PreparedStatement updateQuantity = databaseConnection.connect().prepareStatement(changeQuantity);
                updateQuantity.setInt(1, quantity - ci.getQuantity());
                updateQuantity.setInt(2, ci.getItem().getProductID());
                updateQuantity.executeUpdate();
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
            while (rs.next()) {
                cartItem ci = new cartItem();
                product pr = new product();
                pr.setProductName(rs.getString(1));
                pr.setProductPrice(rs.getInt(2));
                ci.setItem(pr);
                ci.setQuantity(rs.getInt(3));
                cartItemsList.add(ci);
            }
            if(cartItemsList.isEmpty()){
                return "InvalidOrderNumber";
            }
            else{
                String getOrderStatus = "SELECT OrderID, OrderStatus FROM webshop.orderid WHERE OrderID = " + id;
                try{
                    PreparedStatement OrderStatus = databaseConnection.connect().prepareStatement(getOrderStatus);
                    OrderStatus.execute();
                    ResultSet resultSet = OrderStatus.getResultSet();
                    if(resultSet.next()){
                        order.setOrderID(resultSet.getInt(1));
                        order.setStatus(resultSet.getString(2));
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
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
            total += (cartitem.getItem().getProductPrice() * cartitem.getQuantity());
        }
        return total;
    }

    public List<Order> LoadTheList() {
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
    public List<String> LoadOrderList(Order ord){
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void editOrder(Order ord){
  
        String query = "UPDATE webshop.orderid SET OrderStatus=? WHERE OrderID = ?";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            System.out.println(ord.getStatus());
            statement.setString(1, ord.getStatus());
            statement.setInt(2, ord.getOrderID());
            statement.executeUpdate();
            ord.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public int getQuantityFromDatabase(product product){
        String query = "SELECT ProductQuantity FROM webshop.products WHERE ProductID = ?";
        int output = 0;
        try {

            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.setInt(1, product.getProductID());
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()) {
                output = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return output;
    }
    public List<String> statusList(){
        List<String> status = new ArrayList<>();
        status.add("NEW");
        status.add("SHIPPED");
        status.add("DELAYED");
        status.add("DELIVERED");

        return status;
    }
    public String goToCheckOrderStatus(){
        id = 0;
        return "check.xhtml";
    }

}
