/*
package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.HomeForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public String Home(HttpServletRequest request) {
		
		return "content/home";
	}
	//@PostMapping("calendar")
	@RequestMapping(value = "calendar", method=RequestMethod.POST)
	@ResponseBody
	//public String HomeCalendar(HomeForm form, HttpServletResponse response, ExcelStyleTop top) {
	public String HomeCalendar(HomeForm form, HttpServletResponse response) {
		int year = form.getYear();
		int month = form.getMonth();
		//Calendar time = Calendar.getInstance();
		try {
            //Workbook workbook = createExcel(form, top);
            Workbook workbook = createExcel(form);
            
            String fileName = "Calendar_" + year + "_" + month + ".xlsx";
            
            // HTTP 응답 헤더 설정
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            
            // HTTP 응답으로 엑셀 파일 출력
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            
            workbook.close();
            outputStream.close();
            //깃 테스트
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return "redirect:/";
	}
	
	//private Workbook createExcel(HomeForm form, ExcelStyleTop top) {
	private Workbook createExcel(HomeForm form) {
		int year = form.getYear();
	    int month = form.getMonth();
	    
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("Calendar");
	    
	    Map<Integer, CellStyle> excelStyleMap = excelStyle(workbook);
        // 토, 일 font 바꾸기 토 = 초록 일 = 레드 
        CellStyle greenStyle = workbook.createCellStyle();
        greenStyle.cloneStyleFrom(excelStyleMap.get(2));
        Font greenFont = workbook.createFont();
        greenFont.setColor(IndexedColors.GREEN.getIndex());
        greenStyle.setFont(greenFont);
        
        CellStyle redStyle = workbook.createCellStyle();
        redStyle.cloneStyleFrom(excelStyleMap.get(2));
        Font redFont = workbook.createFont();
        redFont.setColor(IndexedColors.RED.getIndex());
        redStyle.setFont(redFont);
        
        // 월의 첫 날이 무슨 요일인지 계산
        int getDayOfWeek = getDayOfWeek(year, month);
        int getDate = getDate(year, month);

	    // 엑셀 내용 설정
	    Row headerRow = sheet.createRow(0);
	    headerRow.createCell(1).setCellValue(year + "년");
	    headerRow.createCell(2).setCellValue(month + "월");
	    String[] day= {"일","월","화","수","목","금","토"};
	    
	    //요일 좌측 나열
	    Row headerRowW = sheet.createRow(1);
	    for (int i = 0; i < 7; i++) {
	    	if(i == 0) {
	    		headerRowW.createCell((i)+2).setCellValue(day[i]);
	    	} else {
	    		headerRowW.createCell((i*3)+2).setCellValue(day[i]);
	    	}
	    	
	    }
	    
	    //해당 월의 첫번째 요일에 1일 시작 i , 1추가 = 년 월 칸 만들기
	    for (int i = 1; i < getDayOfWeek; i++) {
	        Row emptyRow = sheet.createRow(2 + i);
	        emptyRow.createCell(0).setCellValue("");
	    }
	    //첫 주 1일 시작 위치 그후 일 계산 getDate = 해당 월의 일수
	    // createRow 의 5 6 7 번 공백 셀 없애야 한다.
	    for (int i = 0; i <= getDate; i++) {
	    	Row row = sheet.createRow(getDayOfWeek + 1 + i);
	    	Row row2 = sheet.createRow(getDayOfWeek + 2 + i);
	    	Row row3 = sheet.createRow(getDayOfWeek + 3 + i);
	    	Row row4 = sheet.createRow(getDayOfWeek + 4 + i);
	        // 각 날짜마다 행을 생성하고, 해당 행의 첫 번째 셀부터 차례로 값을 채움 j = 1주
	    	// getDayOfWeek = 1 / i = 날짜 / j = 주의 몇번째 요일 
	        for (int j = (getDayOfWeek + i - 1 ) % 7; j < 7; j++) {
	        	
	        	//j >>>> 0 번은 일요일 = red , 6 번은 토요일 = green   greenStyle.setFont(greenFont); redStyle.setFont(redFont);
	        	if (i != 0 && j == 0) {
	        		Cell cell2 = row.createCell((j) * 3 + 2);
	        		cell2.setCellValue(i);
	        		cell2.setCellStyle(excelStyleMap.get(2));
	        		cell2.setCellStyle(redStyle);
	        	} else if (i != 0 && j == 6) {
	        		Cell cell2 = row.createCell((j) * 3 + 2);
	        		cell2.setCellValue(i);
	        		cell2.setCellStyle(excelStyleMap.get(2));
	        		cell2.setCellStyle(greenStyle);
	        	} else if (i != 0){
	        		Cell cell2 = row.createCell((j) * 3 + 2);
	        		cell2.setCellValue(i);
	        		cell2.setCellStyle(excelStyleMap.get(2));
	        	} else {
	        		Cell cell2 = row.createCell((j) * 3 + 2);
        			cell2.setCellValue("");
        			cell2.setCellStyle(excelStyleMap.get(2));
	        	}	// 로컬 저장된 excelform에 맞춰서 style 입히기 
	        	
	        		Cell cell = row.createCell((j) * 3);
	        		cell.setCellStyle(excelStyleMap.get(0));
	        		
	        		Cell cell1 = row.createCell((j) * 3 + 1);
	        		cell1.setCellStyle(excelStyleMap.get(1));  
	        		
	        		Cell cell3 = row2.createCell((j) * 3);
	        		cell3.setCellStyle(excelStyleMap.get(3));
	        		
	        		Cell cell4 = row2.createCell((j) * 3 + 1);
	        		cell4.setCellStyle(excelStyleMap.get(4));
	        		
	        		Cell cell5 = row2.createCell((j) * 3 + 2);
	        		cell5.setCellStyle(excelStyleMap.get(5)); 
	        		
	        		Cell cell6 = row3.createCell((j) * 3);
	        		cell6.setCellStyle(excelStyleMap.get(6));
	        		
	        		Cell cell7 = row3.createCell((j) * 3 + 1);
	        		cell7.setCellStyle(excelStyleMap.get(7));
	        		
	        		Cell cell8 = row3.createCell((j) * 3 + 2);
	        		cell8.setCellStyle(excelStyleMap.get(8));
	        		
	        		Cell cell9 = row4.createCell((j) * 3);
	        		cell9.setCellStyle(excelStyleMap.get(9));
	        		
	        		Cell cell10 = row4.createCell((j) * 3 + 1);
	        		cell10.setCellStyle(excelStyleMap.get(10));
	        		
	        		Cell Cell11 = row4.createCell((j) * 3 + 2);
	        		Cell11.setCellStyle(excelStyleMap.get(11));
	            i++; // 날짜를 하나 채웠으므로  증가시킴
	            if (i > getDate) {
	                break; 
	            }
	        }
	        i--; // for 루프에서 한 번 더 증가했으므로 감소시킴
	    }
	    return workbook;
	}
	
	private boolean isLeapYear(int year) {
	   return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}
	
	private int getDate(int year, int month) {
	    if (isLeapYear(year)) {
	        switch (month) {
	            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
	                return 31;
	            case 4: case 6: case 9: case 11:
	                return 30;
	            case 2:
	                return 29;
	        }
	    } else {
	        switch (month) {
	            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
	                return 31;
	            case 4: case 6: case 9: case 11:
	                return 30;
	            case 2:
	                return 28;
	        }
	    }
	    return 0;
	}
	private int getDayOfWeek(int year, int month) {
	    int dayOfWeek = 0;
	    int sum = 0;

	    // 1.1.1 ~ year-1.12.31
	    for (int i = 1; i < year; i++) {
	        for (int j = 1; j <= 12; j++) {
	            sum += getDate(i, j);
	        }
	    }

	    // year.1.1 ~ year.month-1.마지막(31,30,29,28)
	    for (int k = 1; k < month; k++) {
	        sum += getDate(year, k);
	    }

	    // year.month.1일
	    sum += 1;

	    // 요일
	    dayOfWeek = sum % 7;
	    
	    return dayOfWeek;
	}
	
	private static Map<Integer, CellStyle> excelStyle(Workbook wb) {
		Map<Integer, CellStyle> excelStylelist = new HashMap<>();
		String filePath = "C:\\MyProgram";
		String fileName = "Excel23.xlsx";
		
		try (FileInputStream fileInputStream = new FileInputStream(new File(filePath, fileName))) {
	        // 엑셀 파일로 Workbook instance를 생성한다.
	        Workbook workbook = new XSSFWorkbook(fileInputStream);

	        // workbook의 첫번째 sheet를 가저온다.
	        Sheet sheet = workbook.getSheetAt(0);

	        int cnt = 0;
	        // 모든 행(row)들을 조회한다.
	        for (Row row : sheet) {
	            // 각각의 행에 존재하는 모든 열(cell)을 순회한다.
	            Iterator<Cell> cellIterator = row.cellIterator();
	            
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	                CellStyle excelStyle = wb.createCellStyle();
	                excelStyle.cloneStyleFrom(cell.getCellStyle());
	                excelStylelist.put(cnt++, excelStyle);
	            }
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return excelStylelist;
	}
	
}*/