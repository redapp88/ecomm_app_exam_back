package ma.cigma.app_ecom_exam.domaine;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeVo {
	private Long id;
	private AppUserVo user;
	private Date dateCommande;
	private String state;
	private String payementMethode;
	private String shippingAdress;
	private String shippingCity;
	private float total;
	private String commandeDetails;
	
}
