package Order;

/**
 * Created by Michael Johansson(mj223gn) on 2016-05-05.
 */
public class Order {

    private String OrderName;
    private String OrderEmail;
    private int OrderPhone;
    private String OrderAddress;

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String input) {
        OrderName = input;
    }

    public String getOrderEmail() {
        return OrderEmail;
    }

    public void setOrderEmail(String input) {
        OrderEmail = input;
    }

    public int getOrderPhone() {
        return OrderPhone;
    }

    public void setOrderPhone(int input) {
        OrderPhone = input;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String input) {
        OrderAddress = input;
    }

}
