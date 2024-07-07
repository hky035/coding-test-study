class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int move = name.length() - 1;
        
        // 각 알파벳 변경에 필요한 조작 횟수 계산
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }
            // i에서 next로 넘어가기 위한 이동 횟수 계산
            move = Math.min(move, i + len - next + Math.min(i, len - next));
        }

        answer += move;
        return answer;
    }
}