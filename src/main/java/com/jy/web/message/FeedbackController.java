package com.jy.web.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.domain.msg.Feedback;
import com.jy.service.message.FeedbackService;

@RestController
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;
	//添加评价
	@PostMapping("/addFeedback")
	public Feedback addFeedback(Feedback feedback) {
		return feedbackService.addFeedback(feedback);
	}
}
