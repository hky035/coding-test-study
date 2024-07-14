import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int food : scoville) 
            q.add(food);
        
        while(q.peek() < K) {
            if(q.size() <= 1) return -1;
            int first = q.poll();
            int second = q.poll();
            q.add(first + (second << 1));
            answer++;
        }
        
        return answer;
    }
}
