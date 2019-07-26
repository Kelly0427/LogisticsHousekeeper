package com.jy.service.message;

import java.util.List;

import com.jy.domain.msg.Message;

public interface MessageService {
	//添加公告
	public  Message addMsg(Message message);
	//显示公告
	public Message searchMsg();
}
