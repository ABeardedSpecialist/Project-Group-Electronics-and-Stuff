package Category;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;
import javax.inject.Named;

import Products.DatabaseConnection;
import Products.product;


@ManagedBean (name = "category")
@Named
@SessionScoped
public class categoryData implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	private List<category> theList=new ArrayList<category>();
	private List<product> catList=new ArrayList<product>();
	private category cate;

	public categoryData(){
		cate = new category();
		ListPlease();
	}
	public category getCate() {
		return cate;
	}

	public void setCate(category cate) {
		this.cate = cate;
	}

	public List<category> getTheList() {
		return theList;
	}

	public void setTheList(List<category> theList) {
		this.theList = theList;
	}

	public List<product> getCatList() {
		return catList;
	}

	public void setCatList(List<product> catList) {
		this.catList = catList;
	}

	private List<category> ListPlease(){
		String query = "SELECT * FROM webshop.category";
		try {
			theList.removeAll(theList);
			PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				category c=new category();
				c.setCatID(rs.getInt(1));
				c.setCategoryName(rs.getString(2));
				theList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseConnection.disconnect();
		}

		return theList;
	}
	public void addNewCategory(){
		String query = "INSERT INTO webshop.category (CategoryName)"+" VALUES (?)";
		try {
			PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
			statement.setString(1, cate.getCategoryName());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseConnection.disconnect();
		}
		ListPlease();
	}
	public void editCategory(category cat){
		String query = "UPDATE webshop.category SET CategoryName=? WHERE CategoryID = ?";
		try {
			PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
			statement.setString(1, cat.getCategoryName());
			statement.setInt(2, cat.getCatID());
			statement.executeUpdate();
			cat.setEditable(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseConnection.disconnect();
		}
	}
	public void removeCategory(category cat){
		String query = "DELETE FROM webshop.category WHERE categoryID = '" + cat.getCatID() + "'";
		try {
			PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
			statement.executeUpdate();
			ListPlease();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseConnection.disconnect();
		}
	}

	public String getProductwithCat(category in){
		String query = "SELECT * FROM webshop.productview WHERE CategoryName ='"+in.getCategoryName()+"'";
		try {
			catList.removeAll(catList);
			PreparedStatement statement = databaseConnection.connect().prepareStatement(query);
			statement.execute();
			ResultSet rs = statement.getResultSet();
			while(rs.next()){
				product pr = new product();
				pr.setProductID(rs.getInt(1));
				pr.setProductName(rs.getString(2));
				pr.setProductPrice(rs.getInt(3));
				pr.setProductQuantity(rs.getInt(4));
				pr.setProductImage(rs.getString(5));
				pr.setProductDescription(rs.getString(6));
				pr.setProductCategory(rs.getString(7));
				catList.add(pr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseConnection.disconnect();
		}
		return "catPage";
	}
}


