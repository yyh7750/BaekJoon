import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[] chess;
    private static int N, ans;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        chess = new int[N];
        ans = 0;

        nQueen(0);

        sb.append(ans).append("\n");

        System.out.println(sb);
        br.close();
    }

    private static void nQueen(int row) {

        if (row == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; i++) {
            chess[row] = i;
            if (isChecked(row)) {
                nQueen(row + 1);
            }
        }
    }

    // 같은 선상, 대각선 모두 허용안됨.
    private static boolean isChecked(int col) {

        for (int i = 0; i < col; i++) {
            if (chess[col] == chess[i]) {
                return false;
            } //
            if (Math.abs(col - i) == Math.abs(chess[i] - chess[col])) {
                return false;
            }
        }

        return true;
    }
}
