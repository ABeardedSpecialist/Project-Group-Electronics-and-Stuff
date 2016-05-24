package Products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Michaels on 2016-05-21.
 */
public class DatabaseConnection {

    private static final String SQL_CONNECTION = "jdbc:mysql://localhost:3306/webshop";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "DBTest";
    private static final String PASSWORD = "A.1337,Black.";
    private Connection connection;
    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(SQL_CONNECTION, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
