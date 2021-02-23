package ma.cigma.app_ecom_exam.domaine;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserVo {
	private String username;
	private String oldPassword;
	private String password;
	private String firstName;
	private String lastName;
	private String adresse;
	private String mail;
	private String city;
	private String phone;
	private Date subsDate;
	private String state;
}
