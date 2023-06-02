import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold5
 * fileName       : ABCDE_13023
 * author         : yyh77
 * date           : 2023-06-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-01        yyh77       최초 생성
 */
public class Main {

    private static int N, M;
    private static ArrayList<ArrayList<Integer>> adjList;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 친구 관계는 양방향
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, 0);
        }

        System.out.println(0);
    }


    /**
     * 친구 관계가 5명 이상일 경우 성립한다.
     *
     * @param idx
     * @param depth
     */
    private static int dfs(int idx, int depth) {

        // depth가 0부터 시작이므로 4일 경우 5명 이상 성립
        if (depth == 4) {
            return 1;
        }

        visited[idx] = true;
        for (int next : adjList.get(idx)) {
            if (!visited[next]) {
                int result = dfs(next, depth + 1);
                if (result == 1) {
                    System.out.println(result);
                    System.exit(0);
                }
            }
        }
        
        // 탐색 실패 시 되돌려주기
        visited[idx] = false;

        return 0;
    }
}
