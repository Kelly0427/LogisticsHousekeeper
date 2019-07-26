package com.jy.web.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jy.domain.msg.Message;
import com.jy.service.message.MessageService;
@RestController
public class MessageController {
	@Autowired
	MessageService messageService;
	//添加公告
	@PostMapping("/addMsg")
	public Message addMsg(Message msg) {
		return messageService.addMsg(msg);
	}
	//显示公告
	@GetMapping("/searchMsg")
	public Message searchMsg() {
		return messageService.searchMsg();
	}
}
