package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardForm {

	@Getter@Setter private int brdNo;
	@Getter@Setter private String brdSub;
	@Getter@Setter private String brdContent;
	@Getter@Setter private String brdDate;
	@Getter@Setter private String fileOriName;
	@Getter@Setter private String fileUrl;
	@Getter@Setter private String fileName;

}
