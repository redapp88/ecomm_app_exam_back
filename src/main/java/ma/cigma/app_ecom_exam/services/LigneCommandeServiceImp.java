package ma.cigma.app_ecom_exam.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.cigma.app_ecom_exam.dao.AppUserRepository;
import ma.cigma.app_ecom_exam.dao.LigneCommandeRepository;
import ma.cigma.app_ecom_exam.domaine.LigneCommandeConverter;
import ma.cigma.app_ecom_exam.domaine.LigneCommandeVo;
import ma.cigma.app_ecom_exam.domaine.ProductConverter;
import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
import ma.cigma.app_ecom_exam.entities.Product;
@Service
@Transactional
public class LigneCommandeServiceImp implements LigneCommandeService {
@Autowired
private LigneCommandeRepository ligneCommandeRepository;
@Autowired
private AppUserRepository appUserRepository;
@Autowired
private UsersService usersService;
@Autowired
private ProductsService productsService;
	@Override
	public void deleteLigneCommande(Long id) {
		LigneCommande ligneCommande=this.getLigneCommande(id);

		this.ligneCommandeRepository.delete(ligneCommande);
	}

	@Override
	public LigneCommandeVo addLigneCommnde(LigneCommandeVo ligneCommandeVo) {
		if(ligneCommandeVo.getQuantity()<=0)
			throw new RuntimeException("La quantité doit etre positive");
	LigneCommande l=null;
	AppUser user=this.usersService.get(ligneCommandeVo.getUser().getUsername());
	Product product=this.productsService.get(ligneCommandeVo.getProduct().getId());
	List<LigneCommande> cart=(this.ligneCommandeRepository.getCard(user.getUsername()));
	for (LigneCommande ligne : cart) {
		if(ligne.getProduct().equals(ProductConverter.toBo(ligneCommandeVo.getProduct()))) {
			if((ligne.getQuantity()+ligneCommandeVo.getQuantity())>product.getQuantity()) {
				throw new RuntimeException("vous avez deja à votre pannier une quantité de "+
							ligne.getQuantity()+"vous ne pouvez pas ajouter une quantité superieure à "+(product.getQuantity()-ligne.getQuantity())+
							" vu la quantité restante du produit");
			}
			ligne.setQuantity(ligne.getQuantity()+ligneCommandeVo.getQuantity());
			this.ligneCommandeRepository.save(ligne);
			l=ligne;
			break;
		}
	}
	if(l==null) {
		if((ligneCommandeVo.getQuantity())>product.getQuantity()) {
			throw new RuntimeException("vous ne pouvez pas ajouter à votre pannier une quantité superieure à la quantité restante du produit");
		}
	LigneCommande ligneCommande = LigneCommandeConverter.toBo(ligneCommandeVo);
	ligneCommande=this.ligneCommandeRepository.save(ligneCommande);
	//System.out.println(ligneCommande);
	//user.getCart().add(ligneCommande);
	//this.appUserRepository.save(user);
	l= ligneCommande;}
	
	return LigneCommandeConverter.toVo(l);
	}

	@Override
	public LigneCommandeVo editLigneCommande(Long id, LigneCommandeVo ligneCommandeVo) {
		LigneCommande ligneCommande=this.getLigneCommande(id);
		ligneCommande.setQuantity(ligneCommandeVo.getQuantity());
		return LigneCommandeConverter.toVo(this.ligneCommandeRepository.save(ligneCommande));
	}
	
	@Override
	public LigneCommande getLigneCommande(Long id) {
		Optional<LigneCommande> ligneCommandeOpt = this.ligneCommandeRepository.findById(id);
		if(!ligneCommandeOpt.isPresent())
			throw new RuntimeException("Ligne commande Introuvable");
		
		return ligneCommandeOpt.get();
	}

	@Override
	public List<LigneCommandeVo> getCart(String username) {
	return LigneCommandeConverter.toListVo(this.ligneCommandeRepository.getCard(username));
	}
	
	@Override
	public List<LigneCommande> getLignesByUsername(String username) {
	return this.ligneCommandeRepository.getCard(username);
	}

	@Override
	public LigneCommande save(LigneCommande ligne) {
		return this.ligneCommandeRepository.save(ligne);
	}

	@Override
	public List<LigneCommande> getLignesByProduct(Product product) {
		return this.ligneCommandeRepository.getLignesByProduct(product.getId());
	}
	
	
}
