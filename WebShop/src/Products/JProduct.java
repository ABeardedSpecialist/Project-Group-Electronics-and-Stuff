package Products;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class JProduct {

	@Test
	public void test() {
	product testing=new product();
	List<product> a=testing.getProductsList();
	for(product i:a){
		System.out.println(i.getProductName());
		
		System.out.println(i.isEditable());
		
	}
	
	
	}

}
