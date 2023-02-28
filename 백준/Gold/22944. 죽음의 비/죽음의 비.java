import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Loc {
        int r, c, hp, umbrella, cnt;

        public Loc(int r, int c, int hp, int umbrella, int cnt) {
            this.r = r;
            this.c = c;
            this.hp = hp;
            this.umbrella = umbrella;
            this.cnt = cnt;
        }
    }

    private static int N, H, D, ans = Integer.MAX_VALUE;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        visited = new int[N][N];

        int sr = 0, sc = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        System.out.println(bfs(sr, sc));
        br.close();
    }

    private static int bfs(int sr, int sc) {

        Queue<Loc> Q = new LinkedList<>();
        Q.offer(new Loc(sr, sc, H, 0, 0));
        visited[sr][sc] = H;

        while (!Q.isEmpty()) {
            Loc cur = Q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int hp = cur.hp;
                int umbrella = cur.umbrella;

                if (isChecked(nr, nc)) {

                    // 다음 위치가 안전지대면 이동거리 리턴
                    if (map[nr][nc] == 'E') {
                        ans = Math.min(ans, cur.cnt + 1);
                        continue;
                    }
                    // 다음 위치가 우산이면 내구도 갱신
                    else if (map[nr][nc] == 'U') {
                        umbrella = D;
                    }

                    // 내구도가 남아 있다면 내구도 감소
                    if (umbrella > 0) {
                        umbrella -= 1;
                    } 
                    // 아니면 hp 감소
                    else {
                        hp -= 1;
                    }

                    // hp 0이면 다른 방향 진행
                    if (hp == 0) {
                        continue;
                    }

                    // 방문 배열 처리 = 우산을 들고 간다면 갈 수 있는 경우의 수 고려
                    // hp가 더 많은 경우의 수가 있다면 해당 경우의 수로 탐색 진행
                    if (visited[nr][nc] < hp + umbrella) {
                        visited[nr][nc] = hp + umbrella;
                        Q.offer(new Loc(nr, nc, hp, umbrella,cur.cnt + 1));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            return true;
        }
        return false;
    }
}