import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static int[] arr;
    private static boolean[] visited;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1]; // N은 1부터
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int i : list) {
            sb.append(i).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int cur, int next) {

        if (!visited[arr[cur]]) {
            visited[arr[cur]] = true;
            dfs(arr[cur], next);
            visited[arr[cur]] = false;
        }

        if (arr[cur] == next) {
            list.add(next);
        }
    }
}
