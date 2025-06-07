package week4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*

 */

public class 기차가어둠을헤치고은하수를_S2_15787 {
    private static final int SIZE = 20;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int m;

    long ans;
    int[] arr;
    int[][] command;

    public static void main(String[] args) throws IOException {
        기차가어둠을헤치고은하수를_S2_15787 solve = new 기차가어둠을헤치고은하수를_S2_15787();
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

        arr = new int[n];
        command = new int[m][3];

        IntStream.range(0, m).forEach(i -> {
            StringTokenizer st2 = readLine();
            int cnt = 0;
            while (st2.hasMoreTokens()) {
                command[i][cnt++] = Integer.parseInt(st2.nextToken());
            }
        });
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        for (int i = 0; i < m; i++) {
            int commandType = command[i][0];
            int idx = command[i][1] - 1;
            int shiftCount = command[i][2] - 1;

            switch (commandType) {
                case 1 -> board(arr,idx, shiftCount);
                case 2 -> getOff(arr,idx, shiftCount);
                case 3 -> pushBack(arr,idx);
                case 4 -> pushFront(arr,idx);
            }
        }

        ans = Arrays.stream(arr)
                .distinct()
                .count();

        bw.write(String.valueOf(ans + "\n"));
    }

    public int board(int[] arr,int idx, int shiftCount) {
        return arr[idx] |= shift(shiftCount);
    }

    public int getOff(int[] arr,int idx, int shiftCount) {
        return arr[idx] &= ~shift(shiftCount);
    }

    public int pushBack(int[] arr, int idx) {
        arr[idx] <<= 1;
        return arr[idx] &= shift(20) - 1;
    }

    public int pushFront(int[] arr, int idx) {
        return arr[idx] >>= 1;
    }

    private int shift(int shiftCount) {
        return 1 << shiftCount;
    }

}