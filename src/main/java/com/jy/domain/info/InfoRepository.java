package com.jy.domain.info;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Long>,InfoRepositoryCustom {

	

}
