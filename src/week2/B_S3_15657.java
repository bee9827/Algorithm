package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class B_S3_15657 {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N, M;
    List<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        B_S3_15657 solve = new B_S3_15657();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }


    public void solve() throws IOException {
        Collections.sort(arr);
        for(int i=0; i<N; i++) {
            permutation(i, 1, new ArrayList<>());
        }

    }


    public void permutation(int idx, int depth, List<Integer> ans) throws IOException {
        ans.add(arr.get(idx));
        if (depth == M) {
            bw.write(toString(ans) + "\n");
            ans.remove(ans.size() - 1);
            return;
        }
        for (int i = idx; i < N; i++) {
            permutation(i, depth + 1, ans);
        }
        ans.remove(ans.size() - 1);
    }

    private String toString(List<Integer> ans) {
        return ans.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = readLine();
        for(int i=0; i<N; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }
}