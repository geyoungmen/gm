package com.example.demo.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BoardForm;

@Mapper
public interface BoardMapper{

	int insertBoard(BoardForm board);

	int updateBoard(BoardForm board);

	int deleteBoard(BoardForm board);

	List<BoardForm> getBoard();

	Map<String, Object> detailBoard(BoardForm board);
	
	//Optional<BoardForm> findByFilename(String filename);
}
