import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * @methodName : main
	 * @description : 백준 알고리즘 - 잃어버린 괄호 문제풀이
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.10
	 */
	public static void main(String[] args) {

		// 계산식 입력을 위한 BufferedReader
		BufferedReader br = null;
		// BufferedReader로 입력받은 계산식
		String formula = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			formula = br.readLine();

			// 알고리즘 풀이
			// +, -만 있는 계산식에서 적절히 괄호를 이용하여 최솟값을 나타낼 수 있는 방법은
			// 덧셈으로 만든 가장 큰 숫자를 마지막에 뺄셈으로 처리해주면 된다.
			// 따라서 - 연산을 식에서 분리시켜 덧셈끼리 먼저 더하면 최솟값을 구할 수 있다.
			System.out.println(getMinFormula(formula));

			br.close();
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		}
	}

	/**
	 * @methodName : getMinFormula
	 * @description : 계산식에서 최솟값을 구하기 위한 메소드
	 * @param formula : 계산식
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.10
	 */
	public static int getMinFormula(String formula) {

		// - 식을 분리시켜줄 StringTokenizer
		StringTokenizer subtract = null;
		// + 식을 분리시켜줄 StringTokenizer
		StringTokenizer plus = null;

		// - 연산을 기준으로 분리
		subtract = new StringTokenizer(formula, "-");

		// + 연산이 없는 경우
		if (!formula.contains("+")) {
			if (!formula.contains("-")) {
				return Integer.parseInt(formula);
			} //
			else {
				int subtractFormula = Integer.parseInt(subtract.nextToken());

				while (subtract.hasMoreTokens()) {
					subtractFormula -= Integer.parseInt(subtract.nextToken());
				}

				return subtractFormula;
			}
		} //
		else {
			// 결과값
			int result = Integer.MAX_VALUE;

			while (subtract.hasMoreTokens()) {

				// + 연산을 처리하기 위해 한번 더 분리해준다.
				plus = new StringTokenizer(subtract.nextToken(), "+");

				// 덧셈 연산을 저장하기 위한 int변수 plusFormula
				int plusFormula = 0;
				// 덧셈연산 진행
				while (plus.hasMoreTokens()) {
					plusFormula += Integer.parseInt(plus.nextToken());
				}

				// 초기값 저장
				if (result == Integer.MAX_VALUE) {
					result = plusFormula;
				}
				// 덧셈 연산이 끝났으니 뺄셈으로 최솟값 구하기
				else {
					result -= plusFormula;
				}
			}

			return result;
		}
	}
}