# 프로그래머스 250134 수레 움직이기

📌 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/250134

풀이 1: red, blue 둘 다 도착한 CASE / red만 도착한 CASE / blue만 도착한 CASE / 둘 다 도착하지 않은 CASE 구분하여 각각 처리해줌.
둘 다 도착했다면 그 시점의 depth 저장, 둘 중 하나만 도착했다면 아직 도착하지 않은 color에 대해 DFS 이어가고(4회), 둘 다 도착하지 않았다면 두 Color 모두에 DFS(4*4 = 16회)해줌.

풀이 2 : 풀이 1과 유사하지만 둘 중 하나만 도착했을 때 DFS 호출을 이어가야 한다는 것은 변함 없으므로
이에 대한 처리를 DFS 내 2중 for문 내에서 한꺼번에 해줌. 예를 들어 red가 이미 도착한 상태라면 수레의 이동 조건을 확인할 때 red는 무시하는 방법으로 진행.

코드 자체는 풀이 2가 더 깔끔하지만 실행시간은 풀이 1이 더 우수함 : 풀이 1에서의 loop 수가 절대적으로 적기 때문(풀이 2에서는 반드시 4*4=16회 반복)


- 풀이 1 결과 https://github.com/brothergiven/coding-test-study/commit/abe2e2008a04d5f4eb36685d0977ff97a8082f88

![image](https://github.com/brothergiven/coding-test-study/assets/128305393/eac64d4a-5fbb-4ba8-b6dc-4b63c2798c41)



- 풀이 2 결과 https://github.com/brothergiven/coding-test-study/commit/abe2e2008a04d5f4eb36685d0977ff97a8082f88

![image](https://github.com/brothergiven/coding-test-study/assets/128305393/22e2028b-9073-4753-9b2c-df610ce9cc7f)

# 프로그래머스 42860 조이스틱

📌 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/42860

- 접근
  1. 좌우 이동과 상하 이동은 완전히 분리하여 생각할 수 있다. 따라서 상하 이동을 간단한 char 연산을 통해 먼저 구해놓고 좌우 이동에 집중하자.
  2. 좌우 이동의 경우 문자 "A" 로는 이동할 필요가 없다. 따라서 연속한 A가 몇 개가 있는지를 구한 후 이를 이용하자

- 풀이 
  1. 상하이동 계산
  2. A가 아닌 지점에서부터 시작하여 A를 발견했다면 연속된 A의 시작 지점과 종료 지점 찾음
  3. 그 연속된 A를 거치지 않고 좌우이동 할 때의 이동 횟수를 구한 다음 그 횟수 중 최솟값이 정답이 됨.
  - 주의점 : A가 아닌 지점부터 시작해야함. 연속된 A의 시작 지점과 종료 지점을 찾기 위해서.
 
- 후기
  - 쉬워보였지만 생각할 CASE가 너무 많은 쉽지않은 문제였다.

- 결과
![image](https://github.com/brothergiven/coding-test-study/assets/128305393/8d2ded6b-4773-4d22-b7b5-c2e27eb1da6c)


# 프로그래머스 49191 순위

📌 문제 : https://school.programmers.co.kr/learn/courses/30/lessons/49191

- 접근 및 풀이
  1. 그래프 간선의 시작 정점과 종료 정점 형식으로 입력이 주어지므로 리스트의 배열 형식으로 그래프를 표현하자.
  2. 승자와 패자가 특징이 다르므로 방향성 그래프로 표현해야한다.
  3. A가 B를 이기고 B가 C를 이겼다면 A가 C보다 높은 순위인 것이다.
    - 즉, DFS 호출을 하면 호출 시작 정점과 방문하게 되는 모든 정점은 순위가 정해지게 된다.
    - 이를 이용하여 모든 정점에서부터 시작하여 DFS호출을 해준 후 다른 모든 정점과 순위가 정해진 정점의 수를 return.

- 후기
  - 그래프 이론으로 풀 수 있는 재밌는 문제였다. 다른 사람들의 풀이를 보면 신박하고 깔끔한 풀이법이 많을 것 같다.

- 결과
  ![image](https://github.com/brothergiven/coding-test-study/assets/128305393/18a81293-6027-4220-9680-a97d4af0b76d)
