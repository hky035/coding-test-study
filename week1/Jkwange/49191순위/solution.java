import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[] winScore = new int[n];
        int[] looseScore = new int[n];
        int[] rank = new int[n];
        
        // 초기 설정
        Arrays.fill(rank, 0);
        for (int[] result : results) {
            winScore[result[0] - 1]++;
            looseScore[result[1] - 1]++;
        }

        // rank 등록
        for (int i = 0; i < n; i++) {
            if (winScore[i] + looseScore[i] == n - 1) {
                rank[i] = looseScore[i] + 1;
            }
        }

        // 승패 관계를 이용해 순위를 업데이트
        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) {
                if (rank[i] != 0) continue;
                for (int winner : winScore) {
                    if (rank[winner] != 0) {
                        rank[i] = rank[winner] + 1;
                        updated = true;
                        break;
                    }
                }
                if (rank[i] != 0) continue;
                for (int loser : looseScore) {
                    if (rank[loser] != 0) {
                        rank[i] = rank[loser] - 1;
                        updated = true;
                        break;
                    }
                }
            }
        } while (updated);

        
        for(int i = 0; i< n; i++) 
            if(rank[i] != 0) answer++;
        
        // 결과 출력
        System.out.println(Arrays.toString(winScore));
        System.out.println(Arrays.toString(looseScore));
        System.out.println(Arrays.toString(rank));

        return answer;
    }
}