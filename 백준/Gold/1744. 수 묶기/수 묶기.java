import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	private static int N, oneCnt, sum;
	// 높은 숫자 기준 정렬
	private static PriorityQueue<Integer> plusPQ = new PriorityQueue<>(Collections.reverseOrder());
	// 낮은 숫자 기준 정렬
	private static PriorityQueue<Integer> minusPQ = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num >= 2) {
				plusPQ.offer(num);
			} //
			else if (num <= 0) {
				minusPQ.offer(num);
			} //
			else {
				oneCnt++;
			}
		}

		solution();

		br.close();
		System.out.println(sum);
	}

	/**
	 * Description : +, -에 해당하는 우선순위큐를 분리하여 연산을 수행, 1에 해당하는 값은 모두 더해준다.
	 * 
	 * @return void
	 *
	 * @date 2022. 8. 28.
	 * @author 유영훈
	 */
	private static void solution() {
		while (plusPQ.size() > 1) {
			int a = plusPQ.poll();
			int b = plusPQ.poll();
			sum += a * b;
		}

		if (!plusPQ.isEmpty()) {
			sum += plusPQ.poll();
		}

		while (minusPQ.size() > 1) {
			int a = minusPQ.poll();
			int b = minusPQ.poll();
			sum += a * b;
		}

		if (!minusPQ.isEmpty()) {
			sum += minusPQ.poll();
		}

		for (int i = 0; i < oneCnt; i++) {
			sum += 1;
		}
	}
}