package lotto_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LottoFunction {
	
	public Integer [] beta (Integer [] ans) {
		
		if(ans == null || ans.length != 7) {
			return null;
		}
		
		Integer [] temp 	= new Integer[6]
				, result 	= new Integer[6];

		try {
			Arrays.sort(ans, Collections.reverseOrder());
			
			
			for(int i = 0; i < ans.length - 1 ; i++) {
				temp[i] = ans[i] - ans[i+1];
			}
			
			Arrays.sort(temp);
			
			int sum = 0;
			for(int i = 0; i < result.length; i++) {
				sum += temp[i];
				result[i] = sum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//TODO
/*	1. 4연번x
	(2. 같은끝수3개이상x OO                      v cnt
	(3. 같은번호대4개이상x OO                    v cnt
	(4. 이전회차같은수3개이상x     OO            
	5. 2개이상의 연번x OO                      
	(6. 23기준으로 60 는 피하라 OO                 v cnt
	(7. 홀수와 짝수비율이 60는 피하라 OO               v cnt
	(8. 6개숫자합이 90~190                      v sum 
	(9. 저고차 25~42							
	*/
	public Boolean validCheck (ArrayList<Integer> temp) {
		Collections.sort(temp);
		
		//int [] prelottoNum = new ApiConnect().lotto_json(901);
		//Integer lottoNumInteger[] = Arrays.stream(prelottoNum).boxed().toArray(Integer[]::new); 
		
		int checkCnt1 = 0
		  , checkCnt5 = 0
		  , tempNum = 0;
		
		int [] checkCnt2 = new int[10];
		int [] checkCnt3 = new int[5];
		
		int checkCnt4 = 0
		  , checkCnt6 = 0
		  , checkCnt7 = 0
		  , checkSum8 = 0;
		
		int tempCheck2 = 0
		  , tempCheck3 = 0;
				
		for(int a : temp) {
			tempNum = a;
			tempCheck2 = a % 10;
			tempCheck3 = a / 10;
			checkCnt2[tempCheck2]++;
			checkCnt3[tempCheck3]++;
			
			checkCnt4 = 0;//Arrays.asList(lottoNumInteger).indexOf(a) > 0 ? checkCnt4 + 1 : checkCnt4;
			checkCnt6 = a > 23 ? checkCnt6 + 1 : checkCnt6;
			checkCnt7 = (a % 2 == 1) ? checkCnt7 + 1 : checkCnt7;
			checkSum8 += a;
		}
		
		for(int a : checkCnt2){
			if(a > 2){
				return false;	
			}
		}
		
		for(int a : checkCnt3){
			if(a > 3){
				return false;
			}
		}
		
		if(checkCnt4 > 2 
				|| checkCnt6 % 6 == 0
				|| checkCnt7 % 6 == 0
				|| checkSum8 < 90
				|| checkSum8 > 190) {
			return false;
		}
		
		if(temp.get(5) - temp.get(0) < 25 || temp.get(5) - temp.get(0) > 42) {
			return false;
		}
		
		return true;
	}
}
