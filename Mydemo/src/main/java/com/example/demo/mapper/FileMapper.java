package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.FileForm;

@Mapper
public interface FileMapper{

	int insertFile(FileForm fileForm);
	
	//Map<String, Object> fileList(FileForm fileForm);
	List<Map<String, Object>> fileList(FileForm fileForm);
}
