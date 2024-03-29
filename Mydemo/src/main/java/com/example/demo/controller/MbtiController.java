package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Mbti.MbtiCalculator;
import com.example.demo.dto.MbtiForm;
import com.example.demo.service.MbtiService;
@Controller
public class MbtiController {
	@Autowired
	MbtiService mbtiservice;

	@RequestMapping(value = "/mbti", method=RequestMethod.GET)
	public String Mbti(Model mv) {

		List<MbtiForm> commentList = mbtiservice.getMbtiComment();

		mv.addAttribute("commentList", commentList);

		return "content/mbti";
	}

	/*@RequestMapping(value = "mbtiSubmit", method=RequestMethod.POST)
	@ResponseBody
	public String MbtiSubmit(HttpServletResponse response, HttpServletRequest request,@RequestParam Map<String, String> formData) {
		int EI = 0;
		int NS = 0;
		int FT = 0;
		int JP = 0;

		for (int i = 0; i < 5; i++) {
		    String paramName = "list_" + i;
		    String paramValue = request.getParameter(paramName);

		    if (paramValue != null && !paramValue.isEmpty()) {
		        EI += Integer.parseInt(paramValue);
		    }
		}

		for (int i = 5; i < 10; i++) {
		    String paramName = "list_" + i;
		    String paramValue = request.getParameter(paramName);

		    if (paramValue != null && !paramValue.isEmpty()) {
		        NS += Integer.parseInt(paramValue);
		    }
		}

		for (int i = 10; i < 15; i++) {
			String paramName = "list_" + i;
			String paramValue = request.getParameter(paramName);

			if (paramValue != null && !paramValue.isEmpty()) {
				FT += Integer.parseInt(paramValue);
			}
		}

		for (int i = 15; i < 20; i++) {
			String paramName = "list_" + i;
			String paramValue = request.getParameter(paramName);

			if (paramValue != null && !paramValue.isEmpty()) {
				JP += Integer.parseInt(paramValue);
			}
		}

		// 양수면 E, 음수면 I
		String resultEI = (EI >= 0) ? "E" : "I";
		String resultNS = (NS >= 0) ? "N" : "S";
		String resultFT = (FT >= 0) ? "F" : "T";
		String resultJP = (JP >= 0) ? "J" : "P";
		System.out.println("formData:" + formData);
		System.out.println("formData:" + formData.get("EI0"));
		//System.out.println("formData:" + formData.get("type"));
		System.out.println("resultEI: " + resultEI);
		System.out.println("resultNS: " + resultNS);
		System.out.println("resultFT: " + resultFT);
		System.out.println("resultJP: " + resultJP);

		String resultMbti = resultEI + resultNS + resultFT + resultJP;

		System.out.println("resultMbti : " + resultMbti);
		return resultMbti;
		//return "redirect:mbti";
	}*/
	/*
	@RequestMapping(value = "mbtiSubmit", method=RequestMethod.POST)
	@ResponseBody
	public String MbtiSubmit(@RequestParam Map<String, String> formData) {
		//EI
		int E_EI = 0;
	    int I_EI = 0;
	    int E_IE = 0;
	    int I_IE = 0;
	    //NS
	    int N_NS = 0;
	    int S_NS = 0;
	    int N_SN = 0;
	    int S_SN = 0;
	    //FT
	    int F_FT = 0;
	    int T_FT = 0;
	    int F_TF = 0;
	    int T_TF = 0;
	    //JP
	    int J_JP = 0;
	    int P_JP = 0;
	    int J_PJ = 0;
	    int P_PJ = 0;

	    //EI 비교
	    for (int i = 0; i < 20; i++) {
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
	    }

	    //NS 비교
	    for (int i = 0; i < 20; i++) {
	    	String paramNameNS = "NS" + i;
	    	String paramNameSN = "SN" + i;

	    	String paramValueNS = formData.get(paramNameNS);
	    	if (paramValueNS != null) {
	    		int valueNS = Integer.parseInt(paramValueNS);
	    		switch (valueNS) {
	    		case 7:
	    			N_NS += 3;
	    			break;
	    		case 6:
	    			N_NS += 2;
	    			break;
	    		case 5:
	    			N_NS += 1;
	    			break;
	    		case 4:
	    			N_NS += 0;
	    			break;
	    		case 3:
	    			S_NS += 1;
	    			break;
	    		case 2:
	    			S_NS += 2;
	    			break;
	    		case 1:
	    			S_NS += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    	String paramValueSN = formData.get(paramNameSN);
	    	if (paramValueSN != null) {
	    		int valueSN = Integer.parseInt(paramValueSN);
	    		switch (valueSN) {
	    		case 7:
	    			S_SN += 3;
	    			break;
	    		case 6:
	    			S_SN += 2;
	    			break;
	    		case 5:
	    			S_SN += 1;
	    			break;
	    		case 4:
	    			S_SN += 0;
	    			break;
	    		case 3:
	    			N_SN += 1;
	    			break;
	    		case 2:
	    			N_SN += 2;
	    			break;
	    		case 1:
	    			N_SN += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    }

	    //FT 비교
	    for (int i = 0; i < 20; i++) {
	    	String paramNameFT = "FT" + i;
	    	String paramNameTF = "TF" + i;

	    	String paramValueFT = formData.get(paramNameFT);
	    	if (paramValueFT != null) {
	    		int valueFT = Integer.parseInt(paramValueFT);
	    		switch (valueFT) {
	    		case 7:
	    			F_FT += 3;
	    			break;
	    		case 6:
	    			F_FT += 2;
	    			break;
	    		case 5:
	    			F_FT += 1;
	    			break;
	    		case 4:
	    			F_FT += 0;
	    			break;
	    		case 3:
	    			T_FT += 1;
	    			break;
	    		case 2:
	    			T_FT += 2;
	    			break;
	    		case 1:
	    			T_FT += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    	String paramValueTF = formData.get(paramNameTF);
	    	if (paramValueTF != null) {
	    		int valueTF = Integer.parseInt(paramValueTF);
	    		switch (valueTF) {
	    		case 7:
	    			T_TF += 3;
	    			break;
	    		case 6:
	    			T_TF += 2;
	    			break;
	    		case 5:
	    			T_TF += 1;
	    			break;
	    		case 4:
	    			T_TF += 0;
	    			break;
	    		case 3:
	    			F_TF += 1;
	    			break;
	    		case 2:
	    			F_TF += 2;
	    			break;
	    		case 1:
	    			F_TF += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    }

	    //JP 비교
	    for (int i = 0; i < 20; i++) {
	    	String paramNameJP = "JP" + i;
	    	String paramNamePJ = "PJ" + i;

	    	String paramValueJP = formData.get(paramNameJP);
	    	if (paramValueJP != null) {
	    		int valueJP = Integer.parseInt(paramValueJP);
	    		switch (valueJP) {
	    		case 7:
	    			J_JP += 3;
	    			break;
	    		case 6:
	    			J_JP += 2;
	    			break;
	    		case 5:
	    			J_JP += 1;
	    			break;
	    		case 4:
	    			J_JP += 0;
	    			break;
	    		case 3:
	    			P_JP += 1;
	    			break;
	    		case 2:
	    			P_JP += 2;
	    			break;
	    		case 1:
	    			P_JP += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    	String paramValuePJ = formData.get(paramNamePJ);
	    	if (paramValuePJ != null) {
	    		int valuePJ = Integer.parseInt(paramValuePJ);
	    		switch (valuePJ) {
	    		case 7:
	    			P_PJ += 3;
	    			break;
	    		case 6:
	    			P_PJ += 2;
	    			break;
	    		case 5:
	    			P_PJ += 1;
	    			break;
	    		case 4:
	    			P_PJ += 0;
	    			break;
	    		case 3:
	    			J_PJ += 1;
	    			break;
	    		case 2:
	    			J_PJ += 2;
	    			break;
	    		case 1:
	    			J_PJ += 3;
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    }


	    int totalEI = E_EI + E_IE;
	    int totalIE = I_EI + I_IE;

	    int totalNS = N_NS + N_SN;
	    int totalSN = S_NS + S_SN;

	    int totalFT = F_FT + F_TF;
	    int totalTF = T_FT + T_TF;

	    int totalJP = J_JP + J_PJ;
	    int totalPJ = P_JP + P_PJ;


	    String resultEI;
	    if (totalEI == totalIE) {
	        resultEI = "E";
	    } else {
	        resultEI = (totalEI >= totalIE) ? "E" : "I";
	        //resultEI = totalEI.compareTo(totalIE);
	    }

	    String resultNS;
	    if (totalNS == totalSN) {
	    	resultNS = "N";
	    } else {
	    	resultNS = (totalNS >= totalSN) ? "N" : "S";
	    }

	    String resultFT;
	    if (totalFT == totalTF) {
	    	resultFT = "F";
	    } else {
	    	resultFT = (totalFT >= totalTF) ? "F" : "T";
	    }

	    String resultJP;
	    if (totalJP == totalPJ) {
	    	resultJP = "J";
	    } else {
	    	resultJP = (totalJP >= totalPJ) ? "J" : "P";
	    }
	    String resultMbti = resultEI + resultNS + resultFT + resultJP;
	    return resultMbti;
	}
	*/
	@RequestMapping(value = "mbtiSubmit", method=RequestMethod.POST)
	@ResponseBody
	public String MbtiSubmit(@RequestParam Map<String, String> formData) {
		//EI
		int[] E_EI = {0};
		int[] I_EI = {0};
		int[] E_IE = {0};
		int[] I_IE = {0};
		//NS
		int[] N_NS = {0};
		int[] S_NS = {0};
		int[] N_SN = {0};
		int[] S_SN = {0};
		//FT
		int[] F_FT = {0};
		int[] T_FT = {0};
		int[] F_TF = {0};
		int[] T_TF = {0};
		//JP
		int[] J_JP = {0};
		int[] P_JP = {0};
		int[] J_PJ = {0};
		int[] P_PJ = {0};
		
		for (int i = 0; i < 20; i++) {
			String paramNameEI = "EI" + i;
			String paramNameIE = "IE" + i;
			String paramNameNS = "NS" + i;
			String paramNameSN = "SN" + i;
			String paramNameFT = "FT" + i;
			String paramNameTF = "TF" + i;
			String paramNameJP = "JP" + i;
			String paramNamePJ = "PJ" + i;
			
			mbtiValue(formData, paramNameEI, E_EI, I_EI);
			mbtiValue(formData, paramNameIE, I_IE, E_IE);
			mbtiValue(formData, paramNameNS, N_NS, S_NS);
			mbtiValue(formData, paramNameSN, S_SN, N_SN);
			mbtiValue(formData, paramNameFT, F_FT, T_FT);
			mbtiValue(formData, paramNameTF, T_TF, F_TF);
			mbtiValue(formData, paramNameJP, J_JP, P_JP);
			mbtiValue(formData, paramNamePJ, P_PJ, J_PJ);
			
		}
		int totalEI = E_EI[0] + E_IE[0];
		int totalIE = I_EI[0] + I_IE[0];
		
		int totalNS = N_NS[0] + N_SN[0];
		int totalSN = S_NS[0] + S_SN[0];
		
		int totalFT = F_FT[0] + F_TF[0];
		int totalTF = T_FT[0] + T_TF[0];
		
		int totalJP = J_JP[0] + J_PJ[0];
		int totalPJ = P_JP[0] + P_PJ[0];
		
		String resultEI = (Integer.valueOf(totalEI)).compareTo(Integer.valueOf(totalIE)) >= 0 ? "E" : "I";
		String resultNS = (Integer.valueOf(totalNS)).compareTo(Integer.valueOf(totalSN)) >= 0 ? "N" : "S";
		String resultFT = (Integer.valueOf(totalFT)).compareTo(Integer.valueOf(totalTF)) >= 0 ? "F" : "T";
		String resultJP = (Integer.valueOf(totalJP)).compareTo(Integer.valueOf(totalPJ)) >= 0 ? "J" : "P";
		
		String resultMbti = resultEI + resultNS + resultFT + resultJP;
		return resultMbti;
	}
	
	private static void mbtiValue(Map<String, String> formData, String paramName, int[] set1_value, int[] set2_value) {
		String paramValue = formData.get(paramName);
		if (paramValue != null) {
			int value = Integer.parseInt(paramValue);
			switch (value) {
			case 7:
				set1_value[0] += 3;
				break;
			case 6:
				set1_value[0] += 2;
				break;
			case 5:
				set1_value[0] += 1;
				break;
			case 4:
				set1_value[0] += 0;
				break;
			case 3:
				set2_value[0] += 1;
				break;
			case 2:
				set2_value[0] += 2;
				break;
			case 1:
				set2_value[0] += 3;
				break;
			default:
				break;
				
			}
		}
	}
	
}
