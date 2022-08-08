import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		sb.append("<");
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			q.add(i);
		}

		while (true) {
			for (int i = 0; i < K - 1; i++) {
				int offer = q.poll();
				q.offer(offer);
			}

			if (q.size() > 1) {
				sb.append(q.poll()).append(", ");
			} //
			else {
				sb.append(q.poll()).append(">");
				break;
			}
		}

		br.close();
		System.out.println(sb);
	}
}