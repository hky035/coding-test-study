import java.util.PriorityQueue;

public class Sol2_42626 {
	public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a,b) -> a.compareTo(b));
        
        for(int s : scoville)
        	minHeap.add(s);
        
        int count = 0;
        while(minHeap.peek() < K) {
        	Integer food1 = minHeap.remove();
        	Integer food2 = minHeap.remove();
        	
        	if(minHeap.size() == 2 && (food1 + food2*2) < K) {
        		count = -1;
        		break;
        	}
        	
        	minHeap.add(food1 + food2*2);
        	count++;
        }
     return count;
    }
}