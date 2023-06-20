import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Egg {
		int dura;
		int weight;

		public Egg(int dura, int weight) {
			super();
			this.dura = dura;
			this.weight = weight;
		}
	}

	private static Egg[] eggArr;
	private static int N, result;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		eggArr = new Egg[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int dura = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			eggArr[i] = new Egg(dura, weight);
		}

		recursive(0, 0);

		br.close();
		System.out.println(result);
	}

	private static void recursive(int idx, int cnt) {
		if (idx == N) {
			result = Math.max(result, cnt);
			return;
		}
		if (cnt == N - 1) {
			recursive(idx + 1, cnt);
			return;
		}
		if (eggArr[idx].dura <= 0) {
			recursive(idx + 1, cnt);
			return;
		}

		int tmp = 0;
		for (int i = 0; i < N; i++) {
			if (idx == i) {
				continue;
			}
			if (eggArr[i].dura <= 0) {
				continue;
			}

			tmp = hit(idx, i);

			recursive(idx + 1, cnt + tmp);

			backtracking(idx, i);
		}
	}

	private static int hit(int idx, int i) {
		int cnt = 0;

		eggArr[idx].dura -= eggArr[i].weight;
		eggArr[i].dura -= eggArr[idx].weight;

		if (eggArr[idx].dura <= 0) {
			cnt++;
		}
		if (eggArr[i].dura <= 0) {
			cnt++;
		}

		return cnt;
	}

	private static void backtracking(int idx, int i) {
		eggArr[idx].dura += eggArr[i].weight;
		eggArr[i].dura += eggArr[idx].weight;
	}
}