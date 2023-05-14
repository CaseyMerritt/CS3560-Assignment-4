import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private int number;
    private Date date;
    private double price;
    private String item;

    // Default constructor
    public Order() {
    	number = 0;
    	date = new Date();
    	price = 0.0;
        item = "";
    }

    // Parameterized constructor
    public Order(int number, Date date, double price, String item) {
        this.number = number;
        this.date = date;
        this.price = price;
        this.item = item;
    }

    // Getter for 'number'
    public int getNumber() {
        return number;
    }

    // Setter for 'number'
    public void setNumber(int number) {
        this.number = number;
    }

    // Getter for 'date'
    public Date getDate() {
        return date;
    }

    // Setter for 'date'
    public void setDate(Date date) {
        this.date = date;
    }

    // Getter for 'price'
    public double getPrice() {
        return price;
    }

    // Setter for 'price'
    public void setPrice(double price) {
        this.price = price;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }
}
