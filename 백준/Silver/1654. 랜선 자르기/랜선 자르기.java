import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] arr;
    private static long min, mid, max, ans;
    private static int N, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(arr[i], max);
        }

        binarySearch();

        System.out.println(ans);
        br.close();
    }

    /**
     * mid 값으로 선 개수를 만들어준다.
     * 선 개수가 적다면 mid 값을 더 작아야 한다는 것이므로 max 값을 변경.
     * 선 개수가 많다면 mid 값이 더 커도 된다는 것이므로 min 값을 변경.
     */
    private static void binarySearch() {

        mid = 0;
        min = 1;

        while (max >= min) {
            mid = (min + max) / 2;

            long cnt = 0;
            for (long i : arr) {
                // mid 값으로 선 개수 만들기
                cnt += i / mid;
            }

            // 랜선 개수가 N개 이상일 경우
            // 더 길게 만들 수 있다 -> break 걸지말고 끝까지 돌려라
            if (cnt >= N) {
                // mid + 1 : mid 값은 이미 검사 했으니까 + 1
                min = mid + 1;
                ans = mid;
            }
            // 아닌 경우 max 줄여라.
            else {
                // mid - 1 : mid 값은 이미 검사 했으니까 - 1
                max = mid - 1;
            }
        }
    }
}
