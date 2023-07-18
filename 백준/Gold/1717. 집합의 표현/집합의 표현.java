import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 집합의_표현_1717
 * author         : yyh77
 * date           : 2023-07-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-18        yyh77       최초 생성
 */
public class Main {

    private static int[] parent;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command == 0) {
                union(a, b);
            } else if (command == 1) {
                sb.append((isSameParent(a, b) ? "YES" : "NO") + "\n");
            } else {
                continue;
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    /**
     * 부모 찾는 메소드
     *
     * @param x
     * @return
     */
    public static int find(int x) {

        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    /**
     * 합집합 연산. 큰 값의 부모를 작은 값의 부모로 바꿔준다
     *
     * @param x
     * @param y
     */
    public static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x != y) {
            if (x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    /**
     * 교집합 확인 연산
     *
     * @param x
     * @param y
     * @return
     */
    public static boolean isSameParent(int x, int y) {

        x = find(x);
        y = find(y);

        if (x == y) {
            return true;
        }
        return false;
    }
}
