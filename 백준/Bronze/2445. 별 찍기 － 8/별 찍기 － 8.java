import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			for (int j = N - i; j <= N; j++) {
				System.out.print("*");
			}
			for (int j = (i + 1) * 2; j < N * 2; j++) {
				System.out.print(" ");
			}
			for (int j = N - i; j <= N; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			for (int j = i * 2; j < N * 2; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}
}