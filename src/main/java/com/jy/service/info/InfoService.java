package com.jy.service.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jy.domain.info.Info;

public interface InfoService {
	//添加报修信息
	public Info addInfo(Info info);
	//查询报修信息列表
	public Page<Info> searchInfo(Pageable pageable,String operatorName,String phone);
	//编辑状态
	public Info update(Long id, String status);
	//新提交的保修单
	public Page<Info> loadInfoList(Pageable pageable,String status);
}
