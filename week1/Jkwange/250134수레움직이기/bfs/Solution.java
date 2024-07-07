import java.util.Queue;
import java.util.LinkedList;
import java.util.Objects;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

class Solution {

    class State {
        int redX, redY, blueX, blueY, turn;

        State(int redX, int redY, int blueX, int blueY, int turn) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.turn = turn;
        }

        
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            State state = (State) o;
            return redX == state.redX && redY == state.redY && blueX == state.blueX && blueY == state.blueY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(redX, redY, blueX, blueY);
        }
    }

    public int solution(int[][] maze) {
        int[][] Information = new int[4][2]; // 4개의 저장장소: 빨강, 파랑, 빨강 목적지, 파랑 목적지
        int rows = maze.length;
        int cols = maze[0].length;

        // 좌표 찾기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] != 0 && maze[i][j] != 5) {
                    Information[maze[i][j] - 1][0] = i;
                    Information[maze[i][j] - 1][1] = j;
                }
            }
        }
        System.out.println(Arrays.deepToString(Information));
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<State> queue = new LinkedList<>();
        Set<State> set = new HashSet<>();

        State init = new State(Information[0][0], Information[0][1], Information[1][0], Information[1][1], 0);
        queue.add(init);
        set.add(init);

        
        int redTurns = 0;
        int blueTurns = 0;
        while (!queue.isEmpty()) {
            State current = queue.poll();

            boolean checkRed = current.redX == Information[2][0] && current.redY == Information[2][1];
            boolean checkBlue = current.blueX == Information[3][0] && current.blueY == Information[3][1];
            if (checkRed && checkBlue)
                return current.turn;
            if (checkRed) redTurns = current.turn;
            if (checkBlue) blueTurns = current.turn;
            if(checkRed && checkBlue) return Math.max(redTurns, blueTurns);

            for (int[] direction : directions) {
                int nextRedX = current.redX + direction[0];
                int nextRedY = current.redY + direction[1];
                int nextBlueX = current.blueX + direction[0];
                int nextBlueY = current.blueY + direction[1];

                boolean checkValidRed = !isValid(nextRedX, nextRedY, rows, cols, maze);
                boolean checkValidBlue = !isValid(nextBlueX, nextBlueY, rows, cols, maze);
                boolean isSamePos = nextRedX == nextBlueX && nextRedY == nextBlueY;

                if (checkValidRed || checkValidBlue) {
                    continue;
                }
                if (isSamePos) {
                    continue;
                }

                State nextState = new State(nextRedX, nextRedY, nextBlueX, nextBlueY, current.turn + 1);
                if (!set.contains(nextState)) {
                    set.add(nextState);
                    queue.add(nextState);
                }
            }
        }
        return 0;
    }

    private boolean isValid(int x, int y, int rows, int cols, int[][] maze) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != 5;
    }

}
