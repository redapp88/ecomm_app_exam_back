package ma.cigma.app_ecom_exam.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.app_ecom_exam.entities.Commande;

public class CommandeConverter {
	public static CommandeVo toVo(Commande bo) {
		 if (bo == null || bo.getId() ==null)
		 return null;
		 CommandeVo vo = new CommandeVo();
		 vo.setId(bo.getId());
		 vo.setUser(AppUserConverter.toVo(bo.getUser()));
		 vo.setDateCommande(bo.getDateCommande());
		 vo.setState(bo.getState());
		 vo.setPayementMethode(bo.getPayementMethode());
		 vo.setShippingAdress(bo.getShippingAdress());
		 vo.setShippingCity(bo.getShippingAdress());
		 vo.setTotal(bo.getTotal());
		 vo.setCommandeDetails(bo.getCommandeDetails());
		 return vo;
		 } 
	
	public static Commande toBo(CommandeVo vo) {
		 if (vo == null)
			 return null;
		 Commande bo = new Commande();
		 bo.setId(vo.getId());
		 bo.setUser(AppUserConverter.toBo(vo.getUser()));
		 bo.setDateCommande(vo.getDateCommande());
		 bo.setState(vo.getState());
		 bo.setPayementMethode(vo.getPayementMethode());
		 bo.setShippingAdress(vo.getShippingAdress());
		 bo.setTotal(vo.getTotal());
		 bo.setCommandeDetails(vo.getCommandeDetails());
		 return bo;
		 } 
	
	public static List<CommandeVo> toListVo(List<Commande> listBo) {
		 List<CommandeVo> listVo = new ArrayList<>();
		 for (Commande user : listBo) {
		 listVo.add(toVo(user));
		 }
		 return listVo;
		 } 
}
