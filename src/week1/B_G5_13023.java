package week1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_G5_13023 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    List<Integer>[] edge = new ArrayList[2_000];
    boolean[] visited = new boolean[2_000];
    int ans = 0;

    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_G5_13023 solve = new B_G5_13023();
        solve.solve();
    }

    public void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            edge[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edge[from].add(to);
            edge[to].add(from);
        }
    }

    //O(N^2) -> O(N) 개선 필요. (이번 문제는 방문 노드 수가 적음)
    public int dfs(int idx,int depth) {
        visited[idx] = true;
        if(depth >= 5){
            ans = 1;
            return 5;
        }

        for(int next : edge[idx]) {
            if(!visited[next]) {
                return dfs(next,depth+1)+1;
            }
        }

        return 1;
    }

    public void solve() throws IOException {
        input();
        for(int i=0; i<n; i++) {
            if(ans == 0 && !visited[i]) dfs(i, 0);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}