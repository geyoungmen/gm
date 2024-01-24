package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.MbtiForm;
import com.example.demo.service.MbtiService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Controller
public class MbtiController {
	@Autowired
	MbtiService mbtiservice;
	
	@RequestMapping(value = "/mbti", method=RequestMethod.GET)
	public String Mbti(HttpServletRequest request, Model mv) {
		
		List<MbtiForm> commentList = mbtiservice.getMbtiComment();
		//System.out.println(commentList);
		
		mv.addAttribute("commentList", commentList);
		
		return "content/mbti";
	}
	
	@RequestMapping(value = "mbtiSubmit", method=RequestMethod.POST)
	@ResponseBody
	public String MbtiSubmit(HttpServletResponse response, HttpServletRequest request,@RequestParam Map<String, String> formData) {
		int EI = 0;
		int NS = 0;
		int FT = 0;
		int JP = 0;
		
		for (int i = 0; i < 5; i++) {
		    String paramName = "list_" + i;
		    String paramValue = request.getParameter(paramName);
		    
		    if (paramValue != null && !paramValue.isEmpty()) {
		        EI += Integer.parseInt(paramValue);
		    }
		}
		
		for (int i = 5; i < 10; i++) {
		    String paramName = "list_" + i;
		    String paramValue = request.getParameter(paramName);
		    
		    if (paramValue != null && !paramValue.isEmpty()) {
		        NS += Integer.parseInt(paramValue);
		    }
		}
		
		for (int i = 10; i < 15; i++) {
			String paramName = "list_" + i;
			String paramValue = request.getParameter(paramName);
			
			if (paramValue != null && !paramValue.isEmpty()) {
				FT += Integer.parseInt(paramValue);
			}
		}
		
		for (int i = 15; i < 20; i++) {
			String paramName = "list_" + i;
			String paramValue = request.getParameter(paramName);
			
			if (paramValue != null && !paramValue.isEmpty()) {
				JP += Integer.parseInt(paramValue);
			}
		}
		
		// 양수면 E, 음수면 I
		String resultEI = (EI > 0) ? "E" : "I";
		String resultNS = (NS > 0) ? "N" : "S";
		String resultFT = (FT > 0) ? "F" : "T";
		String resultJP = (JP > 0) ? "J" : "P";
		System.out.println("formData:" + formData);
		System.out.println("resultEI: " + resultEI);
		System.out.println("resultNS: " + resultNS);
		System.out.println("resultFT: " + resultFT);
		System.out.println("resultJP: " + resultJP);
		
		String resultMbti = resultEI + resultNS + resultFT + resultJP;
		
		System.out.println("resultMbti : " + resultMbti);
		return resultMbti;
		//return "redirect:mbti";
	}
}
