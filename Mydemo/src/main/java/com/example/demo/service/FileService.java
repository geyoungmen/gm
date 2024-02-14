package com.example.demo.service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.FileForm;
import com.example.demo.mapper.FileMapper;

@Service
public class FileService {
	@Autowired
	public FileMapper fileMapper;
	
	//파일업로드 디렉토리 경로설정
	private static final String UPLOAD_DIRECTORY = "C:\\MyProgram\\image\\";
	
	public int insertFile(FileForm fileForm, MultipartFile file) throws Exception {
		//파일업로드
				if (!file.isEmpty()) {
					//String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
					//다중처리를 위한 UUID 사용
					String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

					//파일 저장
					byte[] bytes = file.getBytes();
					Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
					Files.write(path, bytes);

					fileForm.setFileName(fileName);
					fileForm.setFileUrl(UPLOAD_DIRECTORY + fileName);
				}
				
		return fileMapper.insertFile(fileForm);
	}
	
	/* public int insertBoard(BoardForm board, List<MultipartFile> files) throws Exception {
    // 파일 업로드
    List<String> fileNames = new ArrayList<>();

    if (files != null && !files.isEmpty()) {
        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 파일 저장
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIRECTORY + fileName);
            Files.write(path, bytes);

            fileNames.add(fileName);
            System.out.println("fileName: " + fileName);
            System.out.println("fileNames: " + fileNames);
            
            board.setFileName(fileName);
			board.setFileUrl(UPLOAD_DIRECTORY + fileName);
        }
    }

    return mapper.insertBoard(board);
}
*/	
	public List<Map<String, Object>> fileList(FileForm fileForm){
	//public Map<String, Object> fileList(FileForm fileForm){
		return fileMapper.fileList(fileForm);
	}
	
}
