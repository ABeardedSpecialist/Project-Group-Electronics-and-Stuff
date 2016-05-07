package Order;


import Cart.cartItem;
import Products.product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Created by Michael Johansson(mj223gn) on 2016-05-05.
 */
@Named
@SessionScoped
@ManagedBean
public class OrderData implements Serializable {

    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private int ID;
    private Order od;
    private List<cartItem> OrderProductList;

    public OrderData() {
        od = new Order();
    }

    public String setod(List<cartItem> list) {
        setOrderProductList(list);
        return "OrderForm";
    }

    public Order getOd() {
        return this.od;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public List<cartItem> getOrderProductList() {
        try {
            OrderProductList.removeAll(OrderProductList);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String quary = "SELECT OrderProduct FROM webshop.orders WHERE OrderID ='" + ID + "'";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                String pro = "SELECT * FROM webshop.products WHERE ProductID = '" + rs.getInt(1) + "';";
                PreparedStatement state = conn.prepareStatement(pro);
                state.execute();
                ResultSet set = state.getResultSet();
                while (set.next()) {

                    product pr = new product();
                    pr.setProductID(rs.getInt(1));
                    pr.setProductName(rs.getString(2));
                    pr.setProductPrice(rs.getInt(3));
                    pr.setProductQuantity(rs.getInt(4));
                    pr.setProductImage(rs.getString(5));
                    pr.setProductDescription(rs.getString(6));
                    pr.setProductCategory(rs.getString(7));

                    for (cartItem items : OrderProductList) {
                        if (pr.getProductID() == items.getItem().getProductID()) {
                            items.setQuantity(items.getQuantity() + 1);
                        } else {

                            cartItem ci = new cartItem();
                            ci.setItem(pr);
                            ci.setQuantity(1);
                            OrderProductList.add(ci);
                        }
                    }

                    conn.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return OrderProductList;
    }

    public void setOrderProductList(List<cartItem> ProductList) {

        OrderProductList.addAll(ProductList);

    }

    public int getOrderTotalPrice() {
        int totalprice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String quary = "SELECT OrderTotalPrice FROM webshop.orderid WHERE OrderID ='" + ID + "'";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            totalprice = rs.getInt(1);

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return totalprice;
    }

    public String sendOrderToDatabase() {
        System.out.println("start");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String quary = "INSERT INTO webshop.orderid (OrderName, OrderAddress, OrderPhone,OrderEmail, OrderTotalPrice, OrderNumberOfProducts)" + " VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.setString(1, od.getOrderName());
            statement.setString(2, od.getOrderAddress());
            statement.setInt(3, od.getOrderPhone());
            statement.setString(4, od.getOrderEmail());
            statement.setInt(5, od.getOrderTotalPrice());
            statement.setInt(6, od.getOrderNumberOfProducts());
            statement.execute();
            System.out.println("query");

            String getID = "SELECT LAST_OrderID";
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        sendProductsToOrder();
        return "OrderPage";
    }

    public void sendProductsToOrder() {
        System.out.println("Start order");
        for (cartItem ci : OrderProductList) {
            System.out.println("one product");
            for (int i = 1; i <= ci.getQuantity(); i++) {
                try {
                    System.out.println("start qu");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
                    String quary = "INSERT INTO webshop.orders (OrderID, OrderProduct)" + " VALUES (?,?)";
                    PreparedStatement statement = conn.prepareStatement(quary);
                    statement.setInt(1, ID);
                    statement.setInt(2, ci.getItem().getProductID());
                    statement.execute();
                    System.out.println("slut qu");
                    conn.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("slut");
            }
        }
    }

}
