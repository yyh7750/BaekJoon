import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.14
	 */
	public static void main(String[] args) {
		BufferedReader br = null;
		String num = null;
		int numLength = 0;
		int[] numCountArr = null;
		long total = 0;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			num = br.readLine();
			numLength = num.length();

			numCountArr = new int[numLength];

			for (int i = 0; i < numLength; i++) {
				// 숫자 검증 및 3의 배수인지 확인하기 위한 total에 더하여 3의 배수가 나오는지 확인
				int digit = Integer.parseInt(num.substring(i, i + 1));
				numCountArr[i] = digit;
				total += digit;
			}

			if (!num.contains("0") || total % 3 != 0) {
				System.out.println("-1");
				return;
			} //
			else {
				Arrays.sort(numCountArr);
				
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < numCountArr.length; i++) {
					sb.append(numCountArr[numCountArr.length - i -1]);
				}
				
				System.out.println(sb);
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