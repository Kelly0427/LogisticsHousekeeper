package com.jy.web.info;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jy.domain.info.Info;
import com.jy.service.info.InfoService;
import com.jy.service.system.OperatorService;

@RestController
public class InfoController {
	@Autowired
	FastFileStorageClient fastFileStorageClient;
	@Autowired
	InfoService infoService;
	@Autowired
	OperatorService operatorService;
	/*
	 * 报修接口
	 */
	@PostMapping("/addInfo")
	public Info addInfo(Info info, MultipartFile upFile) {
		if(!upFile.isEmpty()) {
			String[] fileNameSplit = upFile.getOriginalFilename().split("\\.");
			StorePath storePath=null;
			try {
				storePath=fastFileStorageClient.uploadFile(upFile.getInputStream(), upFile.getSize(),upFile.getOriginalFilename().split("\\.")[fileNameSplit.length - 1], null);
				info.setImgUrl(storePath.getFullPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		info.setStatus("已提交");
		return infoService.addInfo(info);
	}
	/*
	 * 我的报修单
	 */
	@GetMapping("/searchInfo")
	public Page<Info> searchInfo(Pageable pageable) {
		String operatorName=operatorService.operatorRealName();
		String phone=operatorService.operatorPhone();
		return infoService.searchInfo(pageable, operatorName,phone);
	}
	/*
	 * 修改状态
	 */
	@PostMapping("/editInfo")
	public Info editInfo(Long id,String status) {
		return infoService.update(id,status);
	}
	//新提交的保修单
	@GetMapping("/loadInfoList")
	public Page<Info> loadInfoList(Pageable pageable,String status){
		return infoService.loadInfoList(pageable, status);
	}

	
	
}
