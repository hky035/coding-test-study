package week2.brothergiven;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
public class Sol1 {
    // tickets의 col수는 반드시 2
    // tickets[i][0] : 출발, tickets[i][1] : 도착
    // 주어진 모든 항공권 사용하여 방문하는 공항 경로 return
    // 경로가 2개 이상이라면 알파벳 순서
    PriorityQueue<String[]> results;
    String[][] tickets;
    boolean used[];
    int N;
    public String[] solution(String[][] tickets){
        N = tickets.length;
        this.tickets = tickets;
        used = new boolean[N];
        results = new PriorityQueue<String[]>(new Comp<>());
        LinkedList<String> start = new LinkedList<>();
        start.add("ICN");
        findRoute("ICN", start);
        return results.poll();
    }

    class Comp<T> implements Comparator<T[]>{
        public int compare(T[] o1, T[] o2) {
            for(int i = 0; i < o1.length; i++){
                int cmp = ((String) o1[i]).compareTo((String)o2[i]);
                if(!(cmp == 0)) return cmp;
            }
            return 0;
        }
    }


    public void findRoute(String from, LinkedList<String> stack){
        if(allUsed()){
            Collections.reverse(stack);
            results.add(stack.toArray(new String[stack.size()]));
            Collections.reverse(stack);
            return;
        }
        for(int i = 0; i < N; i++){
            if(tickets[i][0].equals(from) && !used[i]){
                used[i] = true;
                stack.push(tickets[i][1]);
                findRoute(tickets[i][1], stack);
                stack.pop();
                used[i] = false;
            }
        }
    }

    public boolean allUsed(){
        for(int i = 0; i < N; i++){
            if(!used[i])
                return false; 
        }
        return true;
    }

    public static void main(String[] args) {
        Sol1 s = new Sol1();
        String tickets[][] = //{{"ICN", "JFK"}, {"HND", "LAD"},{"JFK","HND"}};
		{ { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
			{ "ATL", "SFO" } };
       System.out.println(Arrays.toString(s.solution(tickets)));
    }
}
