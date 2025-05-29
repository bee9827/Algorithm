package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
기본 아이디어: 최소값을 갱신해나간다.
ex) 5 2 4 1
    5 2 2 1

O(N)
 */

public class 주유소_S3_13305 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long ans = 0;
    int n;
    int m;
    int[] station;
    List<Integer> distance = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        주유소_S3_13305 solve = new 주유소_S3_13305();
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
        station = new int[n];


        st = readLine();
        for (int i = 0; i < n - 1; i++) {
            distance.add(Integer.parseInt(st.nextToken()));
        }
        st = readLine();
        for (int i = 0; i < n; i++) {
            station[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int minCost = station[0];
        for(int i=0; i<n; i++){
            minCost = Math.min(minCost, station[i]);
            station[i] = minCost;
        }

        for(int i=0; i<n-1; i++){
            int length = distance.get(i);
            int cost = station[i];
            ans += (long)cost * length;
        }

        bw.write(String.valueOf(ans));
    }
}