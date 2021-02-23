package ma.cigma.app_ecom_exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.cigma.app_ecom_exam.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
	@Query("select u from AppUser u where (u.firstName like :keyword or u.lastName like :keyword or u.username like :keyword)  and u.appRole.roleName like 'USER' and u.state like :state")
	List<AppUser> getByUserByDetails(
			@Param(value = "keyword") String keyword,
			@Param(value = "state")String state);

}
