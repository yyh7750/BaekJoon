import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0;
		long[] distance = null;
		long[] city = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			distance = new long[n - 1];

			for (int i = 0; i < n - 1; i++) {
				distance[i] = Long.parseLong(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			city = new long[n];

			for (int i = 0; i < n; i++) {
				city[i] = Long.parseLong(st.nextToken());
			}

			System.out.println(getOilPrice(distance, city));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : getOilPrice
	 * @description : 최소 기름값 반환
	 * @param distance
	 * @param city
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.16
	 */
	public static long getOilPrice(long[] distance, long[] city) {

		// 처음 기름값 설정
		long minOilPrice = city[0];
		long result = 0;

		for (int i = 0; i < city.length - 1; i++) {
			// 현재 도시 기름값이 전 기름값보다 저렴하다면 업데이트
			if (city[i] < minOilPrice) {
				minOilPrice = city[i];
			}

			// 기름값 구하기
			result += distance[i] * minOilPrice;
		}

		return result;
	}
}