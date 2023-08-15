import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold2
 * fileName       : 컬러볼_10800
 * author         : yyh77
 * date           : 2023-08-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-15        yyh77       최초 생성
 */
public class Main {

    private static class Ball implements Comparable<Ball> {
        int idx, color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }

    private static int N;
    private static Ball[] balls;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        init();
        solution();
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int size : result) {
            sb.append(size).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 현재 인덱스의 크기보다 작은 공들의 크기 값의 합을 구해야한다.
     * 크기가 작은순으로 배열 정렬.
     * 현재 공보다 작은 크기를 누적합으로 구한 후 같은 색 공의 크기의 합을 빼준다.
     * 같은 색의 합은 누적합으로 구해준다.
     */
    private static void solution() {
        // 크기 작은순으로 정렬하며 작은 공부터 탐색한다.
        Arrays.sort(balls);

        result = new int[N];
        int[] colors = new int[N + 1];
        int ballIdx = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {

            Ball cur = balls[i];

            while (balls[ballIdx].size < cur.size) {
                sum += balls[ballIdx].size;
                colors[balls[ballIdx].color] += balls[ballIdx].size;
                ballIdx++;
            }
            result[cur.idx] = sum - colors[cur.color];
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, c, s);
        }
    }
}
