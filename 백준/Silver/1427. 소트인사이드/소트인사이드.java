import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * @packageName : 단계별_문제풀이.정렬
 * @description : 입력받은 숫자 n을 정렬하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.04.16
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.04.16  Younghun Yu  최초 생성
 */
public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		String n = null;
		String[] nArr = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = br.readLine();
			nArr = new String[n.length()];

			nArr = n.split("");
			Arrays.sort(nArr, Collections.reverseOrder());

			System.out.println(String.join("", nArr));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
			System.out.println(ioException.getMessage());
		}
	}
}