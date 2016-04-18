package Products;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Named;
@ManagedBean (name="productData")
@Named
@SessionScoped
public class productData implements Serializable {


	private static final long serialVersionUID = 1L;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
	private List<product> theData=new ArrayList<product>();
	private product pr;
	
	
	
public productData(){
	 pr=new product();
		loadData();
	}
	public List<product> getTheData() {
		
		return theData;
	}
	
	public void setTheData(List<product> theData) {
		this.theData = theData;
	}
	
	private void loadData(){
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
				theData.add(pr);
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String addProduct() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "INSERT INTO webshop.products (ProductName, ProductPrice, ProductQuantity, " +
					"ProductImage, ProductDescription, ProductCategory, ProductSubcategory)" + " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.setString(1,pr.getProductName() );
			statement.setInt(2,pr.getProductPrice() );
			statement.setInt(3,pr.getProductQuantity() );
			statement.setString(4,pr.getProductImage() );
			statement.setString(5,pr.getProductDescription() );
			statement.setString(6,pr.getProductCategory() );
			statement.setString(7,pr.getProductSubcategory() );
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
product theProd=new product();
			String quary = "SELECT * FROM webshop.products WHERE productID ="+id;
			PreparedStatement statement = conn.prepareStatement(quary);
		
		

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



	public String editProducts(int id){
		try{
			Class.forName("com.myseql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
			String query = "UPDATE webshop.products SET (ProductName, ProductPrice, ProductQuantity," + 
					"ProductImage, ProductDescription, ProductCategory, ProductSubcategory"+
					"VALUES (?,?,?,?,?,?,?)"+
					"WHERE productID = "+id;
			PreparedStatement statement=conn.prepareStatement(query);
		
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return "ldaw";}
	
	public void editAction(product pr) {
	    
		pr.setEditable(true);
		
	}
	public product getPr() {
		return pr;
	}
	public void setPr(product pr) {
		this.pr = pr;
	}
	
	

	}

