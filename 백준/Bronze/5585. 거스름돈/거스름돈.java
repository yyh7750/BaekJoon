import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 알고리즘.거스름돈
 * @description :
 * @author : Younghun Yu
 * @date : 2022.03.11
 *       =========================================================== DATE AUTHOR
 *       NOTE -----------------------------------------------------------
 *       2022.03.11 Younghun Yu 최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.11
	 */
	public static void main(String[] args) {

		BufferedReader br = null;
		// 지불해야 하는 금액
		int payment = 0;
		// 거스름돈 목록
		int[] changeArr = { 500, 100, 50, 10, 5, 1 };
		// 거스름돈
		int change = 0;
		// 거스름돈 갯수
		int changeCount = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));

			payment = Integer.parseInt(br.readLine());
			change = 1000 - payment;

			for (int i = 0; i < changeArr.length; i++) {
				if (change / changeArr[i] > 0) {
					changeCount += change / changeArr[i];
					change = change % changeArr[i];
				}
			}
			
			System.out.println(changeCount);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}