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
