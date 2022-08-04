import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static int H, W;
	private static int sr, sc;
	private static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				char[] arr = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					map[i][j] = arr[j];
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						sc = i;
						sr = j;
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			char[] command = new char[N];
			command = br.readLine().toCharArray();
			solution(command, sr, sc);

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}

		br.close();
		System.out.println(sb);
	}

	private static void solution(char[] command, int sr, int sc) {
		char look = map[sc][sr];

		for (int c = 0; c < command.length; c++) {

			switch (command[c]) {

			case 'S':
				shot(sc, sr);
				break;

			case 'U':
				look = '^';
				map[sc][sr] = look;
				if (isChecked(sc - 1, sr)) {
					if (map[sc - 1][sr] != '-' && map[sc - 1][sr] != '*' && map[sc - 1][sr] != '#') {
						map[sc][sr] = '.';
						map[--sc][sr] = '^';
					}
				}
				break;

			case 'D':
				look = 'v';
				map[sc][sr] = look;
				if (isChecked(sc + 1, sr)) {
					if (map[sc + 1][sr] != '-' && map[sc + 1][sr] != '*' && map[sc + 1][sr] != '#') {
						map[sc][sr] = '.';
						map[++sc][sr] = look;
					}
				}
				break;

			case 'L':
				look = '<';
				map[sc][sr] = look;
				if (isChecked(sc, sr - 1)) {
					if (map[sc][sr - 1] != '-' && map[sc][sr - 1] != '*' && map[sc][sr - 1] != '#') {
						map[sc][sr] = '.';
						map[sc][--sr] = look;
					}
				}
				break;

			case 'R':
				look = '>';
				map[sc][sr] = look;
				if (isChecked(sc, sr + 1)) {
					if (map[sc][sr + 1] != '-' && map[sc][sr + 1] != '*' && map[sc][sr + 1] != '#') {
						map[sc][sr] = '.';
						map[sc][++sr] = look;
					}
				}
				break;
			}
		}
	}

	// 평지, 물일 경우 0, 벽을 맞추면 1
	private static void shot(int c, int r) {
		int[] dc = { -1, 1, 0, 0 };
		int[] dr = { 0, 0, -1, 1 };

		int nc = c;
		int nr = r;
		while (true) {
			if (map[c][r] == '^') {
				nc += dc[0];
				nr += dr[0];
			} //
			else if (map[c][r] == 'v') {
				nc += dc[1];
				nr += dr[1];
			} //
			else if (map[c][r] == '<') {
				nc += dc[2];
				nr += dr[2];
			} //
			else if (map[c][r] == '>') {
				nc += dc[3];
				nr += dr[3];
			}

			if (isChecked(nc, nr)) {
				if (map[nc][nr] == '#') {
					break;
				} //
				else if (map[nc][nr] == '*') {
					map[nc][nr] = '.';
					break;
				}
			} //
			else {
				break;
			}
		}
	}

	private static boolean isChecked(int c, int r) {
		if (c >= 0 && c < H && r >= 0 && r < W) {
			return true;
		}
		return false;
	}
}