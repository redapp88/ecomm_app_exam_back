package ma.cigma.app_ecom_exam.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

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
public class LigneCommande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@OneToOne
	private Product product;
	@NonNull
	private int quantity;
	@ManyToOne
	private AppUser user;
	public LigneCommande(Product product, int quantity,AppUser user) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.user=user;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return this.id.equals(((LigneCommande)o).getId());
	}
	
	
	
	
	
}
