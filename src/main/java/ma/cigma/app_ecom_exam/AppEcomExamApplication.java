package ma.cigma.app_ecom_exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ma.cigma.app_ecom_exam.entities.AppRole;
import ma.cigma.app_ecom_exam.dao.AppRoleRepository;

@SpringBootApplication
public class AppEcomExamApplication implements CommandLineRunner {
	@Autowired
	AppRoleRepository AppRoleRepository;
	@Bean
	public BCryptPasswordEncoder generateBcrypte() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppEcomExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * AppRole role1 = new AppRole("USER"); AppRole role2 = new AppRole("MANAGER");
		 * this.AppRoleRepository.save(role1); this.AppRoleRepository.save(role2);
		 */


	}

}
