package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardForm;
import com.example.demo.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	public BoardMapper mapper;

	public int insertBoard(BoardForm board) throws Exception {
	/*	//파일 저장 경로
				String filePath = System.getProperty("user.dir") + "//src//main//resources//static//files";
				//파일이름 중복 방지
				UUID uuid = UUID.randomUUID();
				//저장될 파일이름 생성 uuid + _ + 파일 기존이름
				String fileName = uuid + "_" + file.getOriginalFilename();

				File saveFile = new File(filePath, fileName);

				file.transferTo(saveFile);*/

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

}
