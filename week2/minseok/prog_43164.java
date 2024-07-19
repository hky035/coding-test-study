import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
class Solution {
    String answerPath; //정답으로 결정난 경로를 담는 문자열
    String [] answer; //정답 리턴 배열
    boolean used[];// 티켓 사용 처리를 위한 배열
    boolean isFindRoute;// 문제를 충족하는 경로를 찾았는지를 나타냄
    int ticketCnt;//주어진 티켓의 개수
    public String[] solution(String[][] tickets) {
        ticketCnt = tickets.length;
        Arrays.sort(tickets,new Comp());
        used = new boolean[ticketCnt];
        dfs("ICN","ICN ",0,tickets);//출발은 무조건 인천에서 시작하고, 경로의 시작도 무조건 인천
        String [] answer = answerPath.split(" ");//공항마다 " "로 구분하여 문자열을 저장하였으므로 그걸 이용해서 문자배열로 반환받음.
        return answer;
    }
    public class Comp implements Comparator<String[]> {
    @Override
    public int compare(String[] o1, String[] o2) {
        if(o1[0].equals(o2[0]))
           return o1[1].compareTo(o2[1]);
        return o1[0].compareTo(o2[0]);
    }//모든 티켓을 사용하는 경로가 2개 이상이면, 무조건 사전순으로 빠른 걸 골라야하기 때문에, 2차원 배열을 사전순으로 정렬하기 위한 Comparator 구현
}
    public void dfs(String depart, String route, int cnt, String tickets[][]){
        if(isFindRoute)
            return;//이미 정렬을 해놓은 상태이기 때문에, 제일 처음 만들어진 루트가 사실상 정답임
        if(cnt==ticketCnt){//모든 티켓을 다 소모했으면
            answerPath = route;
            isFindRoute = true;
            return;
        }
        for(int i = 0 ;i<ticketCnt;i++){
            if(!used[i]&&tickets[i][0].equals(depart)){//아직 사용하지 않은 티켓이면서, 현재의 출발점과 티켓의 출발점이 같다면
            	used[i]=true;
                dfs(tickets[i][1],route+tickets[i][1]+" ",cnt+1,tickets);//그때의 티켓의 도착지를 다음 출발점으로 삼음.
                used[i]=false;
            }
        }
    }
}