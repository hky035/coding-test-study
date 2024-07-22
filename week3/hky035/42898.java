// 42898 등굣길
class Solution {
	int[][] map;
	int[][] route;
		
	public int solution(int m, int n, int[][] puddles) {
		map = new int[n][m];
        route = new int[n][m];
        
        for(int i = 0 ; i < n ; i++){
        	map[i][0]	= i;
            route[i][0] = 1;
        }
        for(int i = 0 ; i < m ; i++){
        	map[0][i]	= i;
            route[0][i] = 1;
        }
        
        for(int i = 0 ; i < puddles.length ; i++){
        	int pY = puddles[i][0]-1;
        	int pX = puddles[i][1]-1;
        	
            map[pX][pY] = Integer.MAX_VALUE;
            if(pX == 0) {
            	for(int j = pY ; j < m ; j++) {
            		map[0][j] = Integer.MAX_VALUE;
            		route[0][j] = 0;
            	}
            }
            
            if(pY == 0) {
            	for(int j = pX ; j < n ; j++) {
            		map[j][0] = Integer.MAX_VALUE;
            		route[j][0] = 0;
            	}
            }
        }
    
        
        for(int i = 1 ; i < n ; i++){
            for(int j = 1 ; j < m ; j++){
                if(map[i][j] == Integer.MAX_VALUE){
                    continue;
                }
                    
//                map[i][j] = (map[i-1][j] < map[i][j-1]) ? map[i-1][j] : ((map[i-1][j] == map[i][j-1]) ? map[i-1][j] + map[i][j-1] : map[i][j-1]);
                if (map[i-1][j] < map[i][j-1]) {
                    map[i][j] = map[i-1][j] + 1;
                    route[i][j] = route[i-1][j];
                } else if (map[i-1][j] > map[i][j-1]) {
                	map[i][j] = map[i][j-1] + 1;
                	route[i][j] = route[i][j-1];
                } else {
                	map[i][j] = map[i][j-1] + 1;
                	route[i][j] = route[i-1][j] + route[i][j-1];
                }

            }
        }
        
		
		return route[n-1][m-1] % 1000000007 ;
	}
}
