import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 알고리즘.설탕배달
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.03.06
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.03.06  Younghun Yu  최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description : 
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.06
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		int result = 0;

		try {
			n = Integer.parseInt(br.readLine());

			while (true) {
				if (n % 5 == 0) {
					result += n / 5;
					break;
				}

				n -= 3;
				result++;

				if (n < 0) {
					result = -1;
					break;
				}
			}
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}

		System.out.println(result);
	}
}