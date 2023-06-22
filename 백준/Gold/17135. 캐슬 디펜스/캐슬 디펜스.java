import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold3
 * fileName       : 캐슬디펜스_17135
 * author         : yyh77
 * date           : 2023-06-21
 * description    :
 * 궁수 3명. 궁수가 적을 공격하면 적은 게임에서 제외된다.
 * 배열 범위(N)까지 적이 도달하면 게임에서 제외된다.
 * 모든 적이 격자판에서 제외되면 게임이 종료된다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-21        yyh77       최초 생성
 */
public class Main {

    private static class Enemy implements Comparable<Enemy> {
        int r, c, d;

        public Enemy(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Enemy o) {
            if (this.d == o.d) {
                return this.c - o.c;
            }
            return this.d - o.d;
        }
    }

    private static int N, M, D, ans;
    private static int[][] map;
    private static int[] archer;
    private static List<Enemy> enemies;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        enemies = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    enemies.add(new Enemy(i, j, 0));
                }
            }
        }

        archer = new int[3];

        comb(0, 0);

        System.out.println(ans);

        br.close();
    }

    /**
     * 궁수 위치 선정을 위한 조합
     *
     * @param idx
     * @param start
     */
    private static void comb(int idx, int start) {

        if (idx == 3) {
            List<Enemy> copyEnemies = copyList();
            attack(copyEnemies);
            return;
        }

        for (int i = start; i < M; i++) {
            archer[idx] = i;
            comb(idx + 1, i + 1);
        }
    }

    /**
     * 적과 궁수의 거리를 구해 적을 공격하는 메소드
     *
     * @param copyEnemies
     */
    private static void attack(List<Enemy> copyEnemies) {

        // 이미 정렬된 PriorityQueue를 사용하기 때문에 poll()하면 거리가 같을 때 가장 왼쪽 적이 뽑힌다.
        PriorityQueue<Enemy> pq = new PriorityQueue<>();
        int cnt = 0;

        while (!copyEnemies.isEmpty()) {

            List<Enemy> catchEnemies = new ArrayList<>();

            // 궁수마다 적 설정
            for (int a = 0; a < 3; a++) {
                pq.clear();

                for (int i = 0; i < copyEnemies.size(); i++) {

                    Enemy enemy = copyEnemies.get(i);
                    int dist = calc(enemy.r, enemy.c, N, archer[a]);

                    // 적과의 거리가 사정거리 안일 때
                    if (dist <= D) {
                        pq.add(new Enemy(enemy.r, enemy.c, dist));
                    }
                }

                // pq가 차있다면 잡을 몬스터가 있다는것
                if (!pq.isEmpty()) {

                    Enemy enemy = pq.poll();
                    boolean flag = false;

                    for (int i = 0; i < catchEnemies.size(); i++) {
                        Enemy catchEnemy = catchEnemies.get(i);

                        // 다른 궁수가 잡으려하는 경우
                        if (enemy.r == catchEnemy.r && enemy.c == catchEnemy.c) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        catchEnemies.add(new Enemy(enemy.r, enemy.c, 0));
                    }
                }
            }

            // catchEnemies 리스트에 있는 적들을 모두 제거
            for (int i = 0; i < catchEnemies.size(); i++) {
                for (int j = copyEnemies.size() - 1; j >= 0; j--) {
                    if (catchEnemies.get(i).r == copyEnemies.get(j).r && catchEnemies.get(i).c == copyEnemies.get(j).c) {
                        copyEnemies.remove(j);
                        cnt++;
                        break;
                    }
                }
            }

            moveEnemy(copyEnemies);
        }

        ans = Math.max(ans, cnt);
    }

    /**
     * 적들이 한칸씩 아래로 이동
     *
     * @param copyEnemies
     */
    private static void moveEnemy(List<Enemy> copyEnemies) {

        for (int i = copyEnemies.size() - 1; i >= 0; i--) {
            copyEnemies.get(i).r += 1;
            if (copyEnemies.get(i).r == N) {
                copyEnemies.remove(i);
            }
        }
    }

    /**
     * 궁수와 적과의 거리계산
     *
     * @return
     */
    private static int calc(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    /**
     * 리스트 복사를 위한 메소드
     *
     * @return
     */
    private static List<Enemy> copyList() {
        List<Enemy> copyEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            copyEnemies.add(new Enemy(enemy.r, enemy.c, enemy.d));
        }
        return copyEnemies;
    }
}
