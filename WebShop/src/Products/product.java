package Products;



/**
 * Created by Michael Johansson(mj223gn) on 2016-04-12.
 */

public class product  {
	/**
	 * 
	 */

	private int productID;
	private String productName;
	private int productPrice;
	private int productQuantity;
	private String productImage;
	private String productDescription;
	private String productCategory;
	private String productSubcategory;
	private boolean editable;
	private static final String sql_connection = "jdbc:mysql://localhost:3306/webshop";
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
		return editable;
	}

	public  String setEditable(boolean makeIt) {
		editable = makeIt;
		return null;
	}

	
}
