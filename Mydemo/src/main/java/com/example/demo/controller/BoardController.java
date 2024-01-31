package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BoardForm;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	BoardService boardservice;

	@GetMapping("/board")
	public String boardList(HttpServletRequest request, Model mv) {

		List<BoardForm> boardList = boardservice.getBoard();

		mv.addAttribute("boardList", boardList);

		return "content/board";
	}

	@GetMapping("/writeBoard")
	public String setWriteBoard() {

		return "content/writeBoard";
	}

	//ajax를 활용해서 data를 개별로 받아오기
	//@RequestMapping(value = "/writeBoard", method=RequestMethod.POST)
	@PostMapping("/writeBoard")
	@ResponseBody
	public String addWriteBoard(@RequestPart("brdSub") String brdSub,
			@RequestPart("brdContent") String brdContent,
			@RequestPart("file") MultipartFile file
							) throws Exception {

		System.out.println("file : " + file);
		//System.out.println("ParamsParamsParams : " + Params);

		BoardForm boardForm = new BoardForm();
		boardForm.setBrdSub(brdSub);
		boardForm.setBrdContent(brdContent);
		boardForm.setFileOriName(file.getOriginalFilename());

		System.out.println("filefilefile : " + file.getOriginalFilename());

		/* @RequestParam Map<String, String> Params
		boardForm.setBrdSub(Params.get("brdSub"));
		boardForm.setBrdContent(Params.get("brdContent"));*/

		boardservice.insertBoard(boardForm);

		return "content/board";
	}

	//@RequestMapping(value = "/detailBoard", method=RequestMethod.GET)
	@GetMapping("/detailBoard")
	public String detailBoard(@RequestParam Map<String, String> Params, Model mv) {
		//System.out.println("Params : " + Params);

		BoardForm boardForm = new BoardForm();
		boardForm.setBrdNo(Integer.parseInt(Params.get("No")));

		Map<String, Object> detailBoard = boardservice.detailBoard(boardForm);

		mv.addAttribute("detailboard", detailBoard);

		return "content/detailBoard";
	}

	//@RequestMapping(value = "/deleteBoard", method=RequestMethod.POST)
	@PostMapping("/deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestBody Map<String, String> data) {

	    String brdSub = data.get("brdSub");
	    String brdContent = data.get("brdContent");
	    String brdNoStr = data.get("brdNo");

	    int brdNo = Integer.parseInt(brdNoStr);

	    BoardForm boardForm = new BoardForm();
	    boardForm.setBrdSub(brdSub);
	    boardForm.setBrdContent(brdContent);
	    boardForm.setBrdNo(brdNo);

	    boardservice.deleteBoard(boardForm);

	    return "content/board";
	}


	  @GetMapping("/updateBoard")
	  public String updatesetBoard(@RequestParam Map<String, String> Params, Model mv) {
		BoardForm boardForm = new BoardForm();
		boardForm.setBrdNo(Integer.parseInt(Params.get("No")));

		Map<String, Object> detailBoard = boardservice.detailBoard(boardForm);

		mv.addAttribute("detailboard", detailBoard);

	  return "content/updateBoard";
	  }

	  @PostMapping("/updateBoard")
	  @ResponseBody
	  public String updateBoard(@RequestBody Map<String, String> data) {

	    String brdSub = data.get("brdSub");
	    String brdContent = data.get("brdContent");
	    String brdNoStr = data.get("brdNo");

	    int brdNo = Integer.parseInt(brdNoStr);

	    BoardForm boardForm = new BoardForm();
	    boardForm.setBrdSub(brdSub);
	    boardForm.setBrdContent(brdContent);
	    boardForm.setBrdNo(brdNo);

	    boardservice.updateBoard(boardForm);

	    return "content/board";
		}

	  //카카오맵 api 연동 및 팝업창 띄우기
	  @GetMapping("/kakaoMap")
	  public String kakaoMap() {

		  return "content/kakaoMap";
	  }

}
