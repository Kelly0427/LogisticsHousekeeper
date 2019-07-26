package com.jy.domain.info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InfoRepositoryCustom {
	//个人记录
	public Page<Info> searchInfo(Pageable pageable,String operatorName,String phone);
    //新提交的保修单
	public Page<Info> loadInfoList(Pageable pageable,String status);
}
