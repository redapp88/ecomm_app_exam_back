package ma.cigma.app_ecom_exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.cigma.app_ecom_exam.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	@Query("select c from Commande c where c.user.username like :username and c.state like :state order by c.dateCommande desc")
	List<Commande> getByUsernameAndState(@Param(value = "username") String username,
			@Param(value = "state") String state);

}
