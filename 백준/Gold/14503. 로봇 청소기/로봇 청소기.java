import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 로봇청소기_14503
 * author         : yyh77
 * date           : 2024-01-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-10        yyh77       최초 생성
 */
public class Main {

    private static int[][] map;
    private static int N, M;
    private static int ans = 1;

    // 북, 동, 남, 서
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        System.out.println(ans);
        br.close();
    }

    /**
     * @param r : 현재 좌표 r
     * @param c : 현재 좌표 c
     * @param d : 현재 바라보는 방향
     */
    private static void dfs(int r, int c, int d) {

        map[r][c] = -1;

        // 사방 탐색하면서 청소할 곳 찾으면 90도로 돌아 전진
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 청소하지 않은 경우 가서 청소
            if (isChecked(nr, nc) && map[nr][nc] == 0) {
                dfs(nr, nc, d);
                ans++;
                return;
            }
        }

        // 청소할 곳이 없을 경우 벽 확인 후 후진
        int b = (d + 2) % 4;
        int nr = r + dr[b];
        int nc = c + dc[b];

        if (isChecked(nr, nc) && map[nr][nc] != 1) {
            dfs(nr, nc, d);
        }
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr < N && nr >= 0 && nc < M && nc >= 0) {
            return true;
        }
        return false;
    }
}
