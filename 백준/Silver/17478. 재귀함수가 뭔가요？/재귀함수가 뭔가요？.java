import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @packageName : 단계별_문제풀이.재귀
 * @description : 원하는 재귀 횟수를 입력하여 출력의 반복을 나타내는 프로그램
 * @author : Younghun Yu
 * @date : 2022.05.31
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.05.31  Younghun Yu  최초 생성
 */
public class Main {

	private static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		sb = new StringBuilder();
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");

		int count = 0;
		recursion(n, count);

		System.out.println(sb);
		br.close();
	}

	private static void recursion(int n, int count) {
		StringBuilder underBar = createUnderBar(count);

		if (n == 0) {
			sb.append(underBar).append("\"재귀함수가 뭔가요?\"\n");
			sb.append(underBar).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			sb.append(underBar).append("라고 답변하였지.\n");
			return;
		} //
		else {
			sb.append(underBar).append("\"재귀함수가 뭔가요?\"\n");
			sb.append(underBar).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			sb.append(underBar).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			sb.append(underBar).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
			recursion(n - 1, count + 1);
			sb.append(underBar).append("라고 답변하였지.\n");
		}
	}

	private static StringBuilder createUnderBar(int count) {
		StringBuilder underBar = new StringBuilder();

		if (count > 0) {
			for (int i = 0; i < count; i++) {
				underBar.append("____");
			}

			return underBar;
		} //
		else {
			sb.append("");
			return underBar;
		}
	}
}