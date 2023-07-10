import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.silver2
 * fileName       : 촌수계산_2644
 * author         : yyh77
 * date           : 2023-07-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-07-10        yyh77       최초 생성
 */
public class Main {

    private static int ans;
    private static ArrayList<ArrayList<Integer>> adjList;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }
        
        dfs(start, end, 1);

        System.out.println(ans == 0 ? -1 : ans);

        br.close();
    }

    private static void dfs(int start, int end, int depth) {

        // 돌았던 노드 중복 탐색 방지하기 위한 방문 배열
        visited[start] = true;

        // 인접리스트를 돌면서 체크
        for (int next : adjList.get(start)) {

            // 연결되어 있는 것 중에 해당하는 사람이 있다면 종료
            if (next == end) {
                ans = depth;
                return;
            }

            // 해당하지 않으면 다음 노드 돌면서 depth 증가
            if (!visited[next]) {
                dfs(next, end, depth + 1);
            }
        }
    }
}
