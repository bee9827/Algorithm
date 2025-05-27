package week1;

import java.io.*;
import java.util.StringTokenizer;

public class B_G3_10986 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int[] arr;
    long ans;
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_G3_10986 solve = new B_G3_10986();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }

    public void solve() {
        int[] prefixRemainder = new int[n];

        prefixRemainder[0] = arr[0];
        prefixRemainder[0] %= m;
        for (int i = 1; i < n; i++) {
            prefixRemainder[i] += arr[i] + prefixRemainder[i - 1];
            prefixRemainder[i] %= m;
        }
        long[] count = new long[m];
        for (int i = 0; i < n; i++) {
            count[prefixRemainder[i]]++;
        }

        for (int i = 0; i < m; i++) {
            ans += count[i] * (count[i] - 1) / 2;
        }
        ans += count[0];
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

        arr = new int[n];
        st = readLine();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}