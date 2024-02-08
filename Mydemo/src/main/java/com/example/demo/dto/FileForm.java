package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FileForm {
	
	@Getter@Setter private int fileId;
	@Getter@Setter private String fileOriName;
	//@Getter @Setter private List<String> fileOriName;
	//로컬에 저장되는 이미지 url
	@Getter@Setter private String fileUrl;
	//db에 저장될 이미지 이름
	@Getter@Setter private String fileName;

}
