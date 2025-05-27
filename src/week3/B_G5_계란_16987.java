package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
최대로 깰 수 있는 문제
입력:
n = 개수
내구도 무게(공격력?)

출력: 최대로 깰 수 있는 개수

ex)
3
8 5
1 100
3 5

3

풀이방법: DP or BF
총 사이즈 n <= 8
내구도 무게 <= 300

BF일 경우
7! < 10^6
 */

public class B_G5_계란_16987 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int ans = 0;
    int n;
    int m;
    List<Egg> eggs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        B_G5_계란_16987 solve = new B_G5_계란_16987();
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

        for (int i = 0; i < n; i++) {
            st = readLine();
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggs.add(new Egg(durability, weight));
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        bw.write(String.valueOf(bf(eggs, 0)));
    }

    public int bf(List<Egg> eggs, int idx) {
        if (idx == eggs.size()) {
            return brokenEggsCount(eggs);
        }

        Egg attaker = eggs.get(idx);

        if (attaker.isBroken() || brokenEggsCount(eggs) == eggs.size() - 1) {
            return bf(eggs, idx + 1);
        }

        for (int i = 0; i < eggs.size(); i++) {
            if (i == idx) continue;

            Egg defender = eggs.get(i);

            if (defender.isBroken()) {
                continue;
            }

            attaker.attack(defender);
            ans = Math.max(ans, bf(eggs, idx + 1));
            attaker.recover(defender);
        }

        return ans;
    }

    public int brokenEggsCount(List<Egg> eggs) {
        return (int) eggs.stream()
                .filter(Egg::isBroken)
                .count();
    }

    public class Egg {
        private final Integer weight;
        private Integer durability;

        public Egg(Integer durability, Integer weight) {
            this.durability = durability;
            this.weight = weight;
        }

        public void attack(Egg other) {
            other.durability -= this.weight;
            this.durability -= other.weight;
        }

        public void recover(Egg other) {
            other.durability += this.weight;
            this.durability += other.weight;
        }

        public boolean isBroken() {
            return durability <= 0;
        }
    }

}