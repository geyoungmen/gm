package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BoardForm;
import com.example.demo.dto.FileForm;

@Mapper
public interface BoardMapper{

	int insertBoard(BoardForm board);

	int updateBoard(BoardForm board);

	int deleteBoard(BoardForm board);

	List<BoardForm> getBoard();

	Map<String, Object> detailBoard(BoardForm board);
	
	//Optional<BoardForm> findByFilename(String filename);
}
