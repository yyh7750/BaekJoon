import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * packageName    : baekjoon.gold4
 * fileName       : 특정한_최단경로_1504
 * author         : yyh77
 * date           : 2023-06-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-19        yyh77       최초 생성
 */
public class Main {

    private static class Loc implements Comparable<Loc> {

        int node, weight;

        public Loc(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Loc o) {
            return this.weight - o.weight;
        }
    }

    private static int N, E;
    private static ArrayList<ArrayList<Loc>> adjList;
    private static final int INF = 200_000_000; // 200000 * 1000

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList.get(from).add(new Loc(to, weight));
            adjList.get(to).add(new Loc(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        System.out.println(solution(v1, v2));

        br.close();
    }

    /**
     * dijkstra 메소드를 이용해 각각의 결과값을 구하여 최솟값을 리턴하는 메소드
     *
     * @param v1
     * @param v2
     * @return
     */
    private static int solution(int v1, int v2) {

        int result1 = 0;
        int result2 = 0;

        result1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        result2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

        if (result1 >= INF && result2 >= INF) {
            return -1;
        }
        return Math.min(result1, result2);
    }

    /**
     * 기준 노드에서 다른 노드로의 최단거리를 구하는 메소드
     *
     * @param from
     * @param to
     * @return
     */
    private static int dijkstra(int from, int to) {

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }
        dist[from] = 0;

        PriorityQueue<Loc> pq = new PriorityQueue<>();
        pq.add(new Loc(from, 0));

        while (!pq.isEmpty()) {
            Loc cur = pq.poll();

            if (!visited[cur.node]) {
                visited[cur.node] = true;

                for (int i = 0; i < adjList.get(cur.node).size(); i++) {
                    int nextNode = adjList.get(cur.node).get(i).node;
                    int nextWeight = adjList.get(cur.node).get(i).weight;

                    if (!visited[nextNode] && dist[nextNode] > cur.weight + nextWeight) {
                        dist[nextNode] = cur.weight + nextWeight;
                        pq.add(new Loc(nextNode, dist[nextNode]));
                    }
                }
            }
        }

        return dist[to];
    }
}
