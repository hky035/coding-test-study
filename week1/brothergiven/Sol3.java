import java.util.ArrayList;
import java.util.Arrays;
public class Sol3 {
    /**
     * 권투 경기는 1대1 방식으로 진행, Result 의 각 row는 원소 2개 result[i][0] : 승자, result[i][1] : 패자
     * 했을 때 정확히 순위를 매길 수 있는 선수의 수
     * 
     * 리스트의 배열로 그래프 표현 후 DFS 호출
     * 이 때 그래프는 방향성 그래프로만.
     * 
     * 모든 정점에 대해서 DFS 호출 후에 순서가 매겨지는 것을 ranked 배열에 표시함
     * DFS 종료 후에 ranked의 row가 전부 true라면 그 row는 순위를 매길 수 있음 
     */

    ArrayList<Integer>[] graph;
    boolean [][] ranked;

    void DFS(int start, int now, boolean visited[]){
        if(!visited[now]){
            visited[now] = true;
            ranked[start][now] = true;
            ranked[now][start] = true;
            for(int next : graph[now]){
                DFS(start, next, visited);
            }
        }
    }

    public static void main(String[] args) {
        Sol3 s = new Sol3();
        System.out.println(s.solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    public int solution(int n, int[][] results){
        int answer = 0;
        graph = new ArrayList[n + 1];
        ranked = new boolean[n+1][n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<Integer>();
            ranked[i][i] = true;
        }
        for(int[] arr : results){
            graph[arr[0]].add(arr[1]);
        }
        for(int i = 1; i <= n; i++){
            boolean visited[] = new boolean[n+1];
            DFS(i, i, visited);
        }
        for(int i = 1; i <= n; i++){
            boolean check = true;
            for(int j = 1; j <= n && check; j++){
                if(!ranked[i][j])
                    check = false;
            }
            answer += check ? 1 : 0;
        }
        return answer;

    }
}
