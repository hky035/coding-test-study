class Solution {
      class Game{
        int x;
        int y;
       public Game(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    int answer = 20;
    int [][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    int [][] arr;
    boolean [][]visit_r;
    boolean [][] visit_b;
    boolean last_r = false;
    boolean last_b = false;
    Game start_r;
    Game start_b;
    public int solution(int[][] maze) {
        arr = new int[maze.length][maze[0].length];
        arr = maze;
        visit_r= new boolean[maze.length][maze[0].length];
        visit_b= new boolean[maze.length][maze[0].length];
        for(int i = 0;i<maze.length;i++){
            for(int j = 0;j<maze[i].length;j++){
                if(maze[i][j]==1){
                    start_r = new Game(i,j);
                    visit_r[i][j] =true;
                }
                else if(maze[i][j]==2){
                    start_b = new Game(i,j);
                    visit_b[i][j] = true;
                }
            }
        }
        dfs(start_r,start_b,0);
        return (answer!=20) ? answer:0;
    }
   void dfs(Game red, Game blue, int cnt){
        if(last_r&&last_b){
            answer = Math.min(answer,cnt);
            return;
        }
        for(int i = 0 ;i<4;i++){
            for(int j = 0;j<4;j++){
            	Game nextRed = red, nextBlue = blue;
                if(!last_r)
                    nextRed = new Game(red.x+dir[i][0],red.y+dir[i][1]);
                if(!last_b)
                    nextBlue = new Game(blue.x+dir[j][0],blue.y+dir[j][1]);
                if(outOfBound(nextRed)||outOfBound(nextBlue))
                    continue;
                if((visitRedCheck(nextRed))||(visitBlueCheck(nextBlue)))
                    continue;
                if(nextRed.x==nextBlue.x&&nextRed.y==nextBlue.y)
                    continue;
                if(arr[nextRed.x][nextRed.y]==5||arr[nextBlue.x][nextBlue.y]==5)
                    continue;
                if((nextBlue.x==red.x&&nextBlue.y==red.y)&&(nextRed.x==blue.x&&nextRed.y==blue.y))
                    continue;
                visit_r[nextRed.x][nextRed.y] = true;
                visit_b[nextBlue.x][nextBlue.y] = true;
                if(arr[nextRed.x][nextRed.y]==3)
                    last_r = true;
                if(arr[nextBlue.x][nextBlue.y]==4)
                    last_b = true;
                dfs(nextRed,nextBlue,cnt+1);
                visit_r[nextRed.x][nextRed.y] = false;
                visit_b[nextBlue.x][nextBlue.y] = false;
                last_r = false;
                last_b = false;
                
            }
        }
        
    }
    boolean outOfBound(Game g){
        return (g.x<0||g.y<0||g.x>=arr.length||g.y>=arr[0].length);
    }
    boolean visitRedCheck(Game g){
        return (!last_r&&visit_r[g.x][g.y]);
    }
     boolean visitBlueCheck(Game g){
        return (!last_b&&visit_b[g.x][g.y]);
    }
}