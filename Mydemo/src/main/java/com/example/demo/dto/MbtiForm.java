package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MbtiForm {

	@Getter@Setter private int mbtiNumber;
	@Getter@Setter private String mbtiComment;
	@Getter@Setter private String mbtiType;
	

}
