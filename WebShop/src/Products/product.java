package Products;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-12.
 */
@Named
@SessionScoped
public class product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
	private int productID;
	private String productName;
	private int productPrice;
	private int productQuantity;
	private String productImage;
	private String productDescription;
	private String productCategory;
	private String productSubcategory;
	private boolean editable;
	public String getProductName() {
		return productName;
	}

	public void setProductName(String input) {
		this.productName = input;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int input) {
		this.productPrice = input;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int input) {
		this.productQuantity = input;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String input) {
		this.productImage = input;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String input) {
		this.productDescription = input;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String input) {
		this.productCategory = input;
	}

	public String getProductSubcategory() {
		return productSubcategory;
	}

	public void setProductSubcategory(String input) {
		this.productSubcategory = input;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	public boolean isEditable(){
		return this.editable;
	}
	
	public  void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public void editAction(product pr) {
	    
	pr.setEditable(true);
	
	}
public String saveAction() {
	    
		
		for (product pr : getProductsList()){
			this.setEditable(false);
	     editProducts(pr.productID);
		}
		return null;
		
	}
	
	
	public String addProduct() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "INSERT INTO webshop.products (ProductName, ProductPrice, ProductQuantity, " +
					"ProductImage, ProductDescription, ProductCategory, ProductSubcategory)" + " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.setString(1, this.productName);
			statement.setInt(2, this.productPrice);
			statement.setInt(3, this.productQuantity);
			statement.setString(4, this.productImage);
			statement.setString(5, this.productDescription);
			statement.setString(6, this.productCategory);
			statement.setString(7, this.productSubcategory);

			statement.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "ldaw";
	}

	public void removeProduct() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "DELETE FROM webshop.products WHERE productID = ?";
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.setInt(1, this.productID);

			statement.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getProduct(int id){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "SELECT * FROM webshop.products WHERE productID ="+id;
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.setInt(1, this.productID);
			statement.setString(1, this.productName);
			statement.setInt(2, this.productPrice);
			statement.setInt(3, this.productQuantity);
			statement.setString(4, this.productImage);
			statement.setString(5, this.productDescription);
			statement.setString(6, this.productCategory);
			statement.setString(7, this.productSubcategory);

			statement.execute();

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<product> getProductsList() {
		List<product> output = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "SELECT * FROM webshop.products";
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
				pr.setProductSubcategory(rs.getString(8));
				output.add(pr);
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String editProducts(int id){
		try{
			Class.forName("com.myseql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
			String query = "UPDATE webshop.products SET (ProductName, ProductPrice, Product Quantity," + 
					"ProductImage, ProductDescription, ProductCategory, ProductSubcategory"+
					"VALUES (?,?,?,?,?,?,?)"+
					"WHERE productID = "+id;
			PreparedStatement statement=conn.prepareStatement(query);
			statement.setString(1, this.productName);
			statement.setInt(2, this.productPrice);
			statement.setInt(3, this.productQuantity);
			statement.setString(4, this.productImage);
			statement.setString(5, this.productDescription);
			statement.setString(6, this.productCategory);
			statement.setString(7, this.productSubcategory);
			statement.execute();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return "ldaw";}


}


