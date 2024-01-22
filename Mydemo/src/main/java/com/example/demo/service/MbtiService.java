package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MbtiForm;
import com.example.demo.mapper.MbtiMapper;

@Service
public class MbtiService {
	@Autowired
	public MbtiMapper mapper;
	
	public List<MbtiForm> getMbtiComment(){
		return mapper.getMbtiComment();
	}
	
}
