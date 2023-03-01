import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N, nonColorBlindness, colorBlindness;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 적록색약이 아닌 사람
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    nonColorBlindness++;
                }
            }
        }

        // 적록색약일 경우 빨강 == 초록이기 때문에 G를 R로 바꿔서 dfs 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }

        visited = new boolean[N][N];

        // 적록색약인 사람
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    colorBlindness++;
                }
            }
        }

        System.out.println(nonColorBlindness + " " + colorBlindness);
        br.close();
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isChecked(nr, nc) && !visited[nr][nc] && map[r][c] == map[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            return true;
        }
        return false;
    }
}