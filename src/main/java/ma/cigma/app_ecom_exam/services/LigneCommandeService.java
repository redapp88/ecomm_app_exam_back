package ma.cigma.app_ecom_exam.services;

import java.util.List;

import ma.cigma.app_ecom_exam.domaine.LigneCommandeVo;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
import ma.cigma.app_ecom_exam.entities.Product;

public interface LigneCommandeService {

	void deleteLigneCommande(Long id);
	LigneCommandeVo addLigneCommnde(LigneCommandeVo ligneCommandeVo);
	LigneCommandeVo editLigneCommande(Long id, LigneCommandeVo ligneCommandeVo);
	LigneCommande getLigneCommande(Long id);
	List<LigneCommandeVo> getCart(String username);
	LigneCommande save(LigneCommande ligne);
	List<LigneCommande> getLignesByUsername(String username);
	List <LigneCommande> getLignesByProduct(Product product);
}
