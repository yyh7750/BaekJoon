import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 캠프준비_16938
 * author         : yyh77
 * date           : 2023-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-25        yyh77       최초 생성
 */
public class Main {

    private static int N, L, R, X, ans;
    private static int[] arr, sel;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        L = Integer.valueOf(st.nextToken());
        R = Integer.valueOf(st.nextToken());
        X = Integer.valueOf(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sel = new int[N];
        comb(0, 0, 0);

        System.out.println(ans);

        br.close();
    }

    /**
     * 조합으로 경우의 수를 찾은 후, 조건에 맞는걸 찾으면 카운트 증가
     * @param idx
     * @param k
     * @param sum
     */
    private static void comb(int idx, int k, int sum) {

        if (idx == N) {

            if (sum >= L && sum <= R) {

                int max = 0;
                int min = Integer.MAX_VALUE;

                for (int i = 0; i < k; i++) {
                    max = Math.max(max, sel[i]);
                    min = Math.min(min, sel[i]);
                }

                if ((max - min) >= X) {
                    ans++;
                }
            }

            return;
        }

        sel[k] = arr[idx];

        comb(idx + 1, k + 1, sum + arr[idx]);
        comb(idx + 1, k, sum);
    }
}
