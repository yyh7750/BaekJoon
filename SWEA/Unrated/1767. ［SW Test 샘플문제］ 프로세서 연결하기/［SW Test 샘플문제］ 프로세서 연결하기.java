import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * packageName    : SWExpertAcademy
 * fileName       : 프로세서연결하기_1767
 * author         : yyh77
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        yyh77       최초 생성
 */
public class Solution {

    private static class Loc {
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int N, ans;
    private static List<Loc> cores;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 코어 위치 저장. 가장자리는 제외해도 된다.
                    if (map[i][j] == 1 && i != 0 && j != 0) {
                        cores.add(new Loc(i, j));
                    }
                }
            }

            visited = new boolean[cores.size()];

            for (int i = cores.size(); i >= 0; i--) {
                subSet(0, 0, i);
                // 최대한 많은 코어를 연결해야 하므로 for문을 cores.size부터 돌렸다.
                // 따라서 ans 값이 바뀌면 더이상 진행할 필요가 없으므로 break;
                if (ans != Integer.MAX_VALUE) {
                    break;
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    /**
     * size만큼 코어를 선택하는 부분집합 메소드
     *
     * @param idx
     * @param cnt
     * @param size : 코어 선택 개수
     */
    private static void subSet(int idx, int cnt, int size) {

        if (cnt == size) {
            dfs(0, 0);
            return;
        }

        for (int i = idx; i < cores.size(); i++) {
            // 방문처리 및 인덱스 증가시켜서 다음꺼 구하기
            visited[i] = true;
            subSet(i + 1, cnt + 1, size);
            visited[i] = false;
        }
    }

    /**
     * 부분집합 선택을 완료했을 때 전선 연결하는 메소드
     *
     * @param idx
     * @param sum
     */
    private static void dfs(int idx, int sum) {

        // 코어 별로 전선을 다 연결했을 때
        if (idx == cores.size()) {
            // 최솟값 구하기
            ans = Math.min(ans, sum);
            return;
        }

        // 부분집합에 포함된 인덱스만 처리
        if (!visited[idx]) {
            dfs(idx + 1, sum);
            return;
        }


        for (int d = 0; d < 4; d++) {

            int r = cores.get(idx).r;
            int c = cores.get(idx).c;
            boolean flag = false;
            int cnt = 0;

            while (true) {

                r += dr[d];
                c += dc[d];

                // 끝에 도달했을 때 flag 처리
                if (!isChecked(r, c)) {
                    flag = true;
                    break;
                }

                if (map[r][c] != 0) {
                    break;
                }

                // 전선 표시
                map[r][c] = -1;
                cnt++;
            }

            // 끝까지 갔다면 다음 코어 진행
            if (flag) {
                dfs(idx + 1, sum + cnt);
            }

            // 전선 표시 복구
            while (true) {

                r -= dr[d];
                c -= dc[d];

                if (r == cores.get(idx).r && c == cores.get(idx).c) {
                    break;
                }

                map[r][c] = 0;
            }
        }
    }

    /**
     * 배열 범위 값 검사 메소드
     *
     * @param r
     * @param c
     * @return
     */
    private static boolean isChecked(int r, int c) {

        if (r >= 0 && r < N && c >= 0 && c < N) {
            return true;
        }
        return false;
    }
}
