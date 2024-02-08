package com.example.demo.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

	@PostMapping("/writeBoard")
	public String addWriteBoard(@RequestParam Map<String, String> Params,
			@RequestParam("file") MultipartFile imgfile) throws Exception { 

		BoardForm boardForm = new BoardForm();
		
		boardForm.setBrdSub(Params.get("brdSub"));
		boardForm.setBrdContent(Params.get("brdContent"));
		
		boardservice.insertBoard(boardForm);
		
		if (!imgfile.isEmpty()) {
			FileForm fileForm = new FileForm();
			fileForm.setFileOriName(imgfile.getOriginalFilename());
			fileForm.setFileId(boardForm.getBrdNo());
			
			fileservice.insertFile(fileForm, imgfile);
		}
		
			return "redirect:/board";
	} 
	/*
	@PostMapping("/writeBoard")
	public String addWriteBoard(@RequestParam Map<String, String> Params,
			@RequestParam("file") List<MultipartFile> imgfiles
			) throws Exception {
		
		BoardForm boardForm = new BoardForm();
		boardForm.setBrdSub(Params.get("brdSub"));
		boardForm.setBrdContent(Params.get("brdContent"));

		List<String> fileOriNames = new ArrayList<>();
		System.out.println("imgfiles: " + imgfiles);
	    // 이미지 파일들을 하나씩 처리
	    for (MultipartFile imgFile : imgfiles) {
	        fileOriNames.add(imgFile.getOriginalFilename());
	    }

	    boardForm.setFileOriName(fileOriNames);
	    System.out.println("fileOriNames: " + fileOriNames);
		boardservice.insertBoard(boardForm, imgfiles);
		
		return "redirect:/board";
	}
*/
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
		Map<String, Object> fileBoard = fileservice.fileList(fileForm); 
		
		mv.addAttribute("detailboard", detailBoard);
		mv.addAttribute("fileBoard", fileBoard);
		
		System.out.println("detailBoard : " + detailBoard);
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
	  
	  /*
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
		}
	  @GetMapping("/image/{fileName}")
	  public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) throws IOException {
	      // 실제 이미지 파일의 경로를 찾아서 Resource를 생성
	      Resource resource = new FileSystemResource("C:/MyProgram/image/" + fileName);

	      // Resource를 ResponseEntity로 감싸서 반환
	      return ResponseEntity.ok()
	              .contentType(MediaType.IMAGE_JPEG) // 이미지 타입에 따라 적절한 MediaType 사용
	              .body(resource);
	  }
	  */
	  /*
	  //이미지 불러오기
	  @GetMapping("/image/{fileName}")
	  public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) throws MalformedURLException {
	      System.out.println("fileName: " + fileName);
	      
	      File file = new File("C:\\MyProgram\\image\\" + fileName);
	      Path path = file.toPath();
	      Resource resource = new FileSystemResource(path.toFile());
	      //이미지타입 PNG로 고정
	      return ResponseEntity.ok()
	              .contentType(MediaType.IMAGE_PNG)
	              .body(resource);
	  }
	  */
	  @GetMapping("/image/{fileName}")
	  public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) throws MalformedURLException {
	      
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
