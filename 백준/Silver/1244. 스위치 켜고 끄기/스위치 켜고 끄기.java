import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st = null;
	private static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());

		solution(M);

		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		for (int i : arr) {
			cnt++;
			if (cnt % 20 == 0) {
				sb.append(i).append("\n");
			} //
			else {
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);
	}

	private static void solution(int M) throws IOException {
		if (M <= 0) {
			return;
		}

		st = new StringTokenizer(br.readLine());
		int gender = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());

		if (gender == 1) {
			mSolution(num);
		} //
		else {
			wSolution(num);
		}

		solution(--M);
	}

	private static void mSolution(int n) {
		for (int i = 0; i < arr.length; i++) {
			if ((i + 1) % n == 0) {
				arr[i] = arr[i] == 0 ? 1 : 0;
			}
		}
	}

	private static void wSolution(int n) {
		int cur = n - 1;
		int pre = cur - 1;
		int next = cur + 1;

		while (pre >= 0 && next < arr.length) {
			if (arr[pre] == arr[next]) {
				pre--;
				next++;
			} //
			else {
				break;
			}
		}
		
		pre++;
		next--;

		for (int i = pre; i <= next; i++) {
			arr[i] = arr[i] == 0 ? 1 : 0;
		}
	}
}