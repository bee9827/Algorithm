package week5;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*

 */

public class 지름길_S1_1446 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    ShortCut[] shortCuts;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int t, n, m, d;


    long ans;

    public static void main(String[] args) throws IOException {
        지름길_S1_1446 solve = new 지름길_S1_1446();
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
        d = Integer.parseInt(st.nextToken());

        shortCuts = new ShortCut[n];

        for (int i = 0; i < n; i++) {
            st = readLine();

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            shortCuts[i] = new ShortCut(from, to, distance);
        }


    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }
    

    public void solve() throws IOException {
        Arrays.sort(shortCuts);

        TreeMap<Integer, Integer> dpMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            ShortCut shortCut = shortCuts[i];
            int from = shortCut.from;
            int to = shortCut.to;
            int distance = shortCut.distance;

            if(to > d) break;

            if (dpMap.floorKey(from) != null) { //이하인 키가 있는지 (미만x)
                int prevPoint = dpMap.floorKey(from);
                int v = dpMap.get(prevPoint);

                if (!dpMap.containsKey(to)) {
                    int prevTo = dpMap.floorKey(to);
                    dpMap.put(to, dpMap.get(prevTo) + to - prevTo); // to 기준으로 가장 최소값
                }
                dpMap.put(to, Math.min(dpMap.get(to), v + distance + from - prevPoint)); // from 기준 가장 최소값과 to기준 가장 최소값 비교
            } else {
                if (!dpMap.containsKey(to)) {
                    if (dpMap.floorKey(to) != null) {       //from 기준보다 적을때의 dp | to기준 보다 적을때의 dp
                        int prevTo = dpMap.floorKey(to);
                        dpMap.put(to, dpMap.get(prevTo) + to - dpMap.floorKey(to)); // to 기준으로 가장 최소값
                    } else {
                        dpMap.put(to, distance + from);         // 그냥 걸어 갔을떄 길이를 넣는다.
                    }
                }
                dpMap.put(to, Math.min(dpMap.get(to), distance + from));
            }
        }

        Integer key = dpMap.floorKey(d);
        if (key != null) {
            bw.write("%d\n".formatted(dpMap.get(key) + d - key));
        }else{
            bw.write("%d\n".formatted(d));
        }


    }

    class ShortCut implements Comparable<ShortCut> {
        int from;
        int to;
        int distance;

        public ShortCut(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = Math.min(distance, to - from);
        }

        @Override
        public int compareTo(ShortCut o) {
            if (to != o.to) return to - o.to;
            if (from != o.from) return from - o.from;
            return distance - o.distance;
        }
    }
}