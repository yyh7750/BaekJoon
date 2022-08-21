import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int result;
	private static int[][] adjArr;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = 0;
		int pair = 0;


		N = Integer.parseInt(br.readLine());
		pair = Integer.parseInt(br.readLine());

		adjArr = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < pair; i++) {
			st = new StringTokenizer(br.readLine());
			int line1 = Integer.parseInt(st.nextToken());
			int line2 = Integer.parseInt(st.nextToken());

			adjArr[line1][line2] = 1;
			adjArr[line2][line1] = 1;
		}

		dfs(adjArr, visited, 1);

		// 1번 컴퓨터 제외
		System.out.println(result - 1);
	}

	private static void dfs(int[][] graph, boolean[] visited, int n) {
		if (visited[n]) {
			return;
		}

		visited[n] = true;
		result++;

		for (int i = 0; i < graph[n].length; i++) {
			if (graph[n][i] == 1 && !visited[i]) {
				dfs(graph, visited, i);
			}
		}
	}
}