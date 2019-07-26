package com.jy.service.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jy.domain.msg.Message;
import com.jy.domain.msg.MessageRepository;
import com.jy.service.system.OperatorService;
@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	OperatorService operatorService;
	//添加发布信息
	@Override
	public Message addMsg(Message message) {
		String publisher=operatorService.operatorRole();
		message.setPublisher(publisher);
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		message.setRecordTime(df.format(new Date()));
		return messageRepository.save(message);
	}
	//显示公告
	@Override
	public Message searchMsg() {
		List<Message> list = messageRepository.searchMsg();
		Message msg = new Message();
		if (list.size() > 0) {
			msg = list.get(0);
		}
		return msg;
	}

}
