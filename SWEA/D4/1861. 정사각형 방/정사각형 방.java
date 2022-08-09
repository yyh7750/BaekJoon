import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

	private static int[][] map;
	private static int N;
	private static int cnt, max;
	private static Map<Integer, Integer> coordinate;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			max = 0;
			coordinate = new HashMap<>();

			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) {
					cnt = 1;
					int cur = map[c][r];
					solution(c, r, cur);
					
					max = Math.max(max, cnt);
					coordinate.put(map[c][r], cnt);
				}
			}
			
			int start = Integer.MAX_VALUE;
			for(int key : coordinate.keySet()) {
				if(coordinate.get(key) == max) {
					start = Math.min(start, key);
				}
			}

			sb.append(start).append(" ").append(max).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void solution(int c, int r, int cur) {
		int[] dc = { -1, 1, 0, 0 };
		int[] dr = { 0, 0, -1, 1 };

		for (int d = 0; d < 4; d++) {
			int nc = c + dc[d];
			int nr = r + dr[d];

			if (isChecked(nc, nr) && map[nc][nr] - 1 == cur) {
				cnt++;
				solution(nc, nr, map[nc][nr]);
				break;
			}
		}
	}

	private static boolean isChecked(int c, int r) {
		if (c >= 0 && c < N && r >= 0 && r < N) {
			return true;
		}
		return false;
	}
}