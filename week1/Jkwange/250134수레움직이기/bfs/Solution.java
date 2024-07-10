import java.util.Queue;
import java.util.LinkedList;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

class Solution {

    class State {
        int redX, redY, blueX, blueY, turn;
        Set<String> redVisited;
        Set<String> blueVisited;

        State(int redX, int redY, int blueX, int blueY, int turn, Set<String> redVisited, Set<String> blueVisited) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.turn = turn;
            this.redVisited = new HashSet<>(redVisited);
            this.blueVisited = new HashSet<>(blueVisited);
        }

        @Override
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
        int[][] Information = new int[4][2];
        int rows = maze.length;
        int cols = maze[0].length;

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] != 0 && maze[i][j] != 5) {
                    Information[maze[i][j] - 1][0] = i;
                    Information[maze[i][j] - 1][1] = j;
                }
            }
        }

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        Set<String> initialRedVisited = new HashSet<>();
        Set<String> initialBlueVisited = new HashSet<>();
        initialRedVisited.add(Information[0][0] + "," + Information[0][1]);
        initialBlueVisited.add(Information[1][0] + "," + Information[1][1]);

        State init = new State(Information[0][0], Information[0][1], Information[1][0], Information[1][1], 0, initialRedVisited, initialBlueVisited);
        queue.add(init);
        visited.add(init);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            boolean redAtDestination = current.redX == Information[2][0] && current.redY == Information[2][1];
            boolean blueAtDestination = current.blueX == Information[3][0] && current.blueY == Information[3][1];
            if (redAtDestination && blueAtDestination) {
                return current.turn;
            }

            for (int[] redDirection : directions) {
                int nextRedX = current.redX + redDirection[0];
                int nextRedY = current.redY + redDirection[1];

                if (redAtDestination) {
                    nextRedX = current.redX;
                    nextRedY = current.redY;
                } else if (!isValid(nextRedX, nextRedY, rows, cols, maze) || current.redVisited.contains(nextRedX + "," + nextRedY)) {
                    continue;
                }

                for (int[] blueDirection : directions) {
                    int nextBlueX = current.blueX + blueDirection[0];
                    int nextBlueY = current.blueY + blueDirection[1];

                    if (blueAtDestination) {
                        nextBlueX = current.blueX;
                        nextBlueY = current.blueY;
                    } else if (!isValid(nextBlueX, nextBlueY, rows, cols, maze) || current.blueVisited.contains(nextBlueX + "," + nextBlueY)) {
                        continue;
                    }

                    if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                        continue;
                    }

                    if (nextRedX == current.blueX && nextRedY == current.blueY && nextBlueX == current.redX && nextBlueY == current.redY) {
                        continue;
                    }

                    Set<String> newRedVisited = new HashSet<>(current.redVisited);
                    Set<String> newBlueVisited = new HashSet<>(current.blueVisited);
                    newRedVisited.add(nextRedX + "," + nextRedY);
                    newBlueVisited.add(nextBlueX + "," + nextBlueY);

                    State nextState = new State(nextRedX, nextRedY, nextBlueX, nextBlueY, current.turn + 1, newRedVisited, newBlueVisited);
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.add(nextState);
                    }
                }
            }
        }
        return 0;
    }

    private boolean isValid(int x, int y, int rows, int cols, int[][] maze) {
        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != 5;
    }

}