package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B_S5_4659 {
    public static final int LIMIT_OF_CONTINUOUS_TYPE = 3;
    public static final int LIMIT_OF_CONTINUOUS = 2;
    public static final int VOWEL = 1;
    public static final int CONSONANT = 2;
    public static final List<Character> CONTINUOUS_EXCEPTION = List.of('e', 'o');
    public static final List<Character> VOWELS = List.of('a', 'e', 'i', 'o', 'u');
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    List<String> pwdList;
    List<String> ans;

    public static void main(String[] args) throws IOException {
        B_S5_4659 solve = new B_S5_4659();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }


    public void solve() throws IOException {
        for (String pwd : pwdList) {
            if (checkQualify(pwd)) bw.write(String.format("<%s> is acceptable.%n", pwd));
            else bw.write(String.format("<%s> is not acceptable.%n", pwd));
        }
    }

    public boolean checkQualify(String pwd) {
        if (!containsVowel(pwd)) return false;
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if (isContinuous(pwd, i) || isContinuousType(pwd, i)) return false;
        }

        return true;
    }

    private boolean isContinuousType(String pwd, int i) {
        for (int j = 1; j < LIMIT_OF_CONTINUOUS_TYPE; j++) {
            if (i - j < 0) return false;
            if (getType(pwd.charAt(i)) != getType(pwd.charAt(i - j))) return false;
        }
        return true;
    }

    private boolean isContinuous(String pwd, int i) {
        for (int j = 1; j < LIMIT_OF_CONTINUOUS; j++) {
            if (i - j < 0) return false;
            if (pwd.charAt(i) != pwd.charAt(i - j)) return false;
            else if (isExcept(pwd.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isExcept(char c) {
        return CONTINUOUS_EXCEPTION.stream()
                .anyMatch(x -> x == c);
    }

    private int getType(char c) {
        if (VOWELS.contains(c)) return VOWEL;
        return CONSONANT;
    }

    private boolean containsVowel(String pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);

            if (getType(c) == VOWEL) return true;
        }
        return false;
    }


    public StringTokenizer readLine() {
        try {
            return new StringTokenizer(br.readLine());
        } catch (IOException e) {
            throw new IllegalArgumentException("읽어오지 못했습니다.");
        }
    }

    public void input() {
        StringTokenizer st;

        pwdList = new ArrayList<>();

        while (true){
            st = readLine();
            String pwd = st.nextToken();
            if("end".equals(pwd)) break;
            pwdList.add(pwd);
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }
}