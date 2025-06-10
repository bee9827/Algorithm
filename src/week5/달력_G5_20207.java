package week5;

import java.io.*;
import java.util.StringTokenizer;

/*

 */

public class 달력_G5_20207 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t, n, m;


    long ans;
    int[] days = new int[366];

    public static void main(String[] args) throws IOException {
        달력_G5_20207 solve = new 달력_G5_20207();
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

        for (int j = 0; j < n; j++) {
            StringTokenizer st = readLine();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int i = start; i <= end; i++) {
                days[i]++;
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int l = 0;
        int w = 0;
        for (int i = 1; i < 366; i++) {
            if (days[i] == 0) {
                ans += l * w;
                l = 0;
                w = 0;
            } else {
                l++;
                w = Math.max(w, days[i]);
            }
        }

        ans += l * w;

        bw.write(ans + "\n");
    }
}