package ma.cigma.app_ecom_exam.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ma.cigma.app_ecom_exam.dao.AppRoleRepository;
import ma.cigma.app_ecom_exam.dao.AppUserRepository;
import ma.cigma.app_ecom_exam.domaine.AppUserConverter;
import ma.cigma.app_ecom_exam.domaine.AppUserVo;
import ma.cigma.app_ecom_exam.domaine.LigneCommandeVo;
import ma.cigma.app_ecom_exam.entities.AppRole;
import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
import ma.cigma.app_ecom_exam.entities.Product;

@Service
@Transactional
public class UsersServiceImp implements UsersService {
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	AppRoleRepository appRoleRepository;
	@Autowired
	MailingService mailingService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<AppUserVo> getUsers(String keyword, String state) {
		keyword = "%"+keyword+"%";
		if (state.equals("*"))
			state = "%%";
		return AppUserConverter.toListVo(this.appUserRepository.getByUserByDetails(keyword, state));
	}

	@Override
	public AppUserVo addUser(AppUserVo appUserVo) {
		AppRole role = this.appRoleRepository.findByRoleName("USER");
		if (role == null) {
			throw new RuntimeException("erreur d'inscription");
		}
		AppUser user=AppUserConverter.toBo(appUserVo);
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setSubsDate(new Date());
		user.setState("ACTIVE");
		user.setAppRole(role);
		String mailbody="<p> Bonjour "+user.getFirstName()+ " Votre compte CigmaEcomm est crée avec succès vous pouvez vous connecter en utilisant vos Identifiants de connexion</p>";
		mailbody+="<p>Nom d'utilisateur: "+user.getUsername()+"<p>";
		mailbody+="<p>Mot de passe: "+appUserVo.getPassword()+"<p>";
		try {
			this.mailingService.sendEmail(mailbody, "Creation de vote nouveau Compte CigmaEcomm", user.getMail(), "CigmaEcomm", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AppUserConverter.toVo(this.appUserRepository.save(user));

	}

	@Override
	public AppUserVo editUser(String username, AppUserVo appUserVo) {
		AppUser user=this.get(username);
		user.setAdresse(appUserVo.getAdresse());
		user.setCity(appUserVo.getCity());
		user.setFirstName(appUserVo.getFirstName());
		user.setLastName(appUserVo.getLastName());
		user.setMail(appUserVo.getMail());
		user.setPhone(appUserVo.getPhone());
		user.setState(appUserVo.getState());
		return AppUserConverter.toVo(this.appUserRepository.save(user));
	}

	@Override
	public AppUserVo findByUsername(String username) {
	Optional<AppUser> AppUserOpt = this.appUserRepository.findById(username);
	if(!AppUserOpt.isPresent())
		throw new RuntimeException("Utilisateur introuvable");
	return AppUserConverter.toVo(AppUserOpt.get());
	}

	@Override
	public void deleteUser(String username) {
		this.appUserRepository.deleteById(username);

	}

	@Override
	public AppUserVo editPassword(String username, AppUserVo appUserVo) {
		AppUser user=this.get(username);
		if (!this.bCryptPasswordEncoder.matches(appUserVo.getOldPassword(), user.getPassword())) {
			throw new RuntimeException("Mot de passe incorrecte");
		}
		user.setPassword(this.bCryptPasswordEncoder.encode(appUserVo.getPassword()));
		
		return AppUserConverter.toVo(this.appUserRepository.save(user));
	}


	@Override	
	public AppUser get(String username) {
	Optional<AppUser> AppUserOpt = this.appUserRepository.findById(username);
	if(!AppUserOpt.isPresent())
		throw new RuntimeException("Utilisateur introuvable");
	return AppUserOpt.get();
	}

	@Override
	public AppUser save(AppUser user) {
		return this.appUserRepository.save(user);
	}

	@Override
	public AppUserVo getUser(String username) {
	return AppUserConverter.toVo(this.get(username));
	}

}
