package com.example.demo.service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BoardForm;
import com.example.demo.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	public BoardMapper mapper;
	
	//파일업로드 디렉토리 경로설정
	private static final String UPLOAD_DIRECTORY = "C:\\MyProgram\\image\\";
	//private static final String IMAGE_DIRECTORY = "C:\\MyProgram\\image\\";
	
	public int insertBoard(BoardForm board, MultipartFile file) throws Exception {
		//파일업로드
				if (!file.isEmpty()) {
					String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

					//파일 저장
					byte[] bytes = file.getBytes();
					Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
					Files.write(path, bytes);

					board.setFileName(fileName);
					board.setFileUrl(UPLOAD_DIRECTORY + fileName);
				}

		return mapper.insertBoard(board);
	}

	public int updateBoard(BoardForm board) {
		return mapper.updateBoard(board);
	}

	public int deleteBoard(BoardForm board) {
		return mapper.deleteBoard(board);
	}

	public List<BoardForm> getBoard(){
		return mapper.getBoard();
	}

	public Map<String, Object> detailBoard(BoardForm board){
		return mapper.detailBoard(board);
	}
	/*
	public Resource loadImageAsResource(String filename) throws MalformedURLException {
        Optional<BoardForm> imageEntityOptional = mapper.findByFilename(filename);
        if (imageEntityOptional.isPresent()) {
            String filepath = imageEntityOptional.get().getFileUrl();
            Resource resource = new UrlResource("file:" + filepath);
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } else {
            throw new RuntimeException("Image not found!");
        }
    }
	*/
	
}
