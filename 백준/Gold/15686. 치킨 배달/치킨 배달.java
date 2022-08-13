import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static class Loc {
		int r, c;

		Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int N, M;
	private static int result = Integer.MAX_VALUE;
	private static List<Loc> home = new ArrayList<>();
	private static List<Loc> chicken = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1)
					home.add(new Loc(r, c));
				if (n == 2)
					chicken.add(new Loc(r, c));
			}
		}

		combination(0, 0, new int[M]);

		br.close();
		System.out.println(result);
	}

	private static void combination(int idx, int k, int[] sel) {
		if (k == M) {
			int sum = 0;
			for (int i = 0; i < home.size(); i++) {
				int dist = Integer.MAX_VALUE;
				for (int j = 0; j < M; j++) {
					dist = Math.min(dist, calc(home.get(i), chicken.get(sel[j])));
				}
				sum += dist;
			}
			
			result = Math.min(result, sum);
			return;
		}
		if (idx == chicken.size()) {
			return;
		}
		
		sel[k] = idx;
		combination(idx + 1, k + 1, sel);
		combination(idx + 1, k, sel);
	}

	private static int calc(Loc loc1, Loc loc2) {
		return Math.abs(loc1.r - loc2.r) + Math.abs(loc1.c - loc2.c);
	}
}