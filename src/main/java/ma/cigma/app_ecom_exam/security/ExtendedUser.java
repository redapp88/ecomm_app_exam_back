package ma.cigma.app_ecom_exam.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class ExtendedUser extends User {
	private String firstName;
	private String lastName;
	private String state;
	private String adress;
	private String city;
public ExtendedUser(String username,String password,String firsName,String lastName,String adress,String city,String state, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.firstName=firsName;
		this.lastName=lastName;
		this.state=state;
		this.adress=adress;
		this.city=city;
		
	}
public String getFirstName() {
	return firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}



	

}
