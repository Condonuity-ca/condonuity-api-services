package tech.torbay.fileservice.Utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RegisterFiles {
	
	private List<MultipartFile> multipartFiles;

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

}
