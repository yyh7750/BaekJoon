import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, K;
	private static int[] counting;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		counting = new int[100001];

		solution();

		br.close();
		System.out.println(N == K ? 0 : counting[K]);
	}

	private static void solution() {
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(N);

		while (!Q.isEmpty()) {
			int cur = Q.poll();

			if (cur - 1 >= 0 && cur - 1 < counting.length && counting[cur - 1] == 0) {
				counting[cur - 1] = counting[cur] + 1;
				Q.offer(cur - 1);
			}
			if (cur + 1 >= 0 && cur + 1 < counting.length && counting[cur + 1] == 0) {
				counting[cur + 1] = counting[cur] + 1;
				Q.offer(cur + 1);
			}
			if (cur * 2 >= 0 && cur * 2 < counting.length && counting[cur * 2] == 0) {
				counting[cur * 2] = counting[cur] + 1;
				Q.offer(cur * 2);
			}
		}
	}
}