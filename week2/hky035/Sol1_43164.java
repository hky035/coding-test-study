import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sol1_43164 {
	
	String[][] tickets;
	boolean[] isUsed;	
	String[] answer;
	int maxCount;
	boolean isCompleted;
	
	public String[] solution(String[][] tickets) {
		
		int N = tickets.length;
		isUsed = new boolean[N];
		maxCount = N;
		isCompleted = false;
		List<String> route = new ArrayList<>(N+1);
		
		Arrays.sort(tickets, (a, b) -> {
			if (a[0].equals(b[0])) {
				return a[1].compareTo(b[1]); // 도착지 알파벳 순서
			}
			return a[0].compareTo(b[0]);
		});
		
		this.tickets = tickets;
		answer = new String[N + 1];
		
		findRoute("ICN", 0, route);
		
		return answer;
	}
	
	private void findRoute(String place, int count, List<String> route) {
		route.add(place);
		
		if (isCompleted) return;
		
		if (count == maxCount) {
			isCompleted = true;
			answer = route.toArray(new String[0]);
			return;
		}
		
		for (int i = 0; i < tickets.length; i++) {
			if (place.equals(tickets[i][0]) && !isUsed[i]) {
				isUsed[i] = true;
				findRoute(tickets[i][1], count + 1, route);
				isUsed[i] = false;
			}
		}
		
		route.remove(route.size() - 1);
	}
}
