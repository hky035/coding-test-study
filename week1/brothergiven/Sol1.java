// Sol 1 : PROG 250134 수레 움직이기

public class Sol1 {
    /**
     * 0: 빈칸 1: 빨간 시작 2: 파란 시작 3: 빨간 도착 4: 파란 도착 5: 벽
     */
    final static int direction[][] = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };

    class Node {
        int redX, redY, blueX, blueY, depth;
        boolean redArrived = false, blueArrived = false;

        Node(int rX, int rY, int bX, int bY, int depth, boolean rArrived, boolean bArrived) {
            redX = rX;
            redY = rY;
            blueX = bX;
            blueY = bY;
            this.depth = depth;
            redArrived = rArrived;
            blueArrived = bArrived;
        }
    }

    int[][] maze;
    boolean[][] redVisited, blueVisited;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        redVisited = new boolean[maze.length][maze[0].length];
        blueVisited = new boolean[maze.length][maze[0].length];
        this.maze = maze;
        int rX = 0, rY = 0, bX = 0, bY = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    rX = i;
                    rY = j;
                }
                if (maze[i][j] == 2) {
                    bX = i;
                    bY = j;
                }
            }
        }

        Node now = new Node(rX, rY, bX, bY, 0, false, false);
        blueVisited[bX][bY] = true;
        redVisited[rX][rY] = true;
        DFS(now);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    void DFS(Node now) {
        if (now.redArrived && now.blueArrived) {
//          if(now.depth < 5)
//              System.out.printf("ANSWER : (%d, %d), (%d, %d), %d\n", now.redX, now.redY, now.blueX, now.blueY, now.depth);
            answer = Math.min(answer, now.depth);
            return;
        }
        for (int redMove[] : direction) {
            // red 이동 + blue 이동
            for (int blueMove[] : direction) {
                int rX = now.redArrived ? now.redX : now.redX + redMove[0];
                int rY = now.redArrived ? now.redY : now.redY + redMove[1];
                int bX = now.blueArrived ? now.blueX : now.blueX + blueMove[0];
                int bY = now.blueArrived ? now.blueY : now.blueY + blueMove[1];
                if (checkAvailable(rX, rY) && checkAvailable(bX, bY) // idx 범위
                        && !(equal(rX, rY, now.blueX, now.blueY) && equal(now.redX, now.redY, bX, bY))
                        // 두 수레가 교차되어선 안됨.
                        && maze[bX][bY] != 5 && maze[rX][rY] != 5
                        // 벽으로는 이동 불가
                        && !equal(rX, rY, bX, bY)
                        // 동시에 같은 곳으로 이동 불가
                        && (now.redArrived || !redVisited[rX][rY]) && (now.blueArrived ||!blueVisited[bX][bY])
                        // 이미 이동한곳으로는 이동 불가
                ) {
                    Node next = new Node(rX, rY, bX, bY, now.depth + 1, now.redArrived || maze[rX][rY] == 3, now.blueArrived || maze[bX][bY] == 4);
                    blueVisited[bX][bY] = true;
                    redVisited[rX][rY] = true;
                    DFS(next);
                    redVisited[rX][rY] = false;
                    blueVisited[bX][bY] = false;
                }
            }
        }
    
    }

    boolean checkAvailable(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length;
    }

    boolean equal(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 == y2;
    }

}
