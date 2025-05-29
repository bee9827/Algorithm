package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
O(N)
 */

public class 나무_S2_2805 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int ans = 0;
    int n;
    int m;
    List<Integer> trees = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        나무_S2_2805 solve = new 나무_S2_2805();
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

        st = readLine();
        for (int i = 0; i < n; i++) {
            trees.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        trees.add(0);
        Collections.sort(trees, Collections.reverseOrder());


        long prefixSum = 0;

        // 깎인 나무 길이로 변경.
        for (int i = 0; i < trees.size() - 1; i++) {
            int count = i + 1;
            long current = trees.get(i);
            long next = trees.get(i + 1);
            prefixSum += (current - next) * count;

            // 이번거와 다음거의 중간.
            if (prefixSum >= m) {
                bw.write(String.valueOf(next + (prefixSum - m) / count));
                return;
            }
        }
    }
}