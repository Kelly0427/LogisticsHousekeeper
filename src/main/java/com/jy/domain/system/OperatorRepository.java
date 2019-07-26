package com.jy.domain.system;




import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long>, OperatorRepositoryCustom {

	Operator findByName(String name);
}
