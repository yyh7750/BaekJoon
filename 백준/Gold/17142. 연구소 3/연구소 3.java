import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Loc {
        int r, c, cnt;

        public Loc(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    private static int N, M, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static List<Loc> virusList;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virusList = new ArrayList<>();

        // 비어있는 곳(바이러스가 퍼질 곳)을 카운트하기 위한 변수
        int emptyPlace = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    virusList.add(new Loc(i, j, 0));
                } //
                else if (map[i][j] == 0) {
                    emptyPlace++;
                }
            }
        }

        if (emptyPlace == 0) {
            System.out.println(0);
            return;
        }

        combination(0, 0, new int[M], emptyPlace);

        System.out.println(ans);
        br.close();
    }

    private static void combination(int idx, int k, int[] sel, int emptyPlace) {

        if (idx > virusList.size()) {
            return;
        }

        if (k == M) {
            Queue<Loc> Q = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < M; i++) {
                Loc virus = virusList.get(sel[i]);
                Q.offer(virus);
                visited[virus.r][virus.c] = true;
            }

            int time = bfs(Q, visited, emptyPlace);

            if (time != -1) {
                if (ans == -1) {
                    ans = Integer.MAX_VALUE;
                }
                ans = Math.min(ans, time);
            } //
            else if (ans == Integer.MAX_VALUE) {
                ans = -1;
            }

            return;
        }

        sel[k] = idx;

        combination(idx + 1, k + 1, sel, emptyPlace);
        combination(idx + 1, k, sel, emptyPlace);
    }

    /**
     * Map의 전체 크기를 선언해놓고, 선택된 바이러스 개수 + 벽의 개수를 mapSize 변수로 받는다.
     * 바이러스가 퍼지면서 퍼진 개수만큼 mapSize를 늘린다.
     * mapSize가 fullSize가 되는 순간 모든 바이러스가 퍼진 것이다.
     * 단, 비활성 바이러스도 바이러스이기 때문에 마지막에 비활성 바이러스만 남아있다면
     * 마지막 카운트는 세지 않고 리턴해준다.
     * <p>
     * 연산 두 번 하지 않기 위해 map을 처음 초기화할 때 비어있는 부분만 카운트한다.
     * -> bfs 탐색을 하면서 방문하지 않았고, 벽이 아닌 곳을 찾기만 한다면 카운트를 감소시킨다.
     * -> 카운트가 0이 되면 모든 바이러스가 퍼진 것이다.
     * <p>
     * 모든 경우의 수를 탐색하기 위해 cnt 변수는 전역변수가 아닌 로컬변수로 사용한다.
     *
     * @param Q
     * @param visited
     * @param emptyPlace
     * @return
     */
    private static int bfs(Queue<Loc> Q, boolean[][] visited, int emptyPlace) {

        while (!Q.isEmpty()) {

            Loc cur = Q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 배열 범위를 벗어나지 않고, 방문하지 않았던 곳이면서, 벽이 아닌 곳 (비활성 바이러스 포함)
                if (isChecked(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {

                    // 빈 칸일 때 cnt 감소 -> 위 조건으로 무조건 걸린다.
                    if (map[nr][nc] == 0) {
                        emptyPlace--;
                    }

                    // 더이상 퍼트릴 곳이 없다면 현재 카운트 + 1 리턴.
                    if (emptyPlace == 0) {
                        return cur.cnt + 1;
                    }

                    visited[nr][nc] = true;
                    Q.offer(new Loc(nr, nc, cur.cnt + 1));
                }
            }
        }

        return -1;
    }

    private static boolean isChecked(int nr, int nc) {
        if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            return true;
        }
        return false;
    }
}
