import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		divide(N, r, c);

		br.close();
		System.out.println(cnt);
	}

	private static void divide(int n, int r, int c) {
		if(n == 1) {
			return;
		}
		
		int half = n / 2;

		if (r < half && c < half) {
			cnt += (half * half) * 0;
			divide(half, r, c);
		} //
		else if (r < half && c >= half) {
			cnt += (half * half) * 1;
			divide(half, r, c - half);
		} //
		else if (r >= half && c < half) {
			cnt += (half * half) * 2;
			divide(half, r - half, c);
		} //
		else {
			cnt += (half * half) * 3;
			divide(half, r - half, c - half);
		} //
	}
}