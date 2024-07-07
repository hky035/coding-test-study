
import java.util.Arrays;

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

    String name;
    int N;

    // ex. DDAAADAAC
    public int solution(String name) {
        int answer = 0;
        this.name = name;
        N = name.length();
        for (int i = 0; i < N; i++) {
            answer += marking(i);
        }

        int arr[] = findA(name);
        // arr 에서 값이 가장 큰 놈찾기(큰 놈 두 개 이상이면?)
        // 연속한 A가 가장 큰 지점을 알아내야함. 이 때 시작지점이랑 더 가까운 놈이냐 혹은 더 먼 놈이냐

        int maxIDXLeft = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[maxIDXLeft] < arr[i])
                maxIDXLeft = i;
        }

        int maxIDXRight = N - 1;
        for (int i = N - 2; i >= 0; i--) {
            if (arr[maxIDXRight] < arr[i])
                maxIDXRight = i;
        }

        int start = (maxIDXLeft < N - 1 - maxIDXRight) ? maxIDXLeft : maxIDXRight; // 왼쪽 끝
        int end = (start + arr[start] - 1) % N; // 오른쪽 끝
        int val;
        if (start - 1 == end) { // 전부 A거나 전부 A가 아님
            if (name.charAt(start) == 'A')
                answer += 0;
            else
                answer += N - 1;
        } else {
            System.out.println(start + ", " + end);
            answer += val = Math.min(2 * (N - end) + start, 2 * start + N - end);
            System.out.println(val);
        }


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
    int marking(int cursor) {
        return Math.min(name.charAt(cursor) - 'A', 'Z' - name.charAt(cursor) + 1);
    }

    /**
     * 
     * @param name 이름
     * @return 각 cursor 위치에 대해서 인접한 A의 개수 반환(오른쪽으로만 이동)
     */
    int[] findA(String name) {
        int[] list = new int[name.length()];
        for (int i = 0; i < name.length(); i++) {
            list[i] = countA(name, i);
        }
        // 이배열에서 값이 가장 큰 놈을 고름
        // 값이 가장 큰 놈이 2개 이상이면 0번 인덱스를 기준으로 왼쪽, 오른쪽 검사해서 더 가까운놈

        return list;
    }

    int countA(String name, int idx) {
        if (name.charAt(idx) != 'A')
            return 0;
        else
            return 1 + countA(name, (idx + 1) % name.length());
    }

    public static void main(String[] args) {

        Sol2 s = new Sol2();
        String name = "JAN";
        System.out.println(s.solution(name));

    }
}