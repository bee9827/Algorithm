package week1;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_S4_3986 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] arr;
    long ans;
    int n;
    int m;

    public static void main(String[] args) throws IOException {
        B_S4_3986 solve = new B_S4_3986();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }

    public void solve() {
        Stack<Integer> stack = new Stack<>();

        ans = n;
        for (String str : arr) {
            for(int i=0; i<str.length(); i++) {
                int idxValue = str.charAt(i)-'A';
                if(stack.empty() || stack.peek() != idxValue)
                    stack.push(idxValue);
                else if (stack.peek() == idxValue)
                    stack.pop();
            }
            if(!stack.empty()){
                ans--;
                stack.clear();
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

        arr = new String[n];
        for (int i = 0; i < n; i++) {
            st = readLine();
            arr[i] = st.nextToken();
        }
    }

    private void output() throws IOException {
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}