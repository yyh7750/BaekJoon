import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : 꿀따기_21758
 * author         : yyh77
 * date           : 2023-07-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-10        yyh77       최초 생성
 */
public class Main {

    private static int N;
    private static int[] arr, sumArr, reverseSumArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sumArr = new int[N];
        reverseSumArr = new int[N];

        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            // 누적합 구해주기 (왼쪽에서 오른쪽으로 가는 정방향)
            sumArr[i] = sum;
        }

        sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            // 으론쪽에서 왼쪽으로의 누적합 (역방향)
            sum += arr[i];
            reverseSumArr[i] = sum;
        }

        System.out.println(solution());
        br.close();
    }

    private static int solution() {

        int right = rightHoney();
        int left = leftHoney();
        int mid = midHoney();

        return Math.max(right, Math.max(left, mid));
    }

    /**
     * 꿀통 맨 오른쪽에 고정
     *
     * @return
     */
    private static int rightHoney() {

        int bee1 = 0, bee2 = 0, max = 0;

        for (int i = 1; i < N - 1; i++) {

            // 꿀 총량에서 벌1의 위치와 벌2가 구하는 꿀 값을 뺀 값
            bee1 = sumArr[N - 1] - arr[0] - arr[i];
            // 꿀 총량에서 벌2의 위치 값을 뺀 값
            bee2 = sumArr[N - 1] - sumArr[i];
            max = Math.max(max, bee1 + bee2);
        }

        return max;
    }

    /**
     * 꿀통 맨 왼쪽에 고정
     *
     * @return
     */
    private static int leftHoney() {

        int bee1 = 0, bee2 = 0, max = 0;

        for (int i = N - 2; i > 0; i--) {

            // 꿀 총량에서 벌1의 위치와 벌2가 구하는 꿀 값을 뺀 값
            bee1 = sumArr[N - 1] - arr[N - 1] - arr[i];
            // 꿀 총량에서 벌2의 위치 값을 뺀 값
            bee2 = sumArr[N - 1] - reverseSumArr[i];
            max = Math.max(max, bee1 + bee2);
        }

        return max;
    }

    /**
     * 꿀통 가운데 랜덤하게 배치
     *
     * @return
     */
    private static int midHoney() {

        int bee1 = 0, bee2 = 0, max = 0;

        for (int i = 1; i < N - 1; i++) {

            // 벌1이 벌통까지 가는데 구하는 값
            bee1 = sumArr[i] - arr[0];
            // 벌2가 벌통까지 가는데 구하는 값
            bee2 = reverseSumArr[i] - arr[N - 1];
            max = Math.max(max, bee1 + bee2);
        }

        return max;
    }
}
