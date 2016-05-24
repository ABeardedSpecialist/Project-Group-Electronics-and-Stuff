package Products;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class searchBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private String searchVal;
    private List<product> searchRes = new ArrayList<product>();

    public String getSearchVal() {
        return searchVal;
    }

    public void setSearchVal(String searchVal) {
        this.searchVal = searchVal;
    }

    public List<product> getSearchRes() {
        return searchRes;
    }

    public void setSearchRes(List<product> searchRes) {
        this.searchRes = searchRes;
    }

    public String searchResults() {
        String query = "SELECT * FROM webshop.editview WHERE ProductName LIKE '%" + this.searchVal + "%' OR CategoryName LIKE '%"+this.searchVal+"%'";
        try {
            searchRes.removeAll(searchRes);
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.executeQuery();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                product pr = new product();
                pr.setProductID(rs.getInt(1));
                pr.setProductName(rs.getString(2));
                pr.setProductPrice(rs.getInt(3));
                pr.setProductQuantity(rs.getInt(4));
                pr.setProductImage(rs.getString(5));
                pr.setProductDescription(rs.getString(6));
                pr.setProductCategory(rs.getString(7));
                searchRes.add(pr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return "SearchResults";
    }
}
