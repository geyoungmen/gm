package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	//@RequestMapping(value = "/writeBoard", method=RequestMethod.POST)
	@PostMapping("/writeBoard")
	//@ResponseBody
	public String addWriteBoard(@RequestParam Map<String, String> Params,
	//public String addWriteBoard(@RequestParam Map<String, String> Params,
			@RequestParam("file") MultipartFile imgfile
							) throws Exception {

		//System.out.println("brdSub : " + brdSub);
		//System.out.println("brdContent : " + brdContent);

		BoardForm boardForm = new BoardForm();
		boardForm.setBrdSub(Params.get("brdSub"));
		boardForm.setBrdContent(Params.get("brdContent"));
		
		boardForm.setFileOriName(imgfile.getOriginalFilename());

		/* 	@RequestParam Map<String, String> Params,
		  	@RequestPart("brdSub") String brdSub,
			@RequestPart("brdContent") String brdContent,
		boardForm.setBrdSub(Params.get("brdSub"));
		boardForm.setBrdContent(Params.get("brdContent"));
		boardForm.setBrdSub(brdSub);
		boardForm.setBrdContent(brdContent);*/

		int k = boardservice.insertBoard(boardForm, imgfile);
		//boardservice.insertBoard(boardForm, imgfile);
		
		
			//return "content/board";
			//return k;
			return "redirect:/board";
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
	  
	  /*
	  //이미지 불러오기
	  @GetMapping("/image/{filename}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
	        try {
	            Resource file = boardservice.loadImageAsResource(filename);
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
	                    .body(file);
	        } catch (Exception e) {
	            // Handle exceptions (e.g., file not found)
	            return ResponseEntity.notFound().build();
	        }
	    }
	  
	  
	  	@GetMapping("display")
		@ResponseBody
		public ResponseEntity<byte[]> getThumbnaileFile(@RequestParam("fileName") String fileName) {
			File file = new File(fileName);
			ResponseEntity<byte[]> res = null;
			HttpHeaders headers =new HttpHeaders();
			try {
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				res = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers ,HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return res;
		}*/
	  
	  @GetMapping("/images/{fileOriName}")
		public ResponseEntity<Resource> downloadExecute(@PathVariable("fileOriName") String fileOriName) throws IOException {
		  System.out.println("fileOriName : " + fileOriName);
		  
		  return null;
	  }
}
