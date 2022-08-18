import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	private static int R, C;
	private static int result = 1;
	private static int cnt = 1;
	private static char[][] map;
	private static Map<Character, Boolean> visited;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new HashMap<Character, Boolean>();

		for (int i = 0; i < R; i++) {
			String alphabet = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = alphabet.charAt(j);
				visited.put(map[i][j], false);
			}
		}

		visited.put(map[0][0], true);
		dfs(0, 0);

		System.out.println(result);
	}

	private static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isChecked(nr, nc) && !visited.get(map[nr][nc])) {
				visited.put(map[nr][nc], true);
				cnt++;
				dfs(nr, nc);
				result = Math.max(cnt, result);
				visited.put(map[nr][nc], false);
				cnt--;
			}
		}

	}

	private static boolean isChecked(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C) {
			return true;
		}
		return false;
	}
}