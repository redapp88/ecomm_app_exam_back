package ma.cigma.app_ecom_exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.cigma.app_ecom_exam.entities.LigneCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
	@Query("select l from LigneCommande l where l.user.username like :username")
	public List<LigneCommande> getCard(
			@Param(value = "username")String username);
	@Query("select l from LigneCommande l where l.product.id like :id")
	public List<LigneCommande> getLignesByProduct(
			@Param(value = "id")String id);

}
