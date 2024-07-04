package lv2;

public class lv2_42860 {
	
	public int solution(String name) {
		int answer = 0;
		// 상하 방향키 이동 횟수
		for(int i = 0 ; i < name.length() ; i++) { 
			answer += Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);
		}
		
		// 좌우 방향키 이동 횟수
		int horizMove = name.length() - 1; // 초기 값 오른쪽으로만 이동 시
		for(int i = 0 ; i < name.length() - 1 ; i++) {
			int j;
			for(j = i + 1 ; j < name.length() ; j++) {
				if(name.charAt(j) != 'A')
					break;
			}
			
			horizMove = Math.min(horizMove, i * 2 + name.length() - j);
			horizMove = Math.min(horizMove, i + (name.length() - j) * 2);
		}
		return answer + horizMove;
	}

}
