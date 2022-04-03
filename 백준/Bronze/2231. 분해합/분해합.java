import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		String strN = null;
		int n = 0;
		int result = 0;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			strN = br.readLine();
			n = Integer.parseInt(strN);

			br.close();
			
			for (int i = n - (strN.length() * 9); i < n; i++) {
				int num = i;
				int sum = 0;

				while (num != 0) {
					sum += num % 10;
					num /= 10;
				}

				if (i + sum == n) {
					result = i;
					break;
				}
			}
			
			System.out.println(result);

		} //
		catch (IOException e) {
			System.out.println("입력 오류 발생 다시 시도해 주세요.");
		} //
		catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주세요.");
		}
	}
}