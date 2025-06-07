package week4;

import java.io.*;
import java.util.StringTokenizer;

/*

 */

public class 블로그2_S3_20365 {
    private static final char RED = 'R';
    private static final char BLUE = 'B';
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int m;

    long ans;
    String color;
    int[] arr;

    public static void main(String[] args) throws IOException {
        블로그2_S3_20365 solve = new 블로그2_S3_20365();
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
        arr = new int[n];

        color = readLine().nextToken();
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int redCount = 0;
        int blueCount = 0;
        char prev = '0';

        for (int i = 0; i < n; i++) {
            char curr = color.charAt(i);

            if (curr != prev) {
                switch (curr) {
                    case RED -> redCount++;
                    case BLUE -> blueCount++;
                }
            }

            prev = curr;
        }

        ans = Math.min(redCount, blueCount) + 1;

        bw.write(String.valueOf(ans));
    }
}