package com.example.demo.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardForm;
import com.example.demo.dto.FileForm;
import com.example.demo.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	public BoardMapper boardMapper;
	
	public int insertBoard(BoardForm board) throws Exception {
		return boardMapper.insertBoard(board);
	}
	public int updateBoard(BoardForm board) {
		return boardMapper.updateBoard(board);
	}

	public int deleteBoard(BoardForm board) {
		return boardMapper.deleteBoard(board);
	}

	public List<BoardForm> getBoard( ){
		return boardMapper.getBoard();
	}

	public Map<String, Object> detailBoard(BoardForm board){
		return boardMapper.detailBoard(board);
	}

}
