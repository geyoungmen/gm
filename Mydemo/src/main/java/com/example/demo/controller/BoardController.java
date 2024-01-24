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

import com.example.demo.dto.BoardForm;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class BoardController {
	@Autowired
	BoardService boardservice;
	
	@RequestMapping(value = "/board", method=RequestMethod.GET)
	public String insertBoard(HttpServletRequest request, Model mv) {
		
		List<BoardForm> boardList = boardservice.getBoard();
		
		mv.addAttribute("boardList", boardList);
		
		return "content/board";
	}
	
	@RequestMapping(value = "/writeBoard", method=RequestMethod.GET)
	public String setWriteBoard() {
		
		return "content/writeBoard";
	}
	
	@RequestMapping(value = "/writeBoard", method=RequestMethod.POST)
	@ResponseBody
	public String addWriteBoard(@RequestParam("brdSub") String brdSub, @RequestParam("brdContent") String brdContent){
		BoardForm boardForm = new BoardForm();
		boardForm.setBrdSub(brdSub);
		boardForm.setBrdContent(brdContent);
		
		boardservice.insertBoard(boardForm);
		
		return "content/board";
	}
}
