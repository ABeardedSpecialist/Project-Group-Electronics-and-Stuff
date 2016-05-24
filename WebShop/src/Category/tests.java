package Category;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.junit.Test;

import Products.product;

public class tests {
	private static final long serialVersionUID = 1L;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
	private List<category> theList=new ArrayList<category>();
	private List<product> catList=new ArrayList<product>();
	private String testing;
	@Test
	public void test() {
		try{
		catList.removeAll(catList);
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");
		String quary = "SELECT * FROM webshop.productview WHERE CategoryName ='"+"Laptops"+"';";
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
		pr.setProductSubcategory(rs.getString(8));
		catList.add(pr);}

		conn.close();
for(int i=0; i<catList.size();i++){
	System.out.println(catList.get(i).getProductName());
}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}

}
