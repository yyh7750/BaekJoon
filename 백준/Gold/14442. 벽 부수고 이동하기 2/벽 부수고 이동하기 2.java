import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold3
 * fileName       : 벽_부수고_이동하기_14442
 * author         : yyh77
 * date           : 2023-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-13        yyh77       최초 생성
 */
public class Main {

    private static class Loc {
        int r, c, cnt, chance;

        public Loc(int r, int c, int cnt, int chance) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.chance = chance;
        }
    }

    private static int N, M, K;
    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // 벽을 K개만큼 부술 수 있으므로 방문배열을 3차원 배열만큼 생성
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));

        br.close();
    }

    /**
     * 벽을 부수고 가는 경우와 부수고 가지 않는 경우 2가지로 나뉜다.
     * 최대 K개의 벽을 부술 수 있으므로 방문배열을 3차원으로 만들어 방문처리 해준다.
     * Loc 클래스에서 chance 변수가 벽을 부순 횟수로, 방문배열과 함꼐 비교해준다.
     *
     * @param sr
     * @param sc
     */
    private static int bfs(int sr, int sc) {

        Queue<Loc> Q = new LinkedList<>();
        Q.offer(new Loc(sr, sc, 1, 0));
        visited[sr][sc][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!Q.isEmpty()) {

            Loc cur = Q.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                return cur.cnt;
            }

            for (int d = 0; d < 4; d++) {

                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isChecked(nr, nc)) {

                    // 벽을 부수고 가는 경우
                    if (!visited[nr][nc][cur.chance] && map[nr][nc] == 0) {
                        Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.chance));
                        visited[nr][nc][cur.chance] = true;
                    }

                    // 벽을 부수고 가지 않는 경우
                    // 다음 벽을 부술 수 있는지 체크해야 하기 때문에 cur.chance + 1을 기준으로 체크해준다.
                    else if (cur.chance < K && !visited[nr][nc][cur.chance + 1] && map[nr][nc] == 1) {
                        Q.offer(new Loc(nr, nc, cur.cnt + 1, cur.chance + 1));
                        visited[nr][nc][cur.chance + 1] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            return true;
        }
        return false;
    }
}
