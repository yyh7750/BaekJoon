import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : baekjoon.silver1
 * fileName       : 영역구하기_2583
 * author         : yyh77
 * date           : 2024-01-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-01-21        yyh77       최초 생성
 */
public class Main {

    private static int M, N, K;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            for (int i = sc; i < ec; i++) {
                for (int j = sr; j < er; j++) {
                    map[i][j] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!map[i][j]) {
                    cnt++;
                    list.add(bfs(i, j));
                }
            }
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");

        for (int i : list) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    private static int bfs(int r, int c) {

        int cnt = 1;

        Queue<Loc> q = new LinkedList<>();
        q.offer(new Loc(r, c));
        map[r][c] = true;

        while (!q.isEmpty()) {
            Loc cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 넓이 구하기 = cnt
                if (isChecked(nr, nc) && !map[nr][nc]) {
                    q.offer(new Loc(nr, nc));
                    cnt++;
                    map[nr][nc] = true;
                }
            }
        }

        return cnt;
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
