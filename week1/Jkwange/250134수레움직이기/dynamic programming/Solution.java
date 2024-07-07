import java.util.*;

public class Solution {
    static class State {
        int redX, redY, blueX, blueY, turns;
        Set<String> redVisited, blueVisited;

        State(int redX, int redY, int blueX, int blueY, int turns, Set<String> redVisited, Set<String> blueVisited) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.turns = turns;
            this.redVisited = new HashSet<>(redVisited);
            this.blueVisited = new HashSet<>(blueVisited);
        }

        String getStateString() {
            return redX + "," + redY + "," + blueX + "," + blueY;
        }
    }

    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        int[] startRed = new int[2];
        int[] startBlue = new int[2];
        int[] targetRed = new int[2];
        int[] targetBlue = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 2) {
                    startRed[0] = i;
                    startRed[1] = j;
                } else if (maze[i][j] == 3) {
                    startBlue[0] = i;
                    startBlue[1] = j;
                } else if (maze[i][j] == 4) {
                    targetRed[0] = i;
                    targetRed[1] = j;
                } else if (maze[i][j] == 5) {
                    targetBlue[0] = i;
                    targetBlue[1] = j;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Set<String> initialRedVisited = new HashSet<>();
        initialRedVisited.add(startRed[0] + "," + startRed[1]);
        Set<String> initialBlueVisited = new HashSet<>();
        initialBlueVisited.add(startBlue[0] + "," + startBlue[1]);

        State initialState = new State(startRed[0], startRed[1], startBlue[0], startBlue[1], 0, initialRedVisited, initialBlueVisited);
        queue.add(initialState);
        visited.add(initialState.getStateString());

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            if (currentState.redX == targetRed[0] && currentState.redY == targetRed[1] &&
                currentState.blueX == targetBlue[0] && currentState.blueY == targetBlue[1]) {
                return currentState.turns;
            }

            for (int[] direction : directions) {
                int nextRedX = currentState.redX + direction[0];
                int nextRedY = currentState.redY + direction[1];
                int nextBlueX = currentState.blueX + direction[0];
                int nextBlueY = currentState.blueY + direction[1];

                if (nextRedX == nextBlueX && nextRedY == nextBlueY) {
                    continue;
                }

                if (!isValid(nextRedX, nextRedY, n, m, maze, currentState.redVisited, currentState.blueX, currentState.blueY) ||
                    !isValid(nextBlueX, nextBlueY, n, m, maze, currentState.blueVisited, currentState.redX, currentState.redY)) {
                    continue;
                }

                Set<String> nextRedVisited = new HashSet<>(currentState.redVisited);
                Set<String> nextBlueVisited = new HashSet<>(currentState.blueVisited);
                nextRedVisited.add(nextRedX + "," + nextRedY);
                nextBlueVisited.add(nextBlueX + "," + nextBlueY);

                State nextState = new State(nextRedX, nextRedY, nextBlueX, nextBlueY, currentState.turns + 1, nextRedVisited, nextBlueVisited);
                if (!visited.contains(nextState.getStateString())) {
                    visited.add(nextState.getStateString());
                    queue.add(nextState);
                }
            }
        }

        return 0;
    }

    private boolean isValid(int x, int y, int n, int m, int[][] maze, Set<String> visited, int otherX, int otherY) {
        return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] != 1 && !visited.contains(x + "," + y) && (x != otherX || y != otherY);
    }

}
