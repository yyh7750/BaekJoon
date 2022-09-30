import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int N, Ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			Ans = 0;
			N = Integer.parseInt(br.readLine());
			recursive(0);
			System.out.println(Ans);
		}
		
		br.close();
	}

	private static void recursive(int sum) {
		if (sum == N) {
			Ans++;
			return;
		}

		if (sum > N) {
			return;
		}

		recursive(sum + 1);
		recursive(sum + 2);
		recursive(sum + 3);
	}
}