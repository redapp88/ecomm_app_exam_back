package ma.cigma.app_ecom_exam.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.cigma.app_ecom_exam.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	@Query("select p from Product p where p.label like :label order by p.creationDate desc")
	List<Product> getByDetails(
			@Param(value = "label")String label);

}
