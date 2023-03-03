import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int H, W, ans;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[W];
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(ans);
        br.close();
    }

    /**
     * 양 끝에 블록의 높이가 없다면 사이에 있는 블록 중 높이가 처음으로 시작하는 블록을 기준으로 맨 왼쪽, 마지막이 맨 오른쪽이다.
     * 1. 양 끝의 높이가 H보다 작다면 물이 넘친다.
     * 2. 양 끝의 높이가 1이상이며 바로 옆의 블록의 높이가 양 끝보다 작다면 물이 고인다.
     * 3. 양 끝의 높이가 사이에 있는 블록들보다 높이가 낮다면 물이 고이지 않는다.
     * <p>
     * => 양 끝의 블록은 물이 고이지 않으며 양 끝의 사이에 있는 블록들이 양 끝 블록보다 낮아야 물이 고인다.
     * <p>
     * ==> 왼쪽에서 가장 높은 블록을 구하고, 오른쪽에서 가장 높은 블록을 구한다.
     * ==> 둘 중 낮은 높이의 블록에서 사이에 있는 블록들의 높이를 각각의 인덱스 별로 구해준다.
     * ==> 양 끝의 사이에 있는 블록을 현재 인덱스로 두고 왼쪽, 오른쪽을 비교하여 현재 인덱스가 물이 고이는지 확인.
     */
    private static void solution() {

        // 양 끝은 내부 반복문에서 검사하므로 제외
        for (int i = 1; i < W - 1; i++) {
            int left = 0, right = 0;

            // 왼쪽에서 제일 높은 블록 구하기
            for (int j = 0; j < i; j++) {
                left = Math.max(left, arr[j]);
            }
            // 오른쪽에서 제일 높은 블록 구하기
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, arr[j]);
            }

            // 물이 고이면 더해준다.
            if (arr[i] < left && arr[i] < right) {
                ans += Math.min(left, right) - arr[i];
            }
        }
    }
}