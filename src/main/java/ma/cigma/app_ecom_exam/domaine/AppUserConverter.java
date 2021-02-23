package ma.cigma.app_ecom_exam.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.app_ecom_exam.entities.AppUser;

public class AppUserConverter {
	public static AppUserVo toVo(AppUser bo) {
		 if (bo == null || bo.getUsername() ==null)
		 return null;
		 AppUserVo vo = new AppUserVo();
		 vo.setUsername(bo.getUsername());
		 vo.setPassword(bo.getPassword());;
		 vo.setFirstName(bo.getFirstName());
		 vo.setLastName(bo.getLastName());
		 vo.setAdresse(bo.getAdresse());
		 vo.setMail(bo.getMail());
		 vo.setCity(bo.getCity());
		 vo.setPhone(bo.getPhone());
		 vo.setSubsDate(bo.getSubsDate());
		 vo.setState(bo.getState());
		 return vo;
		 } 
	
	public static AppUser toBo(AppUserVo vo) {
		
		 AppUser bo = new AppUser();
		 bo.setUsername(vo.getUsername());
		 bo.setPassword(vo.getPassword());;
		 bo.setFirstName(vo.getFirstName());
		 bo.setLastName(vo.getLastName());
		 bo.setAdresse(vo.getAdresse());
		 bo.setMail(vo.getMail());
		 bo.setCity(vo.getCity());
		 bo.setPhone(vo.getPhone());
		 bo.setSubsDate(vo.getSubsDate());
		 bo.setState(vo.getState());
		 return bo;
		 } 
	
	public static List<AppUserVo> toListVo(List<AppUser> listBo) {
		 List<AppUserVo> listVo = new ArrayList<>();
		 for (AppUser user : listBo) {
		 listVo.add(toVo(user));
		 }
		 return listVo;
		 } 
}
