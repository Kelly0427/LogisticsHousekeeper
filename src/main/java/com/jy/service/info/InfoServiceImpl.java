package com.jy.service.info;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jy.domain.info.Info;
import com.jy.domain.info.InfoRepository;

@Service
public class InfoServiceImpl implements InfoService{
	@Autowired
	InfoRepository infoRepository;
    //添加报修信息
	@Override
	public Info addInfo(Info info) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		info.setRecordTime(df.format(new Date()));
		return infoRepository.save(info);
	}
	//查询报修信息列表
	@Override
	public Page<Info> searchInfo(Pageable pageable, String operatorName,String phone) {
		return infoRepository.searchInfo(pageable,operatorName,phone);
	}
	//编辑状态
	@Override
	public Info update(Long id, String status) {
		Info info=infoRepository.findOne(id);
		info.setStatus(status);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		info.setRecordTime(df.format(new Date()));
		return infoRepository.save(info);
	}
	
	@Override
	public Page<Info> loadInfoList(Pageable pageable,String status) {
		return infoRepository.loadInfoList(pageable, status);
	}
	

	

}
