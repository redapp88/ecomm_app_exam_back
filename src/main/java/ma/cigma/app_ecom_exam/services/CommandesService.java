package ma.cigma.app_ecom_exam.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.app_ecom_exam.domaine.CommandeVo;
import ma.cigma.app_ecom_exam.entities.Commande;

public interface CommandesService {

	public CommandeVo addCommande(CommandeVo commandeVo);
	public CommandeVo editCommande(Long id,CommandeVo commandeVo);
	public List<CommandeVo> getCommandes(String username, String state);
	public void deleteCommande(Long id);
	Commande getCommande(Long id);
	public Commande Save(Commande commande);
	
	
}
