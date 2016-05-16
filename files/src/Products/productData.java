package Products;

import Category.category;
import com.sun.prism.Image;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean (name = "productData")
@Named
@SessionScoped

public class productData implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
    private List<product> theData = new ArrayList<product>();
    private product pr;
    private Part ImageFile;

    public productData() {
        pr = new product();
        loadData();
    }

    public List<product> getTheData() {

        return theData;
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

    private List<product> loadData() {
        try {
            theData.removeAll(theData);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "SELECT * FROM webshop.editview";
            PreparedStatement statement = conn.prepareStatement(quary);
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

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return theData;
    }

    public void addProduct() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "INSERT INTO webshop.products (ProductName, ProductPrice, ProductQuantity, " +
                    "ProductImage, ProductDescription, ProductCategory)" + " VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.setString(1, pr.getProductName());
            statement.setInt(2, pr.getProductPrice());
            statement.setInt(3, pr.getProductQuantity());
            statement.setString(4, pr.getProductImage());
            statement.setString(5, pr.getProductDescription());
            statement.setString(6, pr.getProductCategory());
            statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int in) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

            String quary = "DELETE FROM webshop.products WHERE productID = " + in;
            PreparedStatement statement = conn.prepareStatement(quary);
            statement.executeUpdate();
            conn.close();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String editProducts(product prod) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String query = "UPDATE webshop.products SET ProductName=?, ProductPrice=?,ProductQuantity=?," +
                    "ProductImage=?,ProductDescription=?,ProductCategory=? WHERE productID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, prod.getProductName());
            statement.setInt(2, prod.getProductPrice());
            statement.setInt(3, prod.getProductQuantity());
            statement.setString(4, prod.getProductImage());
            statement.setString(5, prod.getProductDescription());
            statement.setInt(6, prod.getCategoryID());
            statement.setInt(7, prod.getProductID());
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        prod.setEditable(false);
        loadData();
        return "ldaw";
    }
    public String getProductPage(product prod){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
            String query = "SELECT * FROM webshop.products WHERE ProductID = '"+prod.getProductID()+"'";
            System.out.println(prod.getProductID());
            PreparedStatement statement = conn.prepareStatement(query);

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
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "productpage.xhtml";
    }

    public void editAction(product pr) {

        pr.setEditable(true);

    }

    public product getPr() {
        return pr;
    }

    public void setPr(product pr) {
        this.pr = pr;
    }

    public String edit() {
        loadData();
        return "editProduct";
    }

    /**
     * Uploads a Image to the server, replace file if it already exist
     * @param pr Product we want to change image on
     * @throws IOException
     */
    public void fileUpload(product pr) throws IOException {
        InputStream input = ImageFile.getInputStream();
        Files.copy(input, new File("C:\\Users\\Michael\\IdeaProjects\\Webshop\\web\\images", ImageFile.getSubmittedFileName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        pr.setProductImage("C:\\Users\\Michael\\IdeaProjects\\Webshop\\web\\images\\" + ImageFile.getSubmittedFileName());
    }

}

