import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName    : 백준
 * fileName       : 뱀과사다리게_16928
 * date           : 2023-05-23
 * description    :
 */
public class Main {

    private static int N, M;
    private static boolean[] visited;
    private static Map<Integer, Integer> ladder;
    private static Map<Integer, Integer> snake;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[107];

        ladder = new HashMap<>();
        snake = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(go());
    }

    private static int go() {

        Queue<Loc> Q = new LinkedList<>();
        Q.offer(new Loc(1, 0));

        while (!Q.isEmpty()) {
            // 현재 위치
            Loc cur = Q.poll();
            visited[cur.loc] = true;

            if (cur.loc == 100) {
                return cur.cnt;
            }

            calculate(Q, cur);
        }

        return -1;
    }

    private static void calculate(Queue<Loc> Q, Loc cur) {

        Loc next = null;

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= 6; i++) {

            int loc = cur.loc + i;

            if (visited[loc]) continue;

            if (ladder.containsKey(loc)) {
                next = new Loc(ladder.get(loc), cur.cnt + 1);
            } //
            else if (snake.containsKey(loc)) {
                next = new Loc(snake.get(loc), cur.cnt + 1);
            } //
            else {
                next = new Loc(loc, cur.cnt + 1);
            }
            Q.offer(next);
            visited[next.loc] = true;
        }
    }

    private static class Loc {
        int loc, cnt;

        public Loc(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "loc=" + loc +
                    ", cnt=" + cnt +
                    '}';
        }
    }
}
