import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold3
 * fileName       : 양구출작전_16437
 * author         : yyh77
 * date           : 2023-08-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-29        yyh77       최초 생성
 */
public class Main {

    private static ArrayList<ArrayList<Integer>> adjList;
    private static long[] shipArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        shipArr = new long[N + 1];

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            String animal = st.nextToken();
            long animalNum = Integer.parseInt(st.nextToken());
            int parentNode = Integer.parseInt(st.nextToken());

            if (animal.equals("S")) {
                shipArr[i] = animalNum;
            } //
            else {
                shipArr[i] = animalNum * -1;
            }
            adjList.get(parentNode).add(i);
        }

        dfs(1, 0);

        System.out.println(shipArr[1]);
        br.close();
    }

    private static void dfs(int cur, int parent) {

        for (int next : adjList.get(cur)) {
            dfs(next, cur);
        }

        if (shipArr[cur] > 0) {
            shipArr[parent] += shipArr[cur];
        }
    }
}
