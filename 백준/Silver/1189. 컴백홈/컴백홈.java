import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.silver1
 * fileName       : 컴백홈_1189
 * author         : yyh77
 * date           : 2023-07-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-18        yyh77       최초 생성
 */
public class Main {

    private static int R, C, K, ans;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[R - 1][0] = true;
        dfs(R - 1, 0, 1);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int r, int c, int cnt) {

        if (r == 0 && c == C - 1) {
            if (cnt == K) {
                ans++;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'T') {
                visited[nr][nc] = true;
                dfs(nr, nc, cnt + 1);
                visited[nr][nc] = false;
            }
        }
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
            return true;
        }
        return false;
    }
}
