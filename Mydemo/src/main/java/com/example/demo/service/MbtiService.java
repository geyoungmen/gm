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
	/*
	public static String updateScores(Map<String, String> formData) {
		System.out.println("formData : " + formData);
		List<String> list = new ArrayList<>();
		List<String> mbtiList = Arrays.asList("EI", "NS", "FT", "JP");
		
		for (int i = 0; i < 20; i++) {
            for (String type : mbtiList) {
            	list.add(type + i);
                
            }
        }
		
		for (String type : list) {
        }
		System.out.println("list : " + list);
		System.out.println("formData : " + formData.get("EI0"));
		System.out.println("list : " + list.get(0));
		for (int i=0; i<20;i++) {
			formData.get(list.get(i));
			System.out.println("formㅁㅇㅁㄴㅇData : " + formData.get(list.get(i)));
			}
		

        return  formData.toString();
	}
	*/
}	
