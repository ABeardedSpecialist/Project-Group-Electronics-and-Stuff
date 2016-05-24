package Admin;


import Products.DatabaseConnection;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@Named
@SessionScoped
public class adminData implements Serializable {
    private static final long serialVersionUID = 1L;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private List<admin> aList = new ArrayList<>();
    private admin ad = new admin();

    public adminData() {
        adminList();
    }

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
        String check = "SELECT * FROM webshop.admins WHERE AdminUsername ='" + ad.getUsername() + "'";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(check);
            ResultSet rs = statement.executeQuery(check);
            if (!rs.next()) {
                try {
                    String query = "INSERT INTO webshop.admins (AdminUsername, AdminPassword)" + " VALUES (?,?)";
                    PreparedStatement checkPassword = databaseConnection.connect().prepareStatement(query);
                    checkPassword.setString(1, ad.getUsername());
                    checkPassword.setString(2, ad.getPassword());
                    checkPassword.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            adminList();
            ad = new admin();
            databaseConnection.disconnect();
        }

    }
    public String checkIfAdminExist() {
        try {
            String query = "SELECT * FROM webshop.admins WHERE AdminUsername = ?";
            try {
                PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
                statement.setString(1, ad.getUsername());
                ResultSet Username = statement.executeQuery();
                if (Username.next()) {
                    String passwordCheck = "SELECT * FROM webshop.admins WHERE AdminPassword = ?";
                    PreparedStatement statement1 = databaseConnection.connect().prepareStatement(passwordCheck);
                    statement1.setString(1, ad.getPassword());
                    ResultSet Password = statement1.executeQuery();
                    if (Password.next()) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", ad.getUsername());
                        return "adminPage";
                    } else {
                        return "invalid";
                    }
                } else {
                    return "invalid";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "invalid";
        } finally {
            databaseConnection.disconnect();
            ad = new admin();
        }
    }

    private List<admin> adminList(){
        String query = "SELECT * FROM webshop.admins";
        try {
            aList.removeAll(aList);

            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                admin ad = new admin();
                ad.setUsername(rs.getString(1));
                ad.setPassword(rs.getString(2));
                ad.setEdit(false);
                aList.add(ad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();

        }
        return aList;
    }

    public admin getAd() {
        return ad;
    }

    public void setAd(admin ad) {
        this.ad = ad;
    }

    public void removeAdmin(admin ad) {
        String query = "DELETE FROM webshop.admins WHERE AdminUsername = '" + ad.getUsername() + "'";
        try {

            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        adminList();
    }

    public void editAdmin(admin ad) {
        String query = "UPDATE webshop.admins SET AdminPassword=? WHERE AdminUsername ='" + ad.getUsername() + "'";
        try {

            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.setString(1, ad.getPassword());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        ad.setEdit(false);
        adminList();

    }

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "AdminLogIn";
    }
}