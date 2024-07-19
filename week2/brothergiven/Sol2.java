package week2.brothergiven;

import java.util.PriorityQueue;
import java.util.Queue;
public class Sol2 {
    // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    // 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복
    // 최소 횟수 리턴
    int K;
    public int solution(int[] scoville, int K) {
        int answer = 0;
        this.K = K;
        // 가장 작은 거 두개 뽑아서 섞어서 더함
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int v : scoville)
            pq.add(v);
        while(!checkCompleted(pq) && pq.size() > 1){
            int v1 = pq.poll();
            int v2 = pq.poll();
            pq.add(v1 + v2 + v2);
            answer++;
        }

        if(!checkCompleted(pq)){
            return -1;
        }

        return answer;
    }

    boolean checkCompleted(Queue<Integer> q){
        for(int v : q)
            if(v < K) return false;
        return true;
    }
}
