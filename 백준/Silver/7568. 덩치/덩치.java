import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		BufferedReader br = null;
		StringTokenizer st = null;
		int n = 0; // 전체 사람 수
		int[][] arr = null; // 각 사람의 키, 몸무게를 담기위한 2차원 배열
		StringBuilder sb = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			arr = new int[n][2];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());

				while (st.hasMoreTokens()) {
					arr[i][0] = Integer.parseInt(st.nextToken()); // 몸무게 정보
					arr[i][1] = Integer.parseInt(st.nextToken()); // 키 정보
				}
			}
            
            br.close();

			sb = new StringBuilder();

			for (int i = 0; i < n; i++) {
				
				int rank = 1; // 덩치 순위 - 1부터 시작

				for (int j = 0; j < n; j++) {
					// 둘다 클 경우에만 순위가 올라감
					if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
						rank++;
					} //
					else {
						continue;
					}
				}
				sb.append(rank);
				sb.append(" ");
			}
			
			System.out.println(sb);

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}
}