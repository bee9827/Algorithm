import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class B_S1_20922 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int MAX_VAL = 100001;
    int[] arr;
    long ans;
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_S1_20922 solve = new B_S1_20922();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }

    public void solve() {
        int[] count = new int[MAX_VAL];
        int left = 0;
        int right = 0;

        int val;
        while (left <= right && right <n) {
            val = arr[right++];
            if (++count[val] <= m) {
                ans = max(ans, right - left);
            } else {
                while(count[val] > m){
                    int prev = arr[left++];
                    count[prev]--;

                }
            }
        }
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