package com.example.demo.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
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
import com.example.demo.dto.FileForm;
import com.example.demo.service.BoardService;
import com.example.demo.service.FileService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	BoardService boardservice;
	@Autowired
	FileService fileservice;

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
	/*
	@PostMapping("/writeBoard")
	public String addWriteBoard(@RequestParam Map<String, String> Params,
			@RequestParam("file") MultipartFile imgfile) throws Exception { 

		BoardForm boardForm = new BoardForm();
		
		
		if(!Params.get("brdSub").isEmpty() && !Params.get("brdContent").isEmpty()) {
			boardForm.setBrdSub(Params.get("brdSub"));
			boardForm.setBrdContent(Params.get("brdContent"));
			
			boardservice.insertBoard(boardForm);
		}
		
		if (!imgfile.isEmpty()) {
			System.out.println("imgfile : " +imgfile);
			FileForm fileForm = new FileForm();
			fileForm.setFileOriName(imgfile.getOriginalFilename());
			fileForm.setFileId(boardForm.getBrdNo());
			
			fileservice.insertFile(fileForm, imgfile);
		}
			return "redirect:/board";
	}
	*/
	
	@PostMapping("/writeBoard")
	public String addWriteBoard(@RequestParam Map<String, String> Params,
	        @RequestParam("file") List<MultipartFile> imgfiles) throws Exception {

	    BoardForm boardForm = new BoardForm();
	    if (!Params.get("brdSub").isEmpty() && !Params.get("brdContent").isEmpty()) {
	        boardForm.setBrdSub(Params.get("brdSub"));
	        boardForm.setBrdContent(Params.get("brdContent"));

	        boardservice.insertBoard(boardForm);
	    }

	    for (MultipartFile imgfile : imgfiles) {
	        System.out.println("imgfile : " + imgfile);
	        if (!imgfile.isEmpty()) {
	            FileForm fileForm = new FileForm();
	            fileForm.setFileOriName(imgfile.getOriginalFilename());
	            fileForm.setFileId(boardForm.getBrdNo());

	            fileservice.insertFile(fileForm, imgfile);
	        }
	    }

	    return "redirect:/board";
	}
	
	//@RequestMapping(value = "/detailBoard", method=RequestMethod.GET)
	@GetMapping("/detailBoard")
	public String detailBoard(@RequestParam Map<String, String> Params, Model mv) {
		//System.out.println("Params : " + Params);
		
		int No = Integer.parseInt(Params.get("No"));
		
		BoardForm boardForm = new BoardForm();
		boardForm.setBrdNo(No);
		
		FileForm fileForm = new FileForm();
		fileForm.setFileId(No);
		
		Map<String, Object> detailBoard = boardservice.detailBoard(boardForm);
		List<Map<String, Object>> fileBoard = fileservice.fileList(fileForm); 
		//List<Map<String, Object>> fileBoard = fileservice.fileList(fileForm);
		
		mv.addAttribute("detailboard", detailBoard);
		// 파일이 없을 시
		if(fileBoard != null) {
			mv.addAttribute("fileBoard", fileBoard);
		}
		
		//System.out.println("detailBoard : " + detailBoard);
		System.out.println("fileBoard : " + fileBoard);
		
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
	  
	  @GetMapping("/image/{fileName}")
	  public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) throws MalformedURLException {
	      System.out.println("fileName : " + fileName);
	      File file = new File("C:\\MyProgram\\image\\" + fileName);
	      Path path = file.toPath();
	      Resource resource = new FileSystemResource(path.toFile());
	      
	      // 파일 이름에서 확장자 추출
	      String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	      
	      MediaType mediaType;
	      switch (fileExtension) {
	          case "png":
	              mediaType = MediaType.IMAGE_PNG;
	              break;
	          case "jpg":
	          case "jpeg":
	              mediaType = MediaType.IMAGE_JPEG;
	              break;
	          case "gif":
	              mediaType = MediaType.IMAGE_GIF;
	              break;
	          default:
	              mediaType = MediaType.APPLICATION_OCTET_STREAM;
	              break;
	      }
	      return ResponseEntity.ok()
	              .contentType(mediaType)
	              .body(resource);
	  }
	  
}
