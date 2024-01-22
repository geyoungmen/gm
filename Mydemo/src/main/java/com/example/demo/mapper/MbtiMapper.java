package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MbtiForm;

@Mapper
public interface MbtiMapper {
	List<MbtiForm> getMbtiComment();
}
