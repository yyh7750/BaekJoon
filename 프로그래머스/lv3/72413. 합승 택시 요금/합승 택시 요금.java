class Solution {
    
    static final int MAX = 20000001;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        int[][] dist = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < dist.length; j++) {
				if(i == j) {
					continue;
				}
				dist[i][j] = MAX;
			}
		}

		for (int i = 0; i < fares.length; i++) {
			int from = fares[i][0];
			int to = fares[i][1];
			int tax = fares[i][2];

			dist[from][to] = tax;
			dist[to][from] = tax;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		answer = dist[s][a] + dist[s][b];
        
        for (int i = 1; i <= n; i++) {
				answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
		}
        
        return answer;
    }
}