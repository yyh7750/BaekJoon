import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @packageName : 알고리즘.보물
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.03.09
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.03.09  Younghun Yu  최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.09
	 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = 0;
		int[] aArr = null;
		int[] bArr = null;

		try {

			n = Integer.parseInt(br.readLine());

			aArr = new int[n];
			bArr = new int[n];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < n; j++) {
					if (i == 0) {
						aArr[j] = Integer.parseInt(st.nextToken());
					} //
					else {
						bArr[j] = Integer.parseInt(st.nextToken());
					}
				}
			}

			System.out.println(getMinS(aArr, bArr));

			br.close();
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : getMinS
	 * @description : S = A[0] × B[0] + ... + A[N-1] × B[N-1] -> S의 최솟값을 구하기 위한 메소드.
	 *              A는 오름차순, B는 내림차순으로 정렬하여 계산하면 된다.
	 * @param aArr
	 * @param bArr
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.09
	 */
	public static int getMinS(int[] aArr, int[] bArr) {

		int s = 0;

		Arrays.sort(aArr);
		// 내림차순을 위한 sort(arr, Collections.reverseOrder())는 
		// wrapper class로 사용해야 하므로 성능 이슈가 있어 사용하지 않는다.
		Arrays.sort(bArr);

		for (int i = 0; i < aArr.length; i++) {
			s += aArr[i] * bArr[aArr.length - 1 - i];
		}

		return s;
	}
}