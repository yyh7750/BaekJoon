import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			System.out.println(lcm(a, b));
		}
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int tmp = a % b;

			a = b;
			b = tmp;
		}

		return a;
	}

	private static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}