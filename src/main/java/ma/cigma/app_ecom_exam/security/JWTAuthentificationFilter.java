package ma.cigma.app_ecom_exam.security;																																																																																																																																																																																																																																																																																	
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ma.cigma.app_ecom_exam.entities.AppUser;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
private AuthenticationManager authenticationManager;
	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

	AppUser appUser=null;
	
	try {
		appUser=new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	} 
	return this.authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
	
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
		Authentication authResult) throws IOException, ServletException {
		ExtendedUser springUser=(ExtendedUser) authResult.getPrincipal();
		
		String jwtToken=Jwts.builder()
				.setSubject(springUser.getUsername())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.claim("experition", SecurityConstants.EXPIRATION_TIME)
				.claim("firstName", springUser.getFirstName())
				.claim("lastName", springUser.getLastName())
				.claim("adress", springUser.getAdress())
				.claim("city", springUser.getCity())
				.claim("state", springUser.getState())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING,
				SecurityConstants.TOKEN_PREFIX+jwtToken);
				
				
		
	}

}
