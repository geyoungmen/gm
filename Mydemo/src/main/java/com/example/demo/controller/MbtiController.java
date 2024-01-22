package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MbtiForm;
import com.example.demo.service.MbtiService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class MbtiController {
	@Autowired
	MbtiService mbtiservice;
	
	@RequestMapping(value = "/mbti", method=RequestMethod.GET)
	public String Mbti(HttpServletRequest request, Model mv) {
		
		List<MbtiForm> commentList = mbtiservice.getMbtiComment();
		System.out.println(commentList);
		
		mv.addAttribute("kk", commentList);
		
		return "content/mbti";
	}
}
