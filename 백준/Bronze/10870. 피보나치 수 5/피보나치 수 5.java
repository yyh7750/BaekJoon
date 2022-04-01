import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		int n = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());

			System.out.println(fibonacci(n));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	private static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		} //
		else if (n == 1) {
			return 1;
		} //
		else {
			return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}
}