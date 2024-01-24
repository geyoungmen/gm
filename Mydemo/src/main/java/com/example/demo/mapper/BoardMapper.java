package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BoardForm;

@Mapper
public interface BoardMapper {
	
	int insertBoard(BoardForm board);
	
	List<BoardForm> getBoard();
	
}
