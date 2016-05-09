package Category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import Products.product;



@ManagedBean
@Named
@RequestScoped

public class categoryData implements Serializable {
	@ManagedProperty("#{param.test}")
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
	private List<category> theList=new ArrayList<category>();
	private List<product> catList=new ArrayList<product>();

	public categoryData(){
		ListPlease();
	}

	private List<category> ListPlease(){

		try {
			theList.removeAll(theList);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "SELECT * FROM webshop.category";
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.execute();
			ResultSet rs = statement.getResultSet();


			while (rs.next()) {
				category c=new category();
				c.setCatID(rs.getInt(1));
				c.setCategoryName(rs.getString(2));

				theList.add(c);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return theList;
	}

	public String getProductwithCat(category in){
		try {
			catList.removeAll(catList);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
			String quary = "SELECT * FROM webshop.productview WHERE CategoryName ='"+in.getCategoryName()+"'";
			PreparedStatement statement = conn.prepareStatement(quary);
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
			catList.add(pr);}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "catPage";
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

	
}


