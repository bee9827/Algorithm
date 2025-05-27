package week2;

import java.io.*;
import java.util.*;

public class B_S2_2503 {
    public static int MAX_DISTANCE = 300_001;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int N;
    private TreeSet<String> ansList = new TreeSet<>();
    private Set<String> ans = new HashSet<>();
    private List<Question> question = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        B_S2_2503 solve = new B_S2_2503();
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
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = readLine();

            String number = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            question.add(new Question(number, strike, ball));
        }
    }

    public void output() throws IOException {
        bw.flush();
        bw.close();
    }


    public void solve() throws IOException {
        fillBruteForce();
        for(String ans : ansList) {
            if(check(ans)){
                this.ans.add(ans);
            };
        }
        bw.write(String.valueOf(ans.size()));
    }

    public boolean check(String ans) {
        for (int i = 0; i < question.size(); i++) {
            Question q = question.get(i);
            if(!q.isEqual(getStrike(ans, q.number), getBall(ans, q.number))) return false;
        }
        return true;
    }

    public void fillBruteForce() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if(i == j) continue;
                for(int k = 1; k <= 9; k++){
                    if(i == k || j == k) continue;
                    ansList.add(String.format("%d%d%d", i, j, k));
                }
            }
        }
    }

    public int getStrike(String ans, String number){
        int count = 0;
        for(int i=0; i<number.length(); i++){
            if(ans.charAt(i) == number.charAt(i)) count++;
        }
        return count;
    }

    public int getBall(String ans, String number){
        int count = 0;
        for(int i=0; i<number.length(); i++){
            for(int j=0; j<number.length(); j++){
                if(i == j) continue;

                if(ans.charAt(i) == number.charAt(j)) count++;
            }
        }

        return count;
    }


    private class Question {
        String number;
        int strike;
        int ball;

        public Question(String number, int strike, int ball) {
            this.number = number;
            this.strike = strike;
            this.ball = ball;
        }

        public boolean isEqual(int strike,int ball){
            return this.strike == strike && this.ball == ball;
        }
    }

}