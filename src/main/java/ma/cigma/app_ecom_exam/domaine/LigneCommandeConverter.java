package ma.cigma.app_ecom_exam.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.app_ecom_exam.entities.Commande;
import ma.cigma.app_ecom_exam.entities.LigneCommande;

public class LigneCommandeConverter {
	public static LigneCommandeVo toVo(LigneCommande bo) {
		 if (bo == null || bo.getId() ==null)
		 return null;
		 LigneCommandeVo vo = new LigneCommandeVo();
		 vo.setId(bo.getId());
		 vo.setQuantity(bo.getQuantity());
		 vo.setUser(AppUserConverter.toVo(bo.getUser()));
		 vo.setProduct(ProductConverter.toVo(bo.getProduct()));
		 return vo;
		 } 
	
	public static LigneCommande toBo(LigneCommandeVo vo) {
		
		 LigneCommande bo = new LigneCommande();
		 bo.setId(vo.getId());
		 bo.setQuantity(vo.getQuantity());
		 bo.setUser(AppUserConverter.toBo(vo.getUser()));;
		 bo.setProduct(ProductConverter.toBo(vo.getProduct()));
		 return bo;
		 } 
	
	public static List<LigneCommandeVo> toListVo(List<LigneCommande> listBo) {
		 List<LigneCommandeVo> listVo = new ArrayList<>();
		 for (LigneCommande ligne : listBo) {
		 listVo.add(toVo(ligne));
		 }
		 return listVo;
		 }

	public static List<LigneCommande> toListBo(List<LigneCommandeVo> listVo) {
		 List<LigneCommande> listBo = new ArrayList<>();
		 for (LigneCommandeVo ligne : listVo) {
			 listBo.add(toBo(ligne));
		 }
		 return listBo;
		 }
	} 

