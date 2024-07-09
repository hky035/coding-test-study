
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
     * 
     * 1번이랑 2번이랑 완전 따로 계산할 수 있음.
     * 2번은 그냥 하면 되고
     * 1번은 어떻게?
     * 모든 위치를 한 번씩은 가야하지만 "연속한 A" 는 갈 필요가 없음 (한 칸씩 이동하다가 마지막에 가면 된다)
     * "연속한 A"
     * 연속하지 않은 A에 대해서 어차피 연속하지 않으면 걔네는 지나쳐야함
     * 따라서 연속한 A가 가장 큰 지점을 알아내야 한당!
     */


    // ex. DDAAADAAC
    public int solution(String name) {
        int answer = 0;
        int N = name.length();

        // arr 에서 값이 가장 큰 놈찾기(큰 놈 두 개 이상이면?)
        // 연속한 A가 가장 큰 지점을 알아내야함. 이 때 시작지점이랑 더 가까운 놈이냐 혹은 더 먼 놈이냐
        for (int i = 0; i < N; i++) {
            answer += marking(name, i);
        }
        int val = N-1;

        int startIDX = 0;

        while (startIDX != N && name.charAt(startIDX) == 'A') { // A가 아닌 지점에서 시작.
            startIDX++;
        }
        System.out.println("STARTIDX:  " + startIDX);
        if (startIDX == N) { // 전부 A
            val = 0;
        } else {
            for (int i = 0; i < N; i++) {
                if (name.charAt((startIDX + i) % N) == 'A') {
                    int left, right, candi; // 연속된 A를 기준으로 left : 왼쪽에 있는 문자의 개수, right : 오른쪽에 있는 문자의 개수
                    right = left = (i + startIDX) % N;
                    
                    while(name.charAt((right + 1) % N ) == 'A' && (right + 1) % N != left)
                        right = (right + 1) % N;

                    if(left > right){
                        candi = left - right - 1 + Math.min(right, N - left);
                    } else if (left == 0) {
                        candi = N - 1 - right;
                    } else {
                        candi = left - 1 + N - right - 1 + Math.min(left - 1, N - right - 1);
                    }
                
                    val = Math.min(val, candi);
                    i += (right >= left) ? right - left + 1 : N - (left - right) + 1;
                }
            }
        

        }

        answer += val;
        return answer;
    }

    /**
     * ASCII : 'A' ~ 'Z'
     * 현재 커서의 문자는 반드시 A에서 시작, name.charAt(cursor)로 가는 데 드는 조이스틱 조작 횟수 반환
     * Z로 간 다음에 name.charAt(cursor)까지 가는 것 VS A에서 시작해서 name.charAt(cursor)까지 가는 것
     * 
     * @param cursor 조이스틱을 움직일 현재 cursor의 위치
     * @return 조이스틱을 움직이기 위해 조작한 횟수
     */
    int marking(String name, int cursor) {
        return Math.min(name.charAt(cursor) - 'A', 'Z' - name.charAt(cursor) + 1);
    }


    public static void main(String[] args){
        Sol2 s = new Sol2();
        String name = "AABAAABAA";
        
        System.out.println(s.solution(name));

    }
}