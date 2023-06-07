import java.util.*;
import java.io.*;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 양_3184
 * author         : yyh77
 * date           : 2023-06-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-07        yyh77       최초 생성
 */
public class Main {

    private static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};
    private static char[][] map;
    private static boolean[][] visited;
    private static List<Loc> sheep, wolf;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        sheep = new ArrayList<>();
        wolf = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'v' || map[i][j] == 'o') {

                    wolf = new ArrayList<>();
                    sheep = new ArrayList<>();

                    bfs(i, j);

                    if (wolf.size() < sheep.size()) {
                        for (Loc loc : wolf) {
                            map[loc.r][loc.c] = '.';
                        }
                    } //
                    else {
                        for (Loc loc : sheep) {
                            map[loc.r][loc.c] = '.';
                        }
                    }
                }
            }
        }

        int wolfCnt = 0;
        int sheepCnt = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'v') wolfCnt++;
                else if (map[i][j] == 'o') sheepCnt++;
            }
        }

        System.out.println(sheepCnt + " " + wolfCnt);
        br.close();
    }

    private static void bfs(int r, int c) {

        Queue<Loc> q = new LinkedList<>();
        q.offer(new Loc(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {

            Loc cur = q.poll();

            if (map[cur.r][cur.c] == 'v') {
                wolf.add(new Loc(cur.r, cur.c));
            } else if (map[cur.r][cur.c] == 'o') {
                sheep.add(new Loc(cur.r, cur.c));
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                    if (!visited[nr][nc] && map[nr][nc] != '#') {
                        q.offer(new Loc(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}