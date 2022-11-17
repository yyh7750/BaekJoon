import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

	private static char[][] map;
	// 1~25 숫자를 담을 배열. 좌표값을 얻기 위한 처리
	private static int[] arr;
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };
	private static int ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];

		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}

		arr = new int[7];
		combination(0, 0, 0, 0);

		br.close();
		System.out.println(ans);
	}

	private static void combination(int idx, int k, int som, int yeon) {
		if (yeon >= 4) {
			return;
		}

		if (k == 7) {
			if (isCycle()) {
				ans++;
			}
			return;
		}

		for (int i = idx; i < 25; i++) {
			arr[k] = i;
			if (map[i / 5][i % 5] == 'S') {
				combination(i + 1, k + 1, som + 1, yeon);
			} //
			else {
				combination(i + 1, k + 1, som, yeon + 1);
			}
		}
	}

	private static boolean isCycle() {
		Queue<Integer> Q = new LinkedList<>();
		Q.offer(arr[0]);

		Set<Integer> combResult = new HashSet<>();
		for (int i = 1; i < 7; i++) {
			combResult.add(arr[i]);
		}

		int cnt = 1;

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur / 5 + dr[d];
				int nc = cur % 5 + dc[d];
				int next = nr * 5 + nc;

				if (isChecked(nr, nc)) {
					if (combResult.contains(next)) {
						Q.offer(next);
						combResult.remove(next);
						cnt++;
					}
				}
			}
		}

		if (cnt == 7) {
			return true;
		}
		return false;
	}

	private static boolean isChecked(int nr, int nc) {
		if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
			return true;
		}
		return false;
	}
}