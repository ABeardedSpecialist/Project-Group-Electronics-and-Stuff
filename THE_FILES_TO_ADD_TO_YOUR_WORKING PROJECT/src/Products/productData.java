package Products;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean (name = "productBean")
@Named
@SessionScoped

public class productData implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<product> theData = new ArrayList<product>();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private List<product> latestProducts = new ArrayList<>();
    private product pr = new product();
    private Part ImageFile;

    @PostConstruct
    public void imageFromDatabase(){
        String query = "SELECT * FROM webshop.products ORDER BY ProductID DESC LIMIT 5";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {
                product latest = new product();
                latest.setProductID(rs.getInt(1));
                latest.setProductName(rs.getString(2));
                latest.setProductPrice(rs.getInt(3));
                latest.setProductQuantity(rs.getInt(4));
                latest.setProductImage(rs.getString(5));
                latest.setProductDescription(rs.getString(6));
                latest.setCategoryID(rs.getInt(7));
                latestProducts.add(latest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public productData() {
        loadData();
    }
    public List<product> getTheData() {
        return theData;
    }

    public product getPr() {
        return pr;
    }

    public void setPr(product pr) {
        this.pr = pr;
    }

    public void setTheData(List<product> theData) {
        this.theData = theData;
    }

    public Part getImageFile() {
        return ImageFile;
    }

    public void setImageFile(Part input) {
        ImageFile = input;
    }

    public List<product> getLatestProducts() {
        return latestProducts;
    }

    private List<product> loadData() {
        String query = "SELECT * FROM webshop.editview";
        try {
            theData.removeAll(theData);
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.execute();
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
                pr.setCategoryID(rs.getInt(8));
                theData.add(pr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();

        }
        return theData;
    }

    public void addProduct() {
        String query = "INSERT INTO webshop.products (ProductName, ProductPrice, ProductQuantity, " +
                "ProductImage, ProductDescription, ProductCategory)" + " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.setString(1, pr.getProductName());
            statement.setInt(2, pr.getProductPrice());
            statement.setInt(3, pr.getProductQuantity());
            statement.setString(4, pr.getProductImage());
            statement.setString(5, pr.getProductDescription());
            statement.setInt(6, pr.getCategoryID());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        pr = new product();
    }

    public void removeProduct(int in) {
        String query = "DELETE FROM webshop.products WHERE productID = '" + in + "'";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.executeUpdate();
            loadData();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }

    }

    public void editProducts(product prod) {
        String query = "UPDATE webshop.products SET ProductName=?, ProductPrice=?,ProductQuantity=?," +
                "ProductImage=?,ProductDescription=?,ProductCategory=? WHERE productID = ?";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.setString(1, prod.getProductName());
            statement.setInt(2, prod.getProductPrice());
            statement.setInt(3, prod.getProductQuantity());
            statement.setString(4, prod.getProductImage());
            statement.setString(5, prod.getProductDescription());
            statement.setInt(6, prod.getCategoryID());
            statement.setInt(7, prod.getProductID());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        loadData();
    }
    public String getProductPage(product prod){
        String query = "SELECT * FROM webshop.products WHERE ProductID = '"+prod.getProductID()+"'";
        try {
            PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()) {
                pr.setProductID(rs.getInt(1));
                pr.setProductName(rs.getString(2));
                pr.setProductPrice(rs.getInt(3));
                pr.setProductQuantity(rs.getInt(4));
                pr.setProductImage(rs.getString(5));
                pr.setProductDescription(rs.getString(6));
                pr.setProductCategory(rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return "productpage";
    }

    public void editAction(product pr) {
        pr.setEditable(true);
    }

    /**
     * Uploads a Image to the server, replace file if it already exist
     * @param pr Product we want to change image on
     * @throws IOException
     */
    public void fileUpload(product pr) throws IOException {
        InputStream input = ImageFile.getInputStream();
        Files.copy(input, new File("C:\\Users\\Michael\\Desktop\\Webshop2\\Web\\resources\\images", ImageFile.getSubmittedFileName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        pr.setProductImage(ImageFile.getSubmittedFileName());
    }
    public String getShortDescription(product pr){
        String shortDe;
        if(pr.getProductDescription().length() > 25) {
            shortDe = pr.getProductDescription().substring(0, 25);
        }
        else{
            shortDe = pr.getProductDescription();
        }
        return shortDe;
    }
    public boolean checkQuantityZero(product product){
        return (product.getProductQuantity() == 0);
    }

    public String goToNewProduct(){
        pr = new product();
        return "newProduct.xhtml";
    }


}

