package com.jy.domain.msg;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>,FeedbackRepositoryCustom{

}
