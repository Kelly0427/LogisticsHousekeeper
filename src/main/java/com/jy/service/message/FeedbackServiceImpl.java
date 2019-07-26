package com.jy.service.message;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.domain.msg.Feedback;
import com.jy.domain.msg.FeedbackRepository;
import com.jy.service.system.OperatorService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Autowired
	OperatorService operatorService;
	@Autowired
	FeedbackRepository feedbackRepository;
	//添加评价
	@Override
	public Feedback addFeedback(Feedback feedback) {
		String name=operatorService.operatorName();
		feedback.setName(name);
		String realName=operatorService.operatorRealName();
		feedback.setRealName(realName);
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		feedback.setRecordTime(df.format(new Date()));
		return feedbackRepository.save(feedback);
	}

}
