package Admin;


import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-11.
 */
@ManagedBean
@Named
@SessionScoped
public class admin implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private List<admin> aList = new ArrayList<>();
    private String Username;
    private String Password;
    private boolean edit;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edt) {
        edit = edt;
    }


    public List<admin> getaList() {
        adminList();
        return aList;
    }

    public void setaList(List<admin> aList) {
        this.aList = aList;
    }


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
        String result = "invalid";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String check = "SELECT * FROM webshop.admins WHERE AdminUsername ='" + this.Username + "'";
            Statement bla = conn.createStatement();
            ResultSet rs = bla.executeQuery(check);

            if (!rs.next()) {
                try {
                    String quary = "INSERT INTO webshop.admins (AdminUsername, AdminPassword)" + " VALUES (?,?)";
                    PreparedStatement statement = conn.prepareStatement(quary);
                    statement.setString(1, this.Username);
                    statement.setString(2, this.Password);

                    statement.execute();

                } finally {
                    conn.close();
                }
                result = "correct";
            } else {
                result = "invalid";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String checkIfAdminExist() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            try {
                String quary = "SELECT * FROM webshop.admins WHERE AdminUsername = ?";
                PreparedStatement statement = conn.prepareStatement(quary);
                statement.setString(1, this.Username);
                ResultSet Username = statement.executeQuery();
                if (Username.next()) {
                    String passwordCheck = "SELECT * FROM webshop.admins WHERE AdminPassword = ?";
                    PreparedStatement statement1 = conn.prepareStatement(passwordCheck);
                    statement1.setString(1, this.Password);
                    ResultSet Password = statement1.executeQuery();
                    if (Password.next()) {
                        return "adminPage";
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

    public List<admin> adminList() {

        try {
            aList.removeAll(aList);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT * FROM webshop.admins";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()) {
                admin ad = new admin();
                ad.setUsername(rs.getString(1));
                ad.setPassword(rs.getString(2));
                ad.edit = false;
                aList.add(ad);
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return aList;
    }

    public void removeAdmin(String User) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "DELETE FROM webshop.admins WHERE username = " + User;
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String editAdmin(admin ad) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String query = "UPDATE webshop.admins SET Password=? WHERE Username ="+ad.Username;
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ad.Password);

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ad.setEdit(false);
        adminList();
        return "isdaw";

    }
    public void trueEdit(admin ad){
        ad.setEdit(true);
    }

}

