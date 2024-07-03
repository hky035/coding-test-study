import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
class Solution {
    ArrayList<ArrayList<Integer>> win;//승자 정보
    ArrayList<ArrayList<Integer>> lose;//패자 정보
    Set<Integer> visit = new HashSet<>();//방문처리를 위한 HashSet
    public int solution(int n, int[][] results) {
        int answer = 0;
        win = new ArrayList<>(n+1);
        lose = new ArrayList<>(n+1);
        for(int i =0 ;i<=n;i++){
            ArrayList<Integer>t1 = new ArrayList<>(n+1);
            ArrayList<Integer>t2 = new ArrayList<>(n+1);
            win.add(t1);
            lose.add(t2);
        }
        for(int i =0 ;i<results.length;i++){
            win.get(results[i][0]).add(results[i][1]);//승자 정보 하나씩 저장
            lose.get(results[i][1]).add(results[i][0]);//패자 정보 하나씩 저장
        }
        for(int i = 1; i<=n;i++){
            visit.add(i);//각 노드 삽입
            checkWin(i);//해당 노드가 이긴 노드 탐색
            checkLose(i);//해당 노드가 진 노드 탐색
            if(visit.size()==n)//해당 노드의 정보가 선수 수만큼 있으면 순위 계산 가능
                answer++;
            visit.clear();//방문처리 초기화
        }
        return answer;
    }
    public void checkWin(int node){
        for(int i = 0 ;i<win.get(node).size();i++){
            int next = win.get(node).get(i);
            if(!visit.contains(next)){//무의미한 재귀를 막기 위한 조건문, 이미 탐색했던 건 굳이 더 깊이 안 들어가도 됨
                 visit.add(next);//node가 이긴 놈들을 방문처리함
                 checkWin(next);//그 놈에 대해 다시 파고듬
             }
        }
    }
    public void checkLose(int node){
        for(int i = 0 ;i<lose.get(node).size();i++){
             int next = lose.get(node).get(i);
             if(!visit.contains(next)){
                 visit.add(next);
                 checkLose(next);
             }
        }
    }
}