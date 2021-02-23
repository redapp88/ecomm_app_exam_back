package ma.cigma.app_ecom_exam.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne
	private AppUser user;
	@NonNull
	private Date dateCommande;
	@NotNull
	private String state;
	@NotNull
	private String payementMethode;
	@NotNull
	private String shippingAdress;
	@NotNull
	private String shippingCity;
	private float total;
	@Lob
	private String commandeDetails;
	public Commande(AppUser user, String payementMethode, String shippingAdress,String city,float total) {
		super();
		//System.out.println(shippingAdress);
		this.user = user;
		this.payementMethode = payementMethode;
		this.shippingAdress = shippingAdress;
		this.shippingCity=city;
		this.state="WAITING";
		this.dateCommande=new Date();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.id.equals(((Commande)o).getId());
	}
	
}
