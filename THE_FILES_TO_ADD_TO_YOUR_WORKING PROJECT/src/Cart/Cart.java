package Cart;

import Products.product;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-22.
 */
@Named
@SessionScoped
@ManagedBean (name = "Cart")
public class Cart implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<cartItem> ID = new ArrayList<>();
    private int totalPrice;
    private int numberOfProducts;

    public int getTotalPrice() {
        totalPrice = 0;
        for (cartItem ci:ID) {
            totalPrice += (ci.getQuantity()*ci.getItem().getProductPrice());
        }
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberOfProducts() {
        numberOfProducts = 0;
        for (cartItem ci:ID) {
            numberOfProducts += ci.getQuantity();
        }
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
    public void removeProductFromCart(cartItem ci) {
        ID.remove(ID.indexOf(ci));
    }


    public List<Integer> getQuantity(int quantity){
        List<Integer> Quantity = new ArrayList<>();
        for (int i = 1; i <= quantity; i++){
            Quantity.add(i);
        }
        return Quantity;
    }
    public void addProductToCart(product prod){
        for (cartItem ci: ID) {
            if(ci.getItem().getProductID() == prod.getProductID()){
                if (ci.getQuantity()+1 > ci.getItem().getProductQuantity()){
                    return;
                }
                else{
                    ci.setQuantity(ci.getQuantity()+1);
                    return;
                }
            }
        }
        cartItem ci = new cartItem();
        ci.setQuantity(1);
        ci.setItem(prod);
        ID.add(ci);
    }
    public List<cartItem> getID() {
        return ID;
    }
    public void setID(List<cartItem> ID) {
        this.ID = ID;
    }

    public String zeroCart(){
        ID.removeAll(ID);
        totalPrice = 0;
        numberOfProducts = 0;
        return "index";
    }

}
