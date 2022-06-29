import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 단계별_문제풀이.입출력과_사칙연산
 * @description : 세 자리의 두 수를 곱할때 곱하기 각 자리수를 모두 출력하고 결과를 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.06.29
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.06.29  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());

		br.close();

		int result = a * b;

		while (b > 0) {
			int temp = b % 10;

			sb.append(a * temp).append("\n");
			b /= 10;
		}

		sb.append(result);
		System.out.println(sb);
	}
}