import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Loc {
		int r, c;

		public Loc(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	private static int N, Ans;
	// 미는 순서 : 상, 하, 좌, 우 -> 검사 순서는 반대 : 하, 상, 우, 좌
	private static int[] dr = { 1, -1, 0, 0 };
	private static int[] dc = { 0, 0, 1, -1 };
	// 행, 열 별로 이동하면서 밀때 필요한 방향 배열
	// 상/하 : 우, 좌/우 : 하
	private static int[] ddr = { 0, 0, 1, 1 };
	private static int[] ddc = { 1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[][] board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		back(0, board);
		
		br.close();
		System.out.println(Ans);
	}

	private static void back(int idx, int[][] board) {
		if (idx == 5) {
			// 배열에서 제일 큰 값을 출력
			int maxValue = getMaxValueFromBoard(board);
			Ans = Math.max(Ans, maxValue);
			return;
		}

		// 상, 하, 좌, 우 > dir = 0, 1, 2, 3
		for (int dir = 0; dir < 4; dir++) {
			// 복사할 배열 생성 -> 백트래킹 함수에 보내준다.
			int[][] copyBoard = new int[N][N];
			for (int i = 0; i < N; i++) {
				copyBoard[i] = Arrays.copyOf(board[i], N);
			}

			// 복사 배열로 기울이기 연산
			tilt(copyBoard, dir);

			back(idx + 1, copyBoard);
		}
	}

	private static void tilt(int[][] board, int dir) {
		// 미는 방향의 반대 방향으로 탐색을 시작해 값이 존재한다면 밑으로 내려준다.
		// 옮긴 값과 다음 옮길 값의 값이 같을 경우 합쳐준다.
		// 옮기기 위해 미는 방향의 반대 방향을 시작점으로 잡아준다. (4개)
		Loc[] startLoc = { //
				new Loc(0, 0), //
				new Loc(N - 1, 0), //
				new Loc(0, 0), //
				new Loc(0, N - 1) //
		};

		// 방향에 따라 행, 열 별로 한 칸씩 이동하면서 배열을 옮겨준다.
		for (int line = 0; line < N; line++) {
			int r = startLoc[dir].r + ddr[dir] * line;
			int c = startLoc[dir].c + ddc[dir] * line;

			Loc lineStartLoc = new Loc(r, c);
			tiltLine(board, dir, lineStartLoc);
		}
	}

	private static void tiltLine(int[][] board, int dir, Loc lineStartLoc) {
		// 맵의 값을 담는 큐
		Queue<Integer> blockQ = new LinkedList<>();

		// 한칸씩 찾아서 큐에 담는다 -> 맵의 값을 0으로 초기화 해준다.
		for (int cell = 0; cell < N; cell++) {
			int r = lineStartLoc.r + dr[dir] * cell;
			int c = lineStartLoc.c + dc[dir] * cell;

			// 블록을 담는다.
			if (board[r][c] > 0) {
				blockQ.offer(board[r][c]);
				board[r][c] = 0;
			}
		}

		// 반대방향부터 차례로 놓는다. -> 옮긴값과 다음값이 같다면 합쳐준다.
		for (int cell = 0; cell < N; cell++) {
			if (blockQ.isEmpty()) {
				break;
			}

			int r = lineStartLoc.r + dr[dir] * cell;
			int c = lineStartLoc.c + dc[dir] * cell;

			int prevBlock = blockQ.poll();
			int resultBlock = prevBlock;
			
			if (!blockQ.isEmpty()) {
				int nextBlock = blockQ.peek();
			
				// 같다면 합친다.
				if(prevBlock == nextBlock) {
					resultBlock = prevBlock + nextBlock;
					blockQ.poll();
				}
			}
			
			board[r][c] = resultBlock;
		}
	}

	private static int getMaxValueFromBoard(int[][] board) {
		int maxValue = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				maxValue = Math.max(maxValue, board[i][j]);
			}
		}

		return maxValue;
	}
}