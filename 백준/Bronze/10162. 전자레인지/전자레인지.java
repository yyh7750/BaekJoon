import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.13
	 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;
		int a = 0, b = 0, c = 0;

		try {
			t = Integer.parseInt(br.readLine());

			if (t % 10 != 0) {
				System.out.println(-1);
			} //
			else {
				if (t >= 300) {
					a = t / 300;
					t = t % 300;
				} //
				if (t >= 60) {
					b = t / 60;
					t = t % 60;
				} //
				if (t >= 10) {
					c = t / 10;
					t = t % 10;
				}
				
				System.out.printf("%d %d %d", a, b, c);
			}
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		}
	}
}