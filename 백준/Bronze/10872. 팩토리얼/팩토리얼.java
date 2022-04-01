import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @methodName : main
	 * @description : 팩토리얼 함수를 실행시키는 메인함수
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.04.01
	 */
	public static void main(String[] args) {

		BufferedReader br = null;
		int n = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());

			br.close();

			System.out.println(factorial(n));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : factorial
	 * @description : for문이 아닌 재귀함수로 팩토리얼을 구하는 메소드
	 * @param n
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.04.01
	 */
	private static int factorial(int n) {

		if (n == 0 || n == 1) {
			return 1;
		} //
		else {
			return n * factorial(n - 1);
		}
	}
}