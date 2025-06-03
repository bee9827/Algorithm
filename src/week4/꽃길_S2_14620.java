package week4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static java.lang.Math.min;

/*
BF로 풀어도 10^6 이하.
 */

public class 꽃길_S2_14620 {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;
    int m;
    int[] dx = {0, 0, 0, -1, 1};
    int[] dy = {0, 1, -1, 0, 0};
    int[][] board = new int[10][10];
    int[][] dpBoard = new int[10][10];
    int ans;


    public static void main(String[] args) throws IOException {
        꽃길_S2_14620 solve = new 꽃길_S2_14620();
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


        IntStream.range(0, n).forEach(i -> {
            StringTokenizer st2 = readLine();

            IntStream.range(0, n).forEach(j -> {
                board[i][j] = Integer.parseInt(st2.nextToken());
            });
        });
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {
        IntStream.range(0, n).forEach(i -> {
            Arrays.fill(dpBoard[i], -1);
        });

        ans = findBF(new ArrayList<>());
        bw.write(String.valueOf(ans));
    }

    private int findBF(List<Point> points) {
        int ans = Integer.MAX_VALUE;
        if (points.size() == 3) {
            if (valid(points)) {
                int sum = 0;

                for (Point p : points) {
                    sum += getAdded(p.x, p.y);
                }
                return sum;
            }
            return Integer.MAX_VALUE;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Point p = new Point(i, j);
                points.add(p);
                ans = min(ans, findBF(points));
                points.remove(points.size() - 1);
            }
        }

        return ans;
    }

    public boolean valid(List<Point> points) {
        return validEdge(points) && !isDuplicated(points);
    }

    private boolean isDuplicated(List<Point> points) {
        boolean[][] visited = new boolean[n][n];
        for (Point p : points) {
            for (int i = 0; i < dx.length; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny]) return true;
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private boolean overlap(Point target, Point other) {

        for (int i = 0; i < dx.length; i++) {
            int nx = target.x + dx[i];
            int ny = target.y + dy[i];


        }
        return false;
    }


    private boolean validEdge(List<Point> points) {
        for (Point p : points) {
            if (!validEdge(p.x, p.y)) return false;
        }
        return true;
    }

    public boolean validEdge(int i, int j) {
        return i != 0 && j != 0 && i != n - 1 && j != n - 1;
    }

    public int getAdded(int i, int j) {
        if (dpBoard[i][j] != -1) return dpBoard[i][j];

        dpBoard[i][j] = 0;
        IntStream.range(0, 5).forEach(x -> {
            int nx = i + dx[x];
            int ny = j + dy[x];
            dpBoard[i][j] += board[nx][ny];
        });

        return dpBoard[i][j];
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}