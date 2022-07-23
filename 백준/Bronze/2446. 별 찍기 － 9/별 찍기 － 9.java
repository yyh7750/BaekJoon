import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			for (int j = N - i; j < N; j++) {
				System.out.print(" ");
			}
			for (int j = i * 2; j < N * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i = 0; i < N - 1; i++) {
			for (int j = N - i - 2; j > 0; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i * 2 + 2; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}