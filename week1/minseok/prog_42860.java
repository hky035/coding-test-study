class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int move = name.length()-1;//move 초기 값 설정
        int cnt = 0;//연속된 'A'의 길이
        int max = 0;//가장 긴 연속된 'A'의 길이
        int end = 0;//가장 긴 연속된 'A'의 마지막 인덱스
        for(int i = 0;i<len;i++){
            int incre = name.charAt(i)-'A';
            if(incre>=13)//중간 위치보다 더 높은 위치면 
                incre=26-incre;//뒤에서부터 접근한 값으로 바꿈
            answer+=incre;//각 문자마다의 상하이동값을 누적
            
            if(incre==0){
                cnt++;//만약 알파벳이 'A'면 증가
                if(max<cnt){//증가한 게 현재 max보다 크면 
                    end = i;//그때의 idx를 저장하고
                    max=cnt;//max 갱신
                }
            }
            else
                cnt = 0;//연속이 끊기면 리셋
        }
        
        if(cnt==len)//문자열이 'A'로 이루어져있으면 그냥 0
            return 0;
        
        if(name.charAt(0)=='A')//만약 0번째 문자가 'A'면 예외 처리 시작
        {
            int cnt1 = 0;//앞쪽의 연속된 'A' 길이
            int cnt2 = 0;//뒷쪽의 연속된 'A' 길이
            for(int i = 0;i<len;i++){
                if(name.charAt(i)!='A')
                    break;
                cnt1++;
            }
            for(int i = len-1;i>0;i--){
                if(name.charAt(i)!='A')
                    break;
                cnt2++;
            }
            if(cnt1+cnt2>=max)//현재 저장된 max보다 이 둘을 더한 값이 크면
                return answer+len-1-Math.max(cnt1,cnt2);//둘 중에 더 긴쪽을 피해서 한 방향으로 이동
            
        }
        int start = end - max;//방향을 트는 지점을 파악
        move = Math.min(move, start*2+(len-1-end));//오른쪽으로 이동하다가 다시 왼쪽으로 이동하는 경우
        move = Math.min(move, start+(len-1-end)*2);//왼쪽으로 이동하다가 다시 오른쪽으로 이동하는 경우
        return answer+move;
    }
}