import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
기본 아이디어: 돌면서 탐색

O(N^2)
 */

public class Main {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long ans = 0;
    int n;
    int m;
    List<Integer>[] edge;
    List<Integer> ansList = new ArrayList<>();
    int[] dp;


    public static void main(String[] args) throws IOException {
        Main solve = new Main();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }


    public StringTokenizer readLine() {
        try {
            return new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("읽어오지 못했습니다.");
        }
    }

    public void input() {
        StringTokenizer st = readLine();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edge = new List[n + 1];

        for (int i = 0; i < m; i++) {
            st = readLine();
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            if (edge[from] == null) {
                edge[from] = new ArrayList<>();
            }

            edge[from].add(to);
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int ansCount = 0;
        dp = new int[n + 1];

        Arrays.fill(dp, -1);

        for (int i = 1; i <= n; i++) {
            int count = dfs(i, edge);
            if (ansCount < count) {
                ansList.clear();
                ansCount = count;

                ansList.add(i);
            } else if (ansCount == count) {
                ansList.add(i);
            }
        }

        bw.write(
                ansList.stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    public int dfs(int i, List<Integer>[] edge) {
        if (dp[i] != -1) return dp[i];

        List<Integer> nextNodes = edge[i];
        if(nextNodes == null) return dp[i] = 0;

        for(int nextNode : nextNodes) {
            
        }

    }

    /*
    public int bfs(int i, List<Integer>[] edge) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.add(i);
        visited[i] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int from = queue.poll();

            List<Integer> nextEdge = edge[from];
            if(nextEdge == null) {
                continue;
            }
            for (int to : nextEdge) {
                if (!visited[to]) {
                    visited[to] = true;
                    queue.add(to);
                }
            }
        }

        return count;
    }
     */
}