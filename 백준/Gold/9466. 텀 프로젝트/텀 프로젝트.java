import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int[] arr;
	private static boolean[] team, visited;
	private static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n + 1];
			team = new boolean[n + 1];
			visited = new boolean[n + 1];

			cnt = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

				// 혼자 팀인 경우
				if (arr[i] == i) {
					cnt++;
					team[i] = true;
				}
			}

			for (int i = 1; i <= n; i++) {
				if (!team[i]) {
					dfs(i);
				}
			}

			sb.append(n - cnt).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void dfs(int cur) {
		if (team[cur]) {
			return;
		}

		// 사이클 완성 팀
		if (visited[cur]) {
			team[cur] = true;
			cnt++;
		}

		// 사이클을 이루는지 확인하기 위한 방문배열
		visited[cur] = true;

		int next = arr[cur];
		if (!team[next]) {
			dfs(next);
		}

		// 사이클에 포함되지 않은 팀 체크
		team[cur] = true;
		visited[cur] = false;
	}
}