import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = {1, 2, 3};

        // 1, 2, 3만 들어가는 가장 좋은 수열 중 작은 수를 출력
        // 줄어드는 수와 마찬가지로 작은 수 출력 = 조합으로 순서를 정해준다.
        combination(0, "", N, arr);
    }

    private static void combination(int idx, String checkNum, int n, int[] arr) {

        if (idx == n) {
            // 좋은 수열 검사
            if (isGoodSequence(checkNum)) {
                System.out.println(checkNum);
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (isGoodSequence(checkNum + arr[i])) {
                combination(idx + 1, checkNum + arr[i], n, arr);
            }
        }
    }

    /**
     * 가운데 수를 기준으로 한 칸씩 좁히며 맨 앞, 맨 뒤가 동일한지 체크
     *
     * @param checkNum
     * @return
     */
    private static boolean isGoodSequence(String checkNum) {
        for (int i = 1; i <= checkNum.length() / 2; i++) {
            String tmp1 = checkNum.substring(checkNum.length() - i * 2, checkNum.length() - i);
            String tmp2 = checkNum.substring(checkNum.length() - i, checkNum.length());
            if (tmp1.equals(tmp2)) {
                return false;
            }
        }
        return true;
    }
}
