/**
 * Sol2 : PROG 42860 조이스틱
 */
public class Sol2 {
    /*
     * 맨 처음은 A로 이루어진 알파벳 문자열에서 시작
     * UP : 다음 알파벳, DOWN : 이전 알파벳
     * LEFT : 커서 왼쪽으로 이동, RIGHT : 커서 오른쪽으로 이동
     * 각 조이스틱 조작 횟수를 COUNT 해서
     * 만들고자 하는 name을 만들기 위한 조작 횟수의 최소값 return
     * 
     */

    /**
     * 1. 왼쪽으로 갈지 오른쪽으로 갈지 결정
     * 1-a. "A는 방문할 필요 없다",
     * 현재 위치에서 왼쪽에 있는 A 중 가장 가까운 A와
     * 오른쪽에 있는 A 중 가장 가까운 A 둘 중 비교하여서
     * 더 멀리 있는 A로 간다.
     * 그 다음은? 그 A에서 다시 검사해서 더 멀리있는 A로 이동
     * A가 없다면 그냥 순차탐색
     * 2. 위로 갈지 오른쪽으로 갈지 결정
     * 2-a. ASCII로 비교하여서 더 가까운 방향으로 가면 됨
     */
    int N;
    String name;
    boolean marked[];

    public int solution(String name) {
        int answer = 0;
        this.name = name;
        N = name.length();
        marked = new boolean[N];
        int cursor = 0; // 현재 커서의 위치
        int nextA, count; // nextA : 다음 이동할 A
        // 전부 mark 될 때까지 반복, 
        while(allMarked()){
            nextA = findNextA(cursor); // cursor부터 nextA까지 반복,  


        }
        return answer;
    }


    /**
     * ASCII : 'A' ~ 'Z'
     * 현재 커서의 문자는 반드시 A에서 시작, name.charAt(cursor)로 가는 데 드는 조이스틱 조작 횟수 반환
     * Z로 간 다음에 name.charAt(cursor)까지 가는 것 VS A에서 시작해서 name.charAt(cursor)까지 가는 것
     * @param cursor 조이스틱을 움직일 현재 cursor의 위치
     * @return 조이스틱을 움직이기 위해 조작한 횟수
     */
    int marking(int cursor){
        marked[cursor] = true;
        return Math.min(name.charAt(cursor) - 'A', 'Z' - name.charAt(cursor) + 1);
    }


    int findNextA(int cursor) { // 다음 이동해야하는 A의 위치 return
        int left = -1, right = -1;

        for (int i = (cursor + 1) % N; i != cursor; i = (i + 1) % N) // 오른쪽으로 이동
        {
            if (name.charAt(i) == 'A') {
                left = i;
                break;
            }
        }

        for (int i = (cursor == 0) ? N - 1 : cursor - 1; i != cursor; i = (i == 0) ? N - 1 : i - 1) {
            if (name.charAt(i) == 'A') {
                right = i;
                break;
            }
        }

        return Math.max(left, right); // name 안에서 더 찾을 A 없다면
    }

    boolean allMarked(){
        for(boolean b : marked)
            if(!b) return false; // mark 안된 게 있다면
        return true;
    }

}