package week4;

import java.io.*;
import java.util.*;

/*

 */

public class 패션왕신해빈_S3_9375 {
    private static final char RED = 'R';
    private static final char BLUE = 'B';
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t, n, m;


    long ans;

    Map<String, List<String>>[] clothesType;

    public static void main(String[] args) throws IOException {
        패션왕신해빈_S3_9375 solve = new 패션왕신해빈_S3_9375();
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
        t = Integer.parseInt(readLine().nextToken());

        clothesType = new HashMap[t];

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(readLine().nextToken());
            clothesType[i] = new HashMap<>();

            for (int j = 0; j < n; j++) {
                StringTokenizer st = readLine();
                String name = st.nextToken();
                String type = st.nextToken();
                clothesType[i].computeIfAbsent(type, k -> new ArrayList<>())
                        .add(name);
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        for (int i = 0; i < t; i++) {
            ans = 1;
            for (List<String> clothes : clothesType[i].values()) {
                ans *= clothes.size() + 1;        //공백의 경우수 더해주기
            }
            ans -= 1;           //모두 공백인 경우 빼주기

            bw.write(ans + "\n");
        }
    }

}