class Solution {
    public int solution(int n, int[][] computers) {

		int answer = 0;
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(computers, i, visited);
				answer++;
			}
		}

		return answer;
	}

	/**
	 * @methodName : dfs
	 * @description : 연결정보와 방문배열을 기준으로 서로 연결되어 있는지 확인하는 메소드
	 * @param computers
	 * @param i
	 * @param visited
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.05
	 */
	boolean[] dfs(int[][] computers, int i, boolean[] visited) {
		visited[i] = true;

		for (int j = 0; j < computers.length; j++) {
			if (i == j) {
				continue;
			} //
			else if (!visited[j] && computers[i][j] == 1) {
				visited = dfs(computers, j, visited);
			}
		}
		return visited;
	}
}