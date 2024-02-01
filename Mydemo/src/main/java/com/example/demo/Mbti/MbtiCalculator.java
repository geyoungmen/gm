package com.example.demo.Mbti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class MbtiCalculator {
	
	public static String updateScores(Map<String, String> formData) {
		System.out.println("formData : " + formData);
		List<String> list = new ArrayList<>();
		List<String> mbtiList = Arrays.asList("EI", "NS", "FT", "JP");
		
		for (int i = 0; i < 20; i++) {
            for (String type : mbtiList) {
            	list.add(type + i);
                
            }
        }
		
		for (String type : list) {
        }
		System.out.println("list : " + list);
		System.out.println("formData : " + formData.get("EI0"));
		System.out.println("list : " + list.get(0));
		for (int i=0; i<20;i++) {
			formData.get(list.get(i));
			System.out.println("formㅁㅇㅁㄴㅇData : " + formData.get(list.get(i)));
			}
		
		List<String> mbtiValues = new ArrayList<>();

        // 특정 패턴의 키들을 찾아서 값을 추출
        for (int i = 0; i < 20; i++) {
            for (String mbti : Arrays.asList("EI", "NS", "FT", "JP")) {
                String key = mbti + i;
                String value = formData.get(key);
                if (value != null) {
                    mbtiValues.add(value);
                }
            }
        }
        
		
		/*for(formData : list ) {
			
		}*/
		
		/*for (int i = 0; i < 20; i++) {
	        String paramNameEI = "EI" + i;
	        String paramNameIE = "IE" + i;

	        String paramValueEI = formData.get(paramNameEI);
	        if (paramValueEI != null) {
	            int valueEI = Integer.parseInt(paramValueEI);
	            switch (valueEI) {
	                case 7:
	                    E_EI += 3;
	                    break;
	                case 6:
	                    E_EI += 2;
	                    break;
	                case 5:
	                    E_EI += 1;
	                    break;
	                case 4:
	                	E_EI += 0;
	                    break;
	                case 3:
	                    I_EI += 1;
	                    break;
	                case 2:
	                    I_EI += 2;
	                    break;
	                case 1:
	                    I_EI += 3;
	                    break;
	                default:
	                    break;
	            }
	        }
	        String paramValueIE = formData.get(paramNameIE);
	        if (paramValueIE != null) {
	            int valueIE = Integer.parseInt(paramValueIE);
	            switch (valueIE) {
	                case 7:
	                    I_IE += 3;
	                    break;
	                case 6:
	                    I_IE += 2;
	                    break;
	                case 5:
	                    I_IE += 1;
	                    break;
	                case 4:
	                	I_IE += 0;
	                    break;
	                case 3:
	                    E_IE += 1;
	                    break;
	                case 2:
	                    E_IE += 2;
	                    break;
	                case 1:
	                    E_IE += 3;
	                    break;
	                default:
	                    break;
	            }
	        }
	    }*/
		
		return  formData.toString();
	}
	
}