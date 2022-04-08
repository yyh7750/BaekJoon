import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int a = 0, b = 0, c = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken()); // 고정 비용
			b = Integer.parseInt(st.nextToken()); // 가변 비용
			c = Integer.parseInt(st.nextToken()); // 판매 비용

			// 가변 비용이 판매 비용보다 작아야 이익이 창출된다.
			if (b >= c) {
				System.out.println(-1);
			} //
			else {
				// +1부터 이익이 창출되는 시점(손익분기점)
				System.out.println(a / (c - b) + 1);
			}

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}