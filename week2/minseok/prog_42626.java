import java.util.PriorityQueue;
class Solution {
    PriorityQueue<Integer> foods;
    public int solution(int[] scoville, int K) {
        int answer = 0;
        foods = new PriorityQueue<Integer>(scoville.length);//힙으로 구현한 우선순위 큐 -> 오름차순으로 자동 정렬
        for(int i =0 ;i<scoville.length;i++){
            foods.add(scoville[i]);
        }
        if(foods.peek()>=K)//큐의 제일 작은 값이 K이상이면 바로 조건 만족
            return answer;
        while(true){
            answer++;
            int firstFood = foods.poll();//제일 값이 낮은 음식 추출
            int secondFood = foods.poll();//두번째로 값이 낮은 음식 추출
            int mixedFood = firstFood+(secondFood*2);//음식 합치기
            foods.add(mixedFood);//합친 음식 다시 넣기
            if(foods.peek()>=K)//큐의 제일 작은 값이 K이상이면 조건 만족
                return answer;
            if(foods.size()==1)//큐에 방금 넣은 mixedFood밖에 없으면 더이상 진행 불가
                break;
                
        }
        return -1;//break로 빠져나온 경우에 대한 케이스
    }
}