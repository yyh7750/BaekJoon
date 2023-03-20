import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] ability;
    private static boolean[] visited;
    private static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            ability = new int[11][11];

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    ability[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 해당 행에서 하나 선택하면 다른 행에서는 선택하지 못한다.
            // => visited를 행 단위로 검사
            visited = new boolean[11];
            // 테스트 케이스마다 초기화..
            ans = 0;
            backtracking(0, 0);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void backtracking(int idx, int sum) {

        // 모든 선수의 포지션을 골랐을 때
        if (idx == 11) {
            ans = Math.max(ans, sum);
            return;
        }

        // 한 행씩 0이 아닌 곳을 선택하면서 방문 체크
        for (int i = 0; i < 11; i++) {
            if (!visited[i] && ability[idx][i] != 0) {
                visited[i] = true;
                backtracking(idx + 1, sum + ability[idx][i]);
                visited[i] = false;
            }
        }
    }
}
