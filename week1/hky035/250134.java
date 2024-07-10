public class Solution {
	
	class Point {
		int x,y;
		
		public Point() {
		}
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Point point = (Point)obj;
			if(this.x == point.getX() && this.y == point.getY())
				return true;
			else
				return false;
		}

	}
	
	boolean [][] visitedR;
	boolean [][] visitedB;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; // 상 우 하 좌(시계 방향)
	
	public int solution(int[][] maze) {
		int answer = Integer.MAX_VALUE;
		visitedR = new boolean[maze.length][maze[0].length];
		visitedB = new boolean[maze.length][maze[0].length];
		
		Point red = new Point();
		Point blue = new Point();
		
		findInitPos(red, blue, maze); // 마지막 위치도 기억할 필요 있나? X
		
		answer = findPath(red, blue, maze, 0);
		
		return (answer == Integer.MAX_VALUE) ? 0 : answer;
	}
	
	private int findPath(Point red, Point blue, int[][] maze, int depth) {
		boolean redEnd = maze[red.getX()][red.getY()] == 3;
        boolean blueEnd = maze[blue.getX()][blue.getY()] == 4;
        
		if(redEnd && blueEnd) { // 둘다 도착 => 현재 depth 리턴
			return depth;
		}
		
		int minDepth = Integer.MAX_VALUE;
		// 움직일 수 있는 지 체크
		if(redEnd && !blueEnd) {
			for(int i = 0 ; i < 4 ; i++ ) {
				Point nextBlue = new Point(blue.getX() + dir[i][0], blue.getY() + dir[i][1]);
				if(canMove(red, nextBlue, maze)) {
					if(!visitedB[nextBlue.getX()][nextBlue.getY()]) {
						visitedB[nextBlue.getX()][nextBlue.getY()] = true;
						minDepth = Math.min(minDepth, findPath(red, nextBlue, maze, depth+1));
						visitedB[nextBlue.getX()][nextBlue.getY()] = false;
					}
				}
			}
		}
		else if(!redEnd && blueEnd) {
			for(int i = 0 ; i < 4 ; i++ ) {
				Point nextRed = new Point(red.getX() + dir[i][0], red.getY() + dir[i][1]);
				if(canMove(nextRed, blue, maze)) {
					if(!visitedR[nextRed.getX()][nextRed.getY()]) {
						visitedR[nextRed.getX()][nextRed.getY()] = true;
						minDepth = Math.min(minDepth, findPath(nextRed, blue, maze, depth+1));
						visitedR[nextRed.getX()][nextRed.getY()] = false;
					}
				}
			}
		}
		else if(!redEnd && !blueEnd) {
			for(int i = 0 ; i < 4 ; i++) {
				for(int j = 0 ; j < 4 ; j++) {
					Point nextRed = new Point(red.getX() + dir[i][0], red.getY() + dir[i][1]);
					Point nextBlue = new Point(blue.getX() + dir[j][0], blue.getY() + dir[j][1]);
					if(!(red.equals(nextBlue) && blue.equals(nextRed)) && canMove(nextRed, nextBlue, maze)) {
						if(!visitedR[nextRed.getX()][nextRed.getY()] && !visitedB[nextBlue.getX()][nextBlue.getY()]) {
							visitedR[nextRed.getX()][nextRed.getY()] = true;
							visitedB[nextBlue.getX()][nextBlue.getY()] = true;
							minDepth = Math.min(minDepth, findPath(nextRed, nextBlue, maze, depth+1));
							visitedR[nextRed.getX()][nextRed.getY()] = false;
							visitedB[nextBlue.getX()][nextBlue.getY()] = false;
						}
						
					}
				}
			}
		}
		
		return minDepth;
	}
	
	private boolean canMove(Point red, Point blue, int[][] maze) {
		int rX = red.getX();
		int rY = red.getY();
		int bX = blue.getX();
		int bY = blue.getY();
		
		if(rX >= maze.length || rX < 0 || rY >= maze[0].length || rY < 0 
		   || bX >= maze.length || bX < 0 || bY >= maze[0].length || bY < 0)
			return false;
		
		if(rX == bX && rY == bY)
			return false;
		
		if(maze[rX][rY] == 5 || maze[bX][bY] == 5)
			return false;
		
		else
			return true;
	}
	
	

	private void findInitPos(Point red, Point blue, int[][] maze) {
		for(int i = 0 ; i < maze.length ; i++) {
			for(int j = 0 ; j < maze[i].length ; j++) {
				if(maze[i][j] == 1) {
					red.setX(i);
					red.setY(j);
					visitedR[i][j] = true;
				}
				else if(maze[i][j] == 2) {
					blue.setX(i);
					blue.setY(j);
					visitedB[i][j] = true;
				}
			}
		}
	}
}
