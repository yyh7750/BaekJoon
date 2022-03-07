import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @packageName : 알고리즘.동전
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.03.07
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.03.07  Younghun Yu  최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.07
	 */
	public static void main(String[] args) {

		int n = 0;
		int k = 0;
		int[] coinArr = null;
		int result = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			coinArr = new int[n];

			for (int i = 0; i < n; i++) {
				coinArr[i] = Integer.parseInt(br.readLine());
			}

			for (int i = coinArr.length - 1; i >= 0; i--) {
				result += k / coinArr[i];

				if (k / coinArr[i] != 0) {
					k = k % coinArr[i];
				}

				if (k == 0) {
					break;
				}

			}
			
			System.out.println(result);
			
			br.close();
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}