import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, ans;
    private static int[][] map;
    private static Queue<Loc> Q;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        Q = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    Q.offer(new Loc(i, j));
                }
            }
        }

        bfs();

        boolean result = checkMap();
        if (ans == 0 && result) {
            System.out.println(0);
            return;
        }

        System.out.println(result ? ans - 1 : -1);
    }

    private static boolean checkMap() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void bfs() {
        while (!Q.isEmpty()) {
            Loc cur = Q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isChecked(nr, nc) && map[nr][nc] == 0) {
                    Q.offer(new Loc(nr, nc));
                    map[nr][nc] = map[cur.r][cur.c] + 1;
                    ans = Math.max(ans, map[nr][nc]);
                }
            }
        }
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < M && nc >= 0 && nc < N) {
            return true;
        }
        return false;
    }

    private static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
