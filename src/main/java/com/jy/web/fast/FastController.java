package com.jy.web.fast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jy.tools.file.FileUtil;

@RestController
public class FastController {
	@Autowired
	FastFileStorageClient fastFileStorageClient;
	@Value("${fileupload.FastDFS.storage}")
	String storagePath;

	@PostMapping("/uploadFile")
	public StorePath uploadFile(MultipartFile upFile) {
		StorePath storePath = null;
		try {
			storePath = fastFileStorageClient.uploadFile(upFile.getInputStream(), upFile.getSize(),
					upFile.getOriginalFilename().split("\\.")[1], null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return storePath;
	}
	@PostMapping("/uploadFile2")
	public StorePath uploadFile2(String upFile) {
		MultipartFile file = FileUtil.getMulFileByPath(upFile);
		StorePath storePath = null;
		try {
			storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(),
					file.getOriginalFilename().split("\\.")[1], null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return storePath;
	}

	@DeleteMapping("/deleteFile")
	public boolean deleteFile(String filePath) {
		fastFileStorageClient.deleteFile(filePath);
		return true;

	}

	@GetMapping("/getStoragePath")
	public Map<String, String> getStoragePath() {
		Map<String, String> path = new HashMap<String, String>();
		path.put("path", storagePath);
		return path;
	}

}