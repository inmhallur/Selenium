package testWebsite;

public class Customer {
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zipCode;
	private String userName;
	private String password;
	
	public Customer(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setAddress1(String addressl1){
		this.addressLine1 = addressl1;
	}
	
	public String getAddress1(){
		return addressLine1;
	}
	
	public void setAddress2(String addressl2){
		this.addressLine2 = addressl2;
	}
	
	public String getAddress2(){
		return addressLine2;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	
	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}
	
	public String getZipCode(){
		return zipCode;
	}
	


}
