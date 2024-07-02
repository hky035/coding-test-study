package lv3;



public class lv3_250134 {
	
	class Point {
		int x,y;
		
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
	}
	
	private boolean[][] visitedR;
	private boolean[][] visitedB;
	private int[] dx = {0,0,-1,1}; // 상하좌우
	private int[] dy = {1,-1,0,0};  
	private boolean redEnd;
	private boolean blueEnd;
	final static int MAX_VALUE = 10000;
	
	int maxWidth = 0;
	int maxHeight = 0;
	
	public int solution(int[][] maze ) {
		Point red = null;
		Point blue = null;
		
		visitedR = new boolean[maze.length][maze[0].length];
		visitedB = new boolean[maze.length][maze[0].length];
		
		maxHeight = maze.length;
		maxWidth = maze[0].length;
		
		// 시작 위치 
		for(int i = 0 ; i < maze.length ; i++) {
			for(int j = 0 ; j < maze[i].length ; j++) {
				if(maze[i][j] == 1) red = new Point(i,j);
				if(maze[i][j] == 2) blue = new Point(i,j);
			}
		}
		
		visitedR[red.getX()][red.getY()] = true;
		visitedB[blue.getX()][blue.getY()] = true;
		
		
		
		
		int answer = findPath(red, blue, 0, maze);
		return (answer == MAX_VALUE) ? 0 : answer; 
	}
	
	private Point nextPoint(int x, int y, int move) {
		int nextX = x + dx[move];
		int nextY = y + dy[move];
		
		return new Point(nextX,nextY);
	}
	
	// can move check
	private boolean canMove(Point red, Point blue, Point nextRed, Point nextBlue, int[][] maze) {
		if( nextRed.getX() < 0 || nextRed.getY() < 0 || nextRed.getX() >= maxHeight || nextRed.getY() >= maxWidth 
				|| nextBlue.getX() < 0 || nextBlue.getY() < 0 || nextBlue.getX() >= maxHeight || nextBlue.getY() >= maxWidth
				|| maze[nextRed.getX()][nextRed.getY()] == 5 || maze[nextBlue.getX()][nextBlue.getY()] == 5)
			return false;
		
		if((red.getX() == nextBlue.getX() && red.getY() == nextBlue.getY()) && (blue.getX() == nextRed.getX()) && (blue.getY() == nextRed.getY()))
			return false;
		
		if((!redEnd && visitedR[nextRed.getX()][nextRed.getY()]) || ( !blueEnd && visitedB[nextBlue.getX()][nextBlue.getY()]))
			return false;
		
		if(nextRed.getX() == nextBlue.getX() && nextRed.getY() == nextBlue.getY())
			return false;
		
		return true;
	}
	
	private int findPath(Point red, Point blue, int answer, int[][] maze) {
		if(redEnd && blueEnd) return answer;

		int result = MAX_VALUE;
		
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4; j++) {
				Point nextRed = (!redEnd) ? nextPoint(red.getX(), red.getY(), i) : red;
				Point nextBlue = (!redEnd) ? nextPoint(blue.getX(), blue.getY(), j) : blue;
				
				if(!canMove(red, blue, nextRed, nextBlue, maze)) 
					continue;
				
				visitedR[nextRed.getX()][nextRed.getY()] = true;
				visitedB[nextBlue.getX()][nextBlue.getY()] = true;
				
				if(maze[nextRed.getX()][nextRed.getY()] == 3)
					redEnd = true;
				if(maze[nextBlue.getX()][nextBlue.getY()] == 4)
					blueEnd = true;
				
				result = Math.min(result, findPath(nextRed, nextBlue, answer+1, maze));
				
				redEnd = false;
				blueEnd = false;
				visitedR[nextRed.getX()][nextRed.getY()] = false;
				visitedB[nextBlue.getX()][nextBlue.getY()] = false;
				
			}
		}
		
		return result;
	}
	
}
