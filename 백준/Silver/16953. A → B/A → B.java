import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int ans = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        isA2B(a, b);

        System.out.println(ans);
        br.close();
    }

    /**
     * a값을 2로 곱하거나, 맨 끝자리에 1을 붙여 b로 만들 수 있는지 판단하는 메소드.
     * b를 기준으로 a로 만드는 과정을 선택.
     * b가 2로 나눠지거나, 1이 있다면 연산하여 판단.
     *
     * @param a
     * @param b
     * @return
     */
    private static void isA2B(int a, int b) {

        if (a >= b) {
            ans = 0;
            return;
        }

        while (a != b) {
            String strB = String.valueOf(b);
            char endB = strB.charAt(strB.length() - 1);

            if (b < a) {
                ans = -1;
                return;
            }

            if ((b % 2 != 0 && endB != '1')) {
                ans = -1;
                return;
            }

            if (endB == '1') {
                String result = removeLastChar(strB);
                if (!result.isEmpty()) {
                    b = Integer.parseInt(result);
                }
            } //
            else if (b % 2 == 0) {
                b /= 2;
            }

            ans++;
        }
    }

    private static String removeLastChar(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(0, str.length() - 1);
    }
}
