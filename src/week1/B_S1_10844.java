package week1;

import java.io.*;
import java.util.StringTokenizer;

public class B_S1_10844 {
    private static final int DIVISOR = (int)Math.pow(10,9);
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    long[] arr = {
            0, 1, 1, 1, 1,
            1, 1, 1, 1, 1};
    long[] dp = new long[10];
    long ans = 0;

    int n;

    public static void main(String[] args) throws IOException {
        B_S1_10844 solve = new B_S1_10844();
        solve.solve();
    }

    public StringTokenizer readLine() {
        try {
            return new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("읽어오지 못했습니다.");
        }
    }

    public void input() throws IOException {
        StringTokenizer st = readLine();
        n = Integer.parseInt(st.nextToken());
    }


    public void solve() throws IOException {
        input();
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < 10; i++) {
                if (i == 0) dp[i] = arr[i + 1];
                else if (i == 9) dp[i] = arr[i - 1];
                else dp[i] = arr[i-1] + arr[i+1];
                dp[i] %= DIVISOR;
            }
            for (int i = 0; i < 10; i++) {
                arr = dp.clone();
            }
        }
        for (int i = 0; i < 10; i++) {
            ans += arr[i];
            ans %= DIVISOR;
        }

        output();
    }

    private void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}