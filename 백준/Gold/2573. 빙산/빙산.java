import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold4
 * fileName       : 빙산_2573
 * author         : yyh77
 * date           : 2023-07-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-24        yyh77       최초 생성
 */
public class Main {

    private static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());

        br.close();
    }

    /**
     * 빙산의 개수를 확인하며 2개 이상일 경우 개수 리턴
     * 전부 다 녹을 때까지 2개 이상 분리되지 않는다면 0 리턴
     *
     * @return
     */
    private static int solution() {

        int iceberg = 0, cnt = 0;

        while (true) {

            iceberg = getIceberg();

            if (iceberg >= 2) {
                break;
            }
            else if (iceberg == 0) {
                return 0;
            }

            meltIceberg();
            cnt++;
        }

        return cnt;
    }

    /**
     * 빙산 개수 확인하는 메소드
     *
     * @return
     */
    private static int getIceberg() {

        boolean[][] visited = new boolean[N][M];

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    /**
     * 재귀를 통해 이어진 부분 끝까지 찾아 방문 표시하는 메소드
     *
     * @param r
     * @param c
     * @param visited
     */
    private static void dfs(int r, int c, boolean[][] visited) {

        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
                dfs(nr, nc, visited);
            }
        }
    }

    /**
     * 빙산 녹이는 메소드
     * 빙산이 있는 위치를 큐에 넣고 연산 진행
     */
    private static void meltIceberg() {

        Queue<Loc> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] > 0) {
                    q.offer(new Loc(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {

            Loc cur = q.poll();

            for (int d = 0; d < 4; d++) {

                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 빙산이 있던 위치(visited에 저장해놓은 위치)를 제외하고 다른 위치 개수만큼 감소시킨다
                if (isChecked(nr, nc) && !visited[nr][nc]) {
                    map[cur.r][cur.c] -= 1;
                }
            }
        }
    }

    /**
     * 배열 범위값 검사 메소드
     */
    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            return true;
        }
        return false;
    }
}
