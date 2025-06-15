package week5;

import java.io.*;
import java.util.StringTokenizer;

/*

 */

public class 외판원순회2_S2_10971 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] edge;

    int t, n, m;


    long ans;

    public static void main(String[] args) throws IOException {
        외판원순회2_S2_10971 solve = new 외판원순회2_S2_10971();
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
        n = Integer.parseInt(readLine().nextToken());

        edge = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = readLine();
            for (int j = 0; j < n; j++) {
                edge[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        boolean[] visited = new boolean[n];
        visited[0] = true;
        ans = Integer.MAX_VALUE;
        dfs(edge, visited, 0, 0, 0, 0);
        bw.write(String.valueOf(ans));
    }


    /*
    N*N*N*....*N

    O(N^N)

    next_permutation


    1,2,3,4,5
    1,2,3,5,4
    1,2,4,3,5
    1,2,4,5,3




    arr[N]


    [0,1,0,1,0,1,1,1,1,1,...1]

    1개

    for(int i=0; i<n; i++){
        if(arr[i] == 1) count++;
    }

    N!
    */

    public void dfs(int[][] edge, boolean[] visited, int start, int curr, int depth, int cost) {
        if (depth == n - 1) {
            if (edge[curr][start] != 0) ans = Math.min(ans, cost + edge[curr][start]);
            return;
        }

        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (edge[curr][nextNode] == 0 || visited[nextNode]) continue;

            visited[nextNode] = true;
            dfs(edge, visited, start, nextNode, depth + 1, cost + edge[curr][nextNode]);
            visited[nextNode] = false;
        }
    }


}