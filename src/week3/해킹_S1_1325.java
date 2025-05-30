package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
기본 아이디어: 각 노드마다 bfs or dfs 실행
O(n * m) 약 10^9 좀 아슬한데? -> dp로 해보려했는데 실패 (순환구조 해결 불가)
 */

public class 해킹_S1_1325 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long ans = 0;
    int n;
    int m;
    List<Integer>[] edge;
    List<Integer> ansList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        해킹_S1_1325 solve = new 해킹_S1_1325();
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
        for (int i = 1; i <= n; i++) {
            int count = bfs(i, edge);
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
}