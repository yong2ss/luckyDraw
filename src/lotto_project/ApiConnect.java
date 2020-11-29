package lotto_project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class ApiConnect {
	public String lotto_select(int round) {
		String apiURL = "https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=" + round;
		String json = "";
		BufferedReader br;
		try{
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        json = br.readLine();
	        br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return json;
	}
	
	public int [] lotto_json (int round) {
		String json = lotto_select(round);
		
		int [] lottoNum = new int[7];		
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(json);
			//System.out.println(obj);
			
			for(int i = 0; i < 6; i++) {
				lottoNum[i] = ((Long)obj.get("drwtNo" + (i+1))).intValue();
			}
			lottoNum[6] = ((Long)obj.get("bnusNo")).intValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lottoNum;
	}
}
