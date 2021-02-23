package ma.cigma.app_ecom_exam.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {



	@Id
	private String username;
	private String password;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String adresse;
	@NotNull
	@Email
	@Column(unique=true)
	private String mail;
	@NotNull
	private String city;
	@NotNull
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phone;
	@NotNull
	private Date subsDate;
	@NotNull
	private String state;
	@NotNull
	@ManyToOne
	private AppRole appRole;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LigneCommande> cart = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Commande> commandes = new ArrayList<>();
	
	public AppUser(String username, String password, String firstName, String lastName, String adresse,
			String city,String mail, String phone, AppRole appRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.mail = mail;
		this.city = city;
		this.phone = phone;
		this.appRole = appRole;
		this.subsDate=new Date();
		this.state="ACTIVE";
	}
	public String getPassword() {
		return password;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return this.username.equals(((AppUser)o).getUsername());
	}
	
	

}
