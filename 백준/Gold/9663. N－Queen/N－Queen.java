import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 알고리즘.N_Queen
 * @description : 서로 다른 두 퀸이 공격하지 못하게 놓는 경우의 수를 출력하는 프로그램
 * @author : Younghun Yu
 * @date : 2022.04.21
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.04.21  Younghun Yu  최초 생성
 */
public class Main {

	private static int n;
	private static int[] chess;
	private static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		chess = new int[n];
		count = 0;

		nQueen(0);

		sb.append(count).append("\n");

		br.close();
		System.out.println(sb);
	}

	/**
	 * @methodName : nQueen
	 * @description : 퀸을 놓을 수 있는 경우의 수를 구하는 메도스
	 * @param row
	 *
	 * @author : Younghun Yu
	 * @date : 2022.04.21
	 */
	private static void nQueen(int row) {

		// 한 행마다 퀸이 있다면 count 증가
		if (row == n) {
			count++;
			return;
		}

		for (int i = 0; i < n; i++) {
			chess[row] = i;
			// 퀸을 놓을 수 있는지 체크
			if (isChecked(row)) {
				// 놓을 수 있다면 다음 행 진행
				nQueen(row + 1);
			}
		}
	}

	/**
	 * @methodName : isChecked
	 * @description : 퀸을 놓을 수 있는지 체크하는 메소드
	 * @param col
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.04.21
	 */
	private static boolean isChecked(int col) {

		for (int i = 0; i < col; i++) {
			// 같은 선상에 있다면 퀸을 놓을 수 없는 자리다.
			if (chess[col] == chess[i]) {
				return false;
			} // 대각선 상에 있어도 퀸을 놓을 수 없다.
			if (Math.abs(col - i) == Math.abs(chess[i] - chess[col])) {
				return false;
			}
		}

		return true;
	}
}