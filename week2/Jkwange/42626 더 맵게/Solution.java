import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    ArrayList<String> answer = new ArrayList<>();
    boolean[] visited;
    String[][] tickets;
    
    public boolean travel(String current, int turn) {
        answer.add(current);
        if (turn == tickets.length + 1) {
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                if (travel(tickets[i][1], turn + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        
        answer.remove(answer.size() - 1);
        return false;
    }
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        visited = new boolean[tickets.length];
        this.tickets = tickets;

        travel("ICN", 1);

        return answer.toArray(new String[answer.size()]);
    }
}
