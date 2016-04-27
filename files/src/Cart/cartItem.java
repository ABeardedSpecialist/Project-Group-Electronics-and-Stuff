package Cart;

import Products.product;

/**
 * Created by Michael Johansson(mj223gn) on 2016-04-25.
 */
public class cartItem {

    private product item;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public product getItem() {
        return item;
    }

    public void setItem(product item) {
        this.item = item;
    }
}
