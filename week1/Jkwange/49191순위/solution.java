import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] graph = new int[n][n];
        
        for (int i = 0; i < n; i++) 
            Arrays.fill(graph[i], 0);
        
        
        for (int[] result : results) {
            graph[result[0] - 1][result[1] - 1] = 1;
            graph[result[1] - 1][result[0] - 1] = -1;
        }
        
        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) graph[i][j] = 1;
                    if (graph[i][k] == -1 && graph[k][j] == -1) graph[i][j] = -1;
                }
            }
        }
        
        int answer = 0;
        
        
        for (int i = 0; i < n; i++) {
            boolean rank = true;
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] == 0 && graph[j][i] == 0) {
                    rank = false;
                    break;
                }
            }
            if (rank) {
                answer++;
            }
        }
        
        return answer;
    }
}
