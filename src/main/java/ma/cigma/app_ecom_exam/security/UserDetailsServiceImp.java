package ma.cigma.app_ecom_exam.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.services.UsersService;
@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {
@Autowired
private UsersService usersService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser= this.usersService.get(username);
		if (appUser== null) throw new UsernameNotFoundException(username);
		if(appUser.getState().equals("BLOCKED"))  throw new RuntimeException("Compte bloqué");
		Collection<GrantedAuthority> authorities=new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(appUser.getAppRole().getRoleName()));
	    ExtendedUser user=new ExtendedUser(appUser.getUsername(),
	    		appUser.getPassword(),
	    		appUser.getFirstName(),
	    		appUser.getLastName(),
	    		appUser.getAdresse(),
	    		appUser.getCity(),
	    		appUser.getState(),
	    		authorities);
		return user;
	}



}
