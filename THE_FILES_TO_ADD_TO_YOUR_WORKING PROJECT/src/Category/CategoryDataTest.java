package Category;

import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class CategoryDataTest {
	private static final long serialVersionUID = 1L;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
	private List<category> theList=new ArrayList<category>();
	@Test
	public void test() {
		try {
			theList.removeAll(theList);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(sql_connection, "DBTest", "A.1337,Black.");

			String quary = "SELECT * FROM webshop.category";
			PreparedStatement statement = conn.prepareStatement(quary);
			statement.execute();
			ResultSet rs = statement.getResultSet();


			while (rs.next()) {
				category c = new category();
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
		
		for(int i=0;i<theList.size();i++){
			System.out.println(theList.get(i).getCategoryName());
		}
	}

}
