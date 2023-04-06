import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int k, n, hideIdx;
    private static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());

        char[] before, after;

        char A = 'A';
        before = new char[k];
        for (int i = 0; i < k; i++) {
            before[i] = A++;
        }

        after = new char[k];
        after = br.readLine().toCharArray();

        map = new char[n][k - 1];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < k - 1; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][0] == '?') {
                    hideIdx = i;
                }
            }
        }

        System.out.println(ladderClimbing(before, after));
        br.close();
    }

    /**
     * 사다리 타기를 진헹하는 메소드
     * 1. i번째에 '-'이 나오면 i와 i+1에 해당하는 문자열을 바꿔준다.
     * 2. i번째에 '*'이 나오면 그대로 둔다.
     */
    private static String ladderClimbing(char[] before, char[] after) {

        // 초기값부터 물음표 나오기 전까지 돌린다.
        for (int i = 0; i < hideIdx; i++) {
            swap(before, i);
        }

        // 결과값부터 거꾸로 사다리 타면서 물음표 나오기 전까지 돌린다.
        for (int i = n - 1; i > hideIdx; i--) {
            swap(after, i);
        }

        // 비교합시다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k - 1; i++) {
            if (before[i] == after[i]) {
                sb.append('*');
            } //
            else if (before[i] == after[i + 1]) {
                char temp = before[i];
                before[i] = before[i + 1];
                before[i + 1] = temp;
                sb.append('-');
            } //
            else {
                sb = new StringBuilder();
                for (int j = 0; j < k - 1; j++) {
                    sb.append('x');
                }
                return sb.toString();
            }
        }

        return sb.toString();
    }

    private static void swap(char[] arr, int idx) {
        for (int i = 0; i < k - 1; i++) {
            if (map[idx][i] == '-') {
                char temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }
}
