package Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Cart.Cart;

/**
 * Created by Michael Johansson(mj223gn) on 2016-05-08.
 */
@ManagedBean (name = "kalle")
@Named
@SessionScoped
public class OrderData implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private Order ord;

    public OrderData(){
        ord = new Order();
    }

    public Order getOrd() {
        return ord;
    }

    public void setOrd(Order ord) {
        this.ord = ord;
    }
    public void createOrder(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "INSERT INTO webshop.orderid (OrderName, OrderAddress, OrderPhone, OrderEmail, OrderTotalPrice, OrderNumberOfProducts)"+" VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.setString(1, ord.getOrderName());
            statement.setString(2, ord.getOrderAddress());
            statement.setInt(3, ord.getOrderPhone());
            statement.setString(4, ord.getOrderEmail());
            statement.setInt(5, );

            statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
