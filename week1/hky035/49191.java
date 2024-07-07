package lv3;

import java.util.ArrayList;
import java.util.List;


public class lv3_49191 {
	
	int[][] res;
	int answer = 0;
	
	public int solution(int n, int[][] results) {
        res = new int[n][n]; // start to 0
        
        for(int i = 0 ; i < results.length ; i++) {
        	res[results[i][0]-1][results[i][1]-1] = 1; // win 1
        	res[results[i][1]-1][results[i][0]-1] = 2; // lose 2
        }

        // floyd-warshall
        for(int k = 0 ; k < n ; k++) {
        	for(int i = 0 ; i < n ; i++) {
        		for(int j = 0 ; j < n ; j++) {
        			if(res[i][k] == 1 && res[k][j] == 1) { // i > k > j
        				res[i][j] = 1;
        				res[j][i] = 2;
        			}
        		}
        	}
        }
        
        List<Integer> boxer = new ArrayList<Integer>();
        
        for(int i = 0 ; i < n ; i++)
        	boxer.add(i); // add boxer , 여기 안에 있는 선수 대상으로 검사

        recursion(n, boxer);
        
        return answer;
    }
	
	private void recursion(int n, List<Integer> boxer) {

		if(n == 1) {
			answer++;
			return;
		}
				
		for(int i = 0 ; i < boxer.size() ; i++) {
			List<Integer> winner = new ArrayList<Integer>();
			List<Integer> loser  = new ArrayList<Integer>();
			
			if(check(boxer.get(i), n, winner, loser)) { // 모두 1또는 2이면
				answer++;
				changeState(boxer.get(i), winner, loser);
				recursion(winner.size(), winner); // loser 상대 결과도 바꿔줘야하네
				recursion(loser.size(), loser);
			}
		}
	}
	
	private boolean check(int boxer,int n, List<Integer> winner, List<Integer> loser) {
		int winCnt = 0;
		int loseCnt  = 0;
		for(int i = 0 ; i < res[boxer].length ; i++) {
			if(res[boxer][i] == 1) {		// 내가 이긴 상대
				if(i != boxer)
					loser.add(i);
				winCnt++;
			}
			else if(res[boxer][i] == 2) {	// 내가 진 상대
				if(i != boxer)
					winner.add(i);
				loseCnt++;
			}
		}
		
		if(winCnt + loseCnt == n-1)
			return true;
		else 
			return false;
	}
	
	private void changeState(int boxer, List<Integer> winner, List<Integer> loser) {
		for(int i = 0 ; i < res.length ; i++) {
			res[i][boxer] = 3; // 더이상 볼 필요 없음
		}
		
		for(int i : winner) {
			for(int j : loser) {
				res[j][i] = 3;
				res[i][j] = 3;
			}
		}
	}
}
