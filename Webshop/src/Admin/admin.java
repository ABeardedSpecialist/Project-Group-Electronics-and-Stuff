package Admin;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-11.
 */
@Named
@SessionScoped
public class admin implements Serializable {
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private String Username;
    private String Password;

    public void setUsername(String input) {
        this.Username = input;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setPassword(String input) {
        this.Password = input;
    }

    public String getPassword() {
        return this.Password;
    }

    public String createNewAdmin() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "root", "Po37j8xd");
            try {
                String quary = "INSERT INTO webshop.admins (AdminUsername, AdminPassword)" + " VALUES (?,?)";
                PreparedStatement statement = conn.prepareStatement(quary);
                statement.setString(1, Username);
                statement.setString(2, Password);

                statement.execute();
            } finally {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "correct";
    }

    public String checkIfAdminExist() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "root", "Po37j8xd");
            try {
                String quary = "SELECT * FROM webshop.admins WHERE AdminUsername = ?";
                PreparedStatement statement = conn.prepareStatement(quary);
                statement.setString(1, Username);
                ResultSet Username = statement.executeQuery();
                if (Username.next()) {
                    String passwordCheck = "SELECT * FROM webshop.admins WHERE AdminPassword = ?";
                    PreparedStatement statement1 = conn.prepareStatement(passwordCheck);
                    statement1.setString(1, Password);
                    ResultSet Password = statement1.executeQuery();
                    if (Password.next()) {
                        return "addNewAdmin";
                    } else {
                        return "invalid";
                    }
                } else {
                    return "invalid";
                }
            } finally {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    return "invalid";
    }


}

