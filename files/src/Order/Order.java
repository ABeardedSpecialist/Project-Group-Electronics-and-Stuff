package Order;



/**
 * Created by Michael Johansson(mj223gn) on 2016-05-05.
 */
public class Order {

    private int OrderID;
    private String OrderName;
    private String OrderEmail;
    private int OrderPhone;
    private String OrderAddress;
    private String status;
    private boolean editable;

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

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

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String st){
        this.status = st;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        System.out.println(editable);
        this.editable = editable;
    }
}
