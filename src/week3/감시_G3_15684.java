package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
기본 아이디어: Brute force
이유: 8*8 사이즈의 보드판을 8개의 cctv 에 대해서 진행할 경우 (최대값)
한대의 cctv가 가질수 있는 경우의 수는 4가지 (방향)

4^8 * 8 * 8 = 2^22 즉  10^6 * 4 정도의 값

CCTV의 개수를 t라고 할떄

O(4^t * m * n)
 */

public class 감시_G3_15684 {
    public static final int WALL = 6;
    public static final int EMPTY = 0;
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    long ans = 64;
    int n;
    int m;
    int[][] board;

    public static void main(String[] args) throws IOException {
        감시_G3_15684 solve = new 감시_G3_15684();
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

        board = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = readLine();
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    public void solve() throws IOException {

        bf(getLocationOfCCTV());
        bw.write(String.valueOf(ans));
    }

    private List<Location> getLocationOfCCTV() {
        List<Location> cctvLocation = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (CCTV_TYPE.isValid(board[i][j])) {
                    cctvLocation.add(new Location(i, j));
                }
            }
        }
        return cctvLocation;
    }

    public void bf(List<Location> cctvLocation) {
        if (cctvLocation.isEmpty()) {
            long empty = countEmpty(board);
            if(ans > empty)
                ans = countEmpty(board);
            return;
        }
        int count = 0;

        int cctvOrder = cctvLocation.size();

        int x = cctvLocation.get(cctvLocation.size() - 1).x;
        int y = cctvLocation.get(cctvLocation.size() - 1).y;


        CCTV_TYPE cctvType = CCTV_TYPE.valueOf(board[x][y]);
        cctvLocation.remove(cctvLocation.size() - 1);

        for (List<DIRECTION> directions : cctvType.getDirectionCombinations()) {
            fill(board, x, y, directions, cctvOrder);
            bf(cctvLocation);
            unfill(board, x, y, directions, cctvOrder);
        }

        board[x][y] = cctvType.getNumber();
        cctvLocation.add(new Location(x, y));
    }

    private long countEmpty(int[][] board) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == EMPTY) count++;
            }
        }
        return count;
    }

    public void unfill(int[][] board, int i, int j, List<DIRECTION> directions, int order) {
        for (DIRECTION direction : directions) {
            int dx = direction.getDx();
            int dy = direction.getDy();

            int nx = i;
            int ny = j;

            if(nx==5 && ny==5){
                board[nx][ny] = -1;
            }

            while (true) {
                nx += dx;
                ny += dy;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == WALL) break;
                if(board[nx][ny] == -order) board[nx][ny] = EMPTY;
            }
        }
    }

    public void fill(int[][] board, int x, int y, List<DIRECTION> directions, int order) {
        for (DIRECTION direction : directions) {
            int dx = direction.getDx();
            int dy = direction.getDy();

            int nx = x;
            int ny = y;

            while (true) {

                nx += dx;
                ny += dy;


                if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == WALL) break;
                if (board[nx][ny] == EMPTY) board[nx][ny] = -order;
            }
        }
    }

    public enum CCTV_TYPE {
        FIRST(1, List.of(List.of(DIRECTION.UP), List.of(DIRECTION.DOWN), List.of(DIRECTION.LEFT), List.of(DIRECTION.RIGHT))),
        SECOND(2, List.of(List.of(DIRECTION.DOWN, DIRECTION.UP), List.of(DIRECTION.LEFT, DIRECTION.RIGHT))),
        THIRD(3, List.of(List.of(DIRECTION.UP, DIRECTION.RIGHT), List.of(DIRECTION.UP, DIRECTION.LEFT), List.of(DIRECTION.DOWN, DIRECTION.RIGHT), List.of(DIRECTION.DOWN, DIRECTION.LEFT))),
        FOURTH(4, List.of(List.of(DIRECTION.UP, DIRECTION.LEFT, DIRECTION.RIGHT), List.of(DIRECTION.UP, DIRECTION.RIGHT, DIRECTION.DOWN), List.of(DIRECTION.DOWN, DIRECTION.LEFT, DIRECTION.RIGHT), List.of(DIRECTION.DOWN, DIRECTION.LEFT, DIRECTION.UP))),
        FIFTH(5, List.of(List.of(DIRECTION.UP, DIRECTION.RIGHT, DIRECTION.DOWN, DIRECTION.LEFT)));

        private final Integer number;
        private final List<List<DIRECTION>> directionCombinations;

        CCTV_TYPE(Integer number, List<List<DIRECTION>> directionCombinations) {
            this.number = number;
            this.directionCombinations = directionCombinations;
        }

        public static boolean isValid(int inputNumber) {
            return Arrays.stream(values())
                    .anyMatch(n -> n.number.equals(inputNumber));
        }

        public static CCTV_TYPE valueOf(int inputNumber) {
            return Arrays.stream(values())
                    .filter(n -> n.number.equals(inputNumber))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 번호 입력입니다."));
        }

        public Integer getNumber() {
            return number;
        }

        public List<List<DIRECTION>> getDirectionCombinations() {
            return directionCombinations;
        }
    }

    public enum DIRECTION {
        UP(0, 1),
        DOWN(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        private final int dx;
        private final int dy;

        DIRECTION(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }

    private class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}