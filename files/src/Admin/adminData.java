package Admin;


import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean (name = "admin")
@Named
@SessionScoped
public class adminData implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private List<admin> aList = new ArrayList<>();
    private admin ad;
    private String user;
    private String pass;

    /*public adminData() {
        ad = new admin();
        adminList();

    }*/

    public void trueEdit(admin ad) {
        ad.setEdit(true);
    }

    public List<admin> getaList() {
        return aList;
    }

    public void setaList(List<admin> aList) {
        this.aList = aList;
    }

    public void createNewAdmin() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String check = "SELECT * FROM webshop.admins WHERE AdminUsername ='" + user + "'";
            Statement bla = conn.createStatement();
            ResultSet rs = bla.executeQuery(check);

            if (!rs.next()) {
                try {
                    String quary = "INSERT INTO webshop.admins (AdminUsername, AdminPassword)" + " VALUES (?,?)";
                    PreparedStatement statement = conn.prepareStatement(quary);
                    statement.setString(1, this.user);
                    statement.setString(2, this.pass);

                    statement.execute();

                } finally {
                    conn.close();
                }

            } else {
                System.out.println("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.user = "";
        this.pass = "";
    }
    public String checkIfAdminExist() {
        System.out.println("start");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            try {
                System.out.println("neh");
                String quary = "SELECT * FROM webshop.admins WHERE AdminUsername = ?";
                PreparedStatement statement = conn.prepareStatement(quary);
                statement.setString(1, this.user);
                ResultSet Username = statement.executeQuery();
                System.out.println("bajs");
                if (Username.next()) {
                    String passwordCheck = "SELECT * FROM webshop.admins WHERE AdminPassword = ?";
                    PreparedStatement statement1 = conn.prepareStatement(passwordCheck);
                    statement1.setString(1, this.pass);
                    ResultSet Password = statement1.executeQuery();
                    System.out.println("kalle");
                    if (Password.next()) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", this.user);
                        return "adminPage";
                    } else {
                        System.out.println("kuken");
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
    private List<admin> adminList() {
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
                ad.setEdit(false);
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

    public admin getAd() {
        return ad;
    }

    public void setAd(admin ad) {
        this.ad = ad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void removeAdmin(admin ad) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "DELETE FROM webshop.admins WHERE AdminUsername = '" + ad.getUsername() + "'";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        adminList();
    }
    public void editAdmin(admin ad) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String query = "UPDATE webshop.admins SET AdminPassword=? WHERE AdminUsername ='" + ad.getUsername() + "'";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, ad.getPassword());

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ad.setEdit(false);
        adminList();

    }
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "AdminLogIn.xhtml";
    }
}