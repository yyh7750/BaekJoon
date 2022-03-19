import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int result = 0;

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		// 컴퓨터 수 - 정점
		int n = 0;
		// 이어져 있는 쌍의 수 - 간선
		int pair = 0;

		// 그래프 - 인접행렬
		int[][] graph = null;
		// 방문여부
		boolean[] visited = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			pair = Integer.parseInt(br.readLine());

			graph = new int[n + 1][n + 1];
			visited = new boolean[n + 1];
			
			// 인접행렬 생성 - 간선 연결정보 저장
			for(int i = 0; i < pair; i++) {
				st = new StringTokenizer(br.readLine());
				int line1 = Integer.parseInt(st.nextToken());
				int line2 = Integer.parseInt(st.nextToken());
				
				graph[line1][line2] = 1;
				graph[line2][line1] = 1;
			}
			
			dfs(graph, visited, 1);
			
			// 1번 컴퓨터 제외
			System.out.println(result - 1);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
	
	private static void dfs(int[][] graph, boolean[] visited, int n) {
		// 재귀함수 종료 조건 설정
		if(visited[n]) {
			return;
		}
		
		visited[n] = true;
		result++;
		// 탐색 시작
		for(int i = 0; i < graph[n].length; i++) {
			if(graph[n][i] == 1 && !visited[i]) {
				dfs(graph, visited, i);
			}
		}
	}
}