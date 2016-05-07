package Order;

import java.util.List;

/**
 * Created by Michael Johansson(mj223gn) on 2016-05-05.
 */
public class Order {

    private String OrderName;
    private String OrderEmail;
    private int OrderPhone;
    private String OrderAddress;
    private int OrderTotalPrice;
    private int OrderNumberOfProducts;
    private Status OrderStatus;

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getOrderEmail() {
        return OrderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        OrderEmail = orderEmail;
    }

    public int getOrderPhone() {
        return OrderPhone;
    }

    public void setOrderPhone(int orderPhone) {
        OrderPhone = orderPhone;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        OrderAddress = orderAddress;
    }

    public int getOrderTotalPrice() {
        return OrderTotalPrice;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        OrderTotalPrice = orderTotalPrice;
    }

    public int getOrderNumberOfProducts() {
        return OrderNumberOfProducts;
    }

    public void setOrderNumberOfProducts(int orderNumberOfProducts) {
        OrderNumberOfProducts = orderNumberOfProducts;
    }

    public Status getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(Status orderStatus) {
        OrderStatus = orderStatus;
    }


    public enum Status{
        New, Shipped, Delivered, Delayed, Returned
    }

}
