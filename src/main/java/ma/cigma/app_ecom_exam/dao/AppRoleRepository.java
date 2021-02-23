package ma.cigma.app_ecom_exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.app_ecom_exam.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

	AppRole findByRoleName(String string);

}
