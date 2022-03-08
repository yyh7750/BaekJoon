import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @packageName : 알고리즘.회의실배정
 * @description : 
 * @author : Younghun Yu
 * @date : 2022.03.08
 * ===========================================================
 *     DATE      AUTHOR      NOTE
 * -----------------------------------------------------------
 * 2022.03.08  Younghun Yu  최초 생성
 */
public class Main {

	/**
	 * @methodName : main
	 * @description :
	 * @param args
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.08
	 */
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int conferenceNum = 0;
		int[][] schedule = null;

		try {

			// 회의 수 입력
			conferenceNum = Integer.parseInt(br.readLine());

			schedule = new int[conferenceNum][2];

			// 회의실 사용표 입력
			for (int i = 0; i < conferenceNum; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < 2; j++) {
					schedule[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println(getUseCount(conferenceNum, schedule));

		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	public static int getUseCount(int n, int[][] schedule) {

		int count = 0;
		int prevEndTime = 0;

		// 종료 시간에 따라 정렬시켜준다. 2차원 배열 정렬을 위한 Comparator
		Arrays.sort(schedule, new Comparator<int[]>() {

			// 종료시간 오름차순 정렬
			@Override
			public int compare(int[] o1, int[] o2) {

				// 종료 시간이 같으면 시작 시간으로 정렬
				if (o1[1] == o2[1]) {
					return o1[0] - o2[0];
				} //
				else {
					return o1[1] - o2[1];
				}
			}
		});
		
		for (int i = 0; i < n; i++) {
			// 직전 종료시간이 다음 회의 시작 시간보다 작거나 같다면 카운트 추가(회의실 사용 횟수 추가)
			if (prevEndTime <= schedule[i][0]) {
				prevEndTime = schedule[i][1];
				count++;
			}
		}

		return count;
	}
}