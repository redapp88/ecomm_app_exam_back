package ma.cigma.app_ecom_exam.services;

import java.util.List;

import ma.cigma.app_ecom_exam.domaine.AppUserVo;
import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.entities.LigneCommande;


public interface UsersService {

	public List<AppUserVo> getUsers(String keyword,String state);
	public AppUserVo addUser(AppUserVo appUserVo);
	public AppUserVo editUser(String username,AppUserVo appUserVo);
	public AppUserVo findByUsername(String username);
	public void deleteUser(String username);
	public AppUserVo editPassword(String username, AppUserVo appUserVo);
	AppUser get(String username);
	public AppUser save(AppUser user);
	public AppUserVo getUser(String username);
}
