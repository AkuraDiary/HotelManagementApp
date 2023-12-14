package Data.DataSource;

import Data.Enums.Enums;
import Data.Model.Customer;
import Data.Model.User;
import Util.Encryption;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomerDataSource {
    private ArrayList<Customer> listCustomer = new ArrayList<>(
            Arrays.asList(
                    new Customer(
                            "12012310312",
                            "David",
                            "david-ntb@gmail.com",
                            "01923910239"
                    )
            )
    );

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(ArrayList<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }



}