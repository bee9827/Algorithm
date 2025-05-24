import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class B_S2_18352 {
    public static int MAX_DISTANCE = 300_001;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N, M, K, X;
    List<Integer>[] edge;
    int[] distance;
    List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        B_S2_18352 solve = new B_S2_18352();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }


    public void solve() throws IOException {
        bfs(X);
        ans.sort(Comparator.naturalOrder());
        bw.write(getAns());
    }

    public String getAns() {
        if(ans.isEmpty()) return "-1";
        return ans.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }

    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (distance[current] == K) {
                ans.add(current);
                continue;
            }

            for (int next : edge[current]) {
                if (distance[next] > distance[current] + 1) {
                    distance[next] = distance[current] + 1;
                    queue.add(next);
                }
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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        edge = new List[N + 1];
        Arrays.setAll(edge, i -> new ArrayList<>());

        distance = new int[N + 1];
        Arrays.fill(distance, MAX_DISTANCE);

        for (int i = 0; i < M; i++) {
            st = readLine();

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edge[from].add(to);
        }
    }

    public void output() throws IOException {
        bw.flush();
        bw.close();
    }

}