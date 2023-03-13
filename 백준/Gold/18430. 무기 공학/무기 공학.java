import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, ans;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /**
         * 현재 좌표를 기준으로 부메랑을 만들 수 있으면 만들어보고,
         * 다른 좌표에서 또 부메랑이 만들어지는지 탐색해본다.. 완탐.....
         */

        dfs(0, 0, 0);

        System.out.println(ans);
        br.close();
    }

    /**
     * 현재 좌표 기준으로 오른쪽으로 계속 이동(c 증가),
     * 맨 오른쪽(M)에 도달했다면 r 증가 후 c는 0으로 초기화
     * 각 위치에서 만든 부메랑 더해준다.
     * 이러면 완탐이잖아
     *
     * @param r
     * @param c
     * @param sum
     */
    private static void dfs(int r, int c, int sum) {

        if (r == N) {
            ans = Math.max(ans, sum);
            return;
        }

        if (c == M) {
            c = 0;
            r++;
        }

        // 부메랑 모양
        // {{0, -1}, {1, 0}}, {{0, -1}, {-1, 0}}, {{-1, 0}, {0, 1}}, {{1, 0}, {0, 1}}
        // 현재좌표 방문처리는 부메랑을 만들 수 있을 경우에만 해준다.

        if (isChecked(r, c - 1) && isChecked(r + 1, c) && !visited[r][c] && !visited[r][c - 1] && !visited[r + 1][c]) {
            visited[r][c] = true;
            visited[r][c - 1] = true;
            visited[r + 1][c] = true;
            dfs(r, c + 1, sum + map[r][c] * 2 + map[r][c - 1] + map[r + 1][c]);
            visited[r][c] = false;
            visited[r][c - 1] = false;
            visited[r + 1][c] = false;
        }
        if (isChecked(r, c - 1) && isChecked(r - 1, c) && !visited[r][c] && !visited[r][c - 1] && !visited[r - 1][c]) {
            visited[r][c] = true;
            visited[r][c - 1] = true;
            visited[r - 1][c] = true;
            dfs(r, c + 1, sum + map[r][c] * 2 + map[r][c - 1] + map[r - 1][c]);
            visited[r][c] = false;
            visited[r][c - 1] = false;
            visited[r - 1][c] = false;
        }
        if (isChecked(r - 1, c) && isChecked(r, c + 1) && !visited[r][c] && !visited[r - 1][c] && !visited[r][c + 1]) {
            visited[r][c] = true;
            visited[r - 1][c] = true;
            visited[r][c + 1] = true;
            dfs(r, c + 1, sum + map[r][c] * 2 + map[r - 1][c] + map[r][c + 1]
            );
            visited[r][c] = false;
            visited[r - 1][c] = false;
            visited[r][c + 1] = false;
        }
        if (isChecked(r + 1, c) && isChecked(r, c + 1) && !visited[r][c] && !visited[r + 1][c] && !visited[r][c + 1]) {
            visited[r][c] = true;
            visited[r + 1][c] = true;
            visited[r][c + 1] = true;
            dfs(r, c + 1, sum + map[r][c] * 2 + map[r + 1][c] + map[r][c + 1]);
            visited[r][c] = false;
            visited[r + 1][c] = false;
            visited[r][c + 1] = false;
        }

        dfs(r, c + 1, sum);
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            return true;
        }
        return false;
    }
}