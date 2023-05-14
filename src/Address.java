import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	private int id;
	private String street;
	private String city;
	private String state;
	private int zip;

	@OneToOne(mappedBy = "address")
    private Customer customer;
	
	Address(){
		this.street = "";
		this.city = "";
		this.state = "";
	}
	
	Address(String street, String city, String state, int zip){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setZip(int zip) {
		this.zip = zip;
	}

	public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
