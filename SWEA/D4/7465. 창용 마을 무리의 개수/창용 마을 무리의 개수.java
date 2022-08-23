import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int N, M;
	private static int[] town;
	private static int[] rank;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			town = new int[N + 1];
			makeSet();
			rank = new int[N + 1];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				unionSet(parent, child);
			}

			int cnt = 0;
			for (int i = 1; i < N + 1; i++) {
				if (town[i] == i) {
					cnt++;
				}
			}

			sb.append(cnt).append("\n");
		}

		br.close();
		System.out.println(sb);
	}

	private static void unionSet(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);

		if (rank[pi] > rank[pj]) {
			town[pj] = pi;
		} //
		else {
			town[pi] = pj;
			if (rank[pi] == rank[pj]) {
				rank[pj]++;
			}
		}
	}

	private static int findSet(int i) {
		if (town[i] == i) {
			return i;
		}
		return town[i] = findSet(town[i]);
	}

	private static void makeSet() {
		for (int i = 0; i < town.length; i++) {
			town[i] = i;
		}
	}
}