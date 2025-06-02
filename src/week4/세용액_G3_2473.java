package week4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
두수를 더한 배열을 유지하고
입력:
n = 개수
전체 용액 농도

출력: 0에 가장 가까운 값

ex)
5
-2 6 -97 -6 98

-97 -2 98

풀이방법: 두수를 더한 배열을 유지하고 나머지 하나를 O(logN)타임에 찾는다
따라서 O(n^2 * logN)
 */

public class 세용액_G3_2473 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int m;
    List<Integer> arr = new ArrayList<>();
    List<Integer> ans = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        세용액_G3_2473 solve = new 세용액_G3_2473();
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
            int value = Integer.parseInt(st.nextToken());
            arr.add(value);
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        Collections.sort(arr);

        long minVal = Long.MAX_VALUE;

        for (int i = 0; i < n - 2; i++) {
            long sum;

            int l = i + 1;
            int r = n - 1;

            while (l < r) {
                sum = (long)arr.get(i) + arr.get(l) + arr.get(r);
                if (Math.abs(sum) < minVal) {
                    minVal = Math.abs(sum);
                    ans.clear();
                    ans.add(arr.get(i));
                    ans.add(arr.get(l));
                    ans.add(arr.get(r));
                }
                if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        bw.write(
                ans.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
                        .toString());
    }

}