package com.example.demo.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardForm {

	@Getter@Setter private int brdNo;
	@Getter@Setter private String brdSub;
	@Getter@Setter private String brdContent;
	@Getter@Setter private String brdDate;
//	@Getter@Setter private String fileOriName;
	//@Getter @Setter private List<String> fileOriName;
	//로컬에 저장되는 이미지 url
//	@Getter@Setter private String fileUrl;
	//db에 저장될 이미지 이름
//	@Getter@Setter private String fileName;
}
