package Admin;



/**
 * Created by Michael Johansson(mj223gn) on 2016-04-11.
 */

public class admin {
    /**
     *
     */
  
    private String Username;
    private String Password;
    private boolean edit;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edt) {
        edit = edt;
    }


   

    public void setUsername(String input) {
        this.Username = input;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setPassword(String input) {
        this.Password = input;
    }

    public String getPassword() {
        return this.Password;
    }

  

   
}


