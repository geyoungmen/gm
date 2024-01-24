package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardForm;
import com.example.demo.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	public BoardMapper mapper;
	
	public int insertBoard(BoardForm board) {
		return mapper.insertBoard(board);
	}
	
	public List<BoardForm> getBoard(){
		return mapper.getBoard();
	}
	
}
