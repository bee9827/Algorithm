package week1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_S2_2512 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    List<Integer> budget = new ArrayList<>();
    int ans = 0;

    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_S2_2512 solve = new B_S2_2512();
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

        st = readLine();
        for (int i = 0; i < n; i++) {
            budget.add(Integer.parseInt(st.nextToken()));
        }
        m = Integer.parseInt(readLine().nextToken());
    }


    public void solve() throws IOException {
        input();
        budget.sort(Integer::compareTo);


        int i = 0;

        while (n != 0 && m / n > budget.get(i)) {
            m -= budget.get(i++);
            n -= 1;
        }

        if(n == 0){
            ans = budget.get(budget.size()-1);
        }
        else{
            ans = m/n;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}