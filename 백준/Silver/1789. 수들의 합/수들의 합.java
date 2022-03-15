import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		long s = 0;
		long n = 0;
		int count = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			s = Long.parseLong(br.readLine());

			for (long i = 1; i <= s; i++) {
				if (s < n) {
					break;
				} //
				else {
					n += i;
					++count;
				}
			}
			
			System.out.println(s == n ? count : count - 1);

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