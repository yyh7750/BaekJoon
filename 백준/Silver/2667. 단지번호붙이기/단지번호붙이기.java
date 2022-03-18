import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	// visit check 배열
	static boolean[][] visited = null;
	// 단지 수 카운트
	static int count = 0;
	
	public static void main(String[] args) {
		BufferedReader br = null;

		// 지도의 크기
		int n = 0;
		// 지도
		int[][] map = null;

		// 출력할 List
		List<Integer> list = null;

		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visited = new boolean[n][n];

			// 지도 입력
			for (int i = 0; i < map.length; i++) {
				String st = br.readLine();
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = st.charAt(j) - '0';
				}
			}

			list = new ArrayList<>();

			// 검사 시작
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					// 위치값이 1 && 방문체크가 true일 경우 탐색 시작
					if (map[i][j] == 1 && !visited[i][j]) {
						count = 1;
						list.add(dfs(map, i, j));
					}
				}
			}

			// 정렬 후 출력
			Collections.sort(list);
			System.out.println(list.size());
			for (int i : list) {
				System.out.println(i);
			}
		} //
		catch (IOException ioException) {
			System.out.println("입력 오류. 다시 실행해주세요.");
		} //
		catch (NumberFormatException numberException) {
			System.out.println("숫자를 입력해주세요. 다시 실행해주세요.");
		}
	}

	/**
	 * @description : 단지 수 카운트를 반환하기 위한 재귀함수
	 * @return 단지 개수
	 * @param map
	 * @param x
	 * @param y
	 * @return
	 *
	 * @author : Younghun Yu
	 * @date : 2022.03.18
	 */
	public static int dfs(int[][] map, int x, int y) {
		// 좌우
		int[] dx = { -1, 1, 0, 0 };
		// 상하
		int[] dy = { 0, 0, -1, 1 };

		// 방문 체크
		visited[x][y] = true;

		// 탐색 시작 - 사방으로 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < map.length && ny < map.length && nx >= 0 && ny >= 0) {
				if (map[nx][ny] == 1 && !visited[nx][ny]) {
					count++;
					dfs(map, nx, ny);
				}
			}
		}

		return count;
	}
}