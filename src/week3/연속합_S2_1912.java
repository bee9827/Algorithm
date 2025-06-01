package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
#기본 아이디어:
i) 누적합을 이용하여 음수가 아닐경우 계속 더해간다.
ii) 음수일 경우 누적합을 초기화하고 ans를 갱신한다. (음수로만 이루어진 배열일수 있기때문)

#시간복잡도 : 
O(N)
 */

public class 연속합_S2_1912 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long ans = 0;
    int n;
    int m;
    List<Integer> arr = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        연속합_S2_1912 solve = new 연속합_S2_1912();
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

        st = readLine();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            arr.add(number);
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        int prefixSum = 0;
        ans = -1000;
        for (int number : arr) {
            if (number + prefixSum < 0) {
                prefixSum = 0;

                ans = Math.max(ans, number);
            } else {
                prefixSum += number;
                ans = Math.max(ans, prefixSum);
            }
        }
        bw.write(ans + "\n");
    }

}