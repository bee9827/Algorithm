import java.io.*;
import java.util.*;

public class B_G4_21939 {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N, M;
    TreeMap<Integer, TreeSet<Integer>> difficultyLevel = new TreeMap<>();
    HashMap<Integer, Integer> problem = new HashMap<>();
    List<List<String>> commands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        B_G4_21939 solve = new B_G4_21939();
        solve.init();
    }

    public void init() throws IOException {
        input();
        solve();
        output();
    }


    public void solve() throws IOException {
        for (List<String> command : commands) {
            switch (command.get(0)) {
                case "add" -> add(Integer.parseInt(command.get(1)), Integer.parseInt(command.get(2)));
                case "solved" -> remove(Integer.parseInt(command.get(1)));
                case "recommend" -> bw.write(String.format("%d%n", get(Recommend.valueOf(Integer.parseInt(command.get(1))))));

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

    public void add(Integer problem, Integer level) {
        this.problem.put(problem, level);
        difficultyLevel.computeIfAbsent(level, k -> new TreeSet<>()).add(problem);
    }

    public void remove(Integer problem) {
        int level = this.problem.get(problem);
        this.problem.remove(problem);

        difficultyLevel.get(level).remove(problem);
        if(difficultyLevel.get(level).isEmpty()) difficultyLevel.remove(level);
    }

    public int get(Recommend recommend) {
        switch (recommend){
            case easiest:
                return difficultyLevel.firstEntry().getValue().first();
            case hardest:
                return difficultyLevel.lastEntry().getValue().last();

        }
        return -1;
    }

    public void input() {
        StringTokenizer st = readLine();
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = readLine();
            int problem = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            add(problem, level);
        }
        st = readLine();

        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = readLine();

            commands.add(new ArrayList<>());
            while (st.hasMoreTokens()) {
                commands.get(i).add(st.nextToken());
            }
        }
    }

    private void output() throws IOException {
        bw.flush();
        bw.close();
    }

    private enum Recommend {
        hardest(1),
        easiest(-1);

        private final Integer value;

        Recommend(Integer value) {
            this.value = value;
        }

        public static Recommend valueOf(Integer value) {
            return Arrays.stream(Recommend.values())
                    .filter(v -> v.getValue().equals(value))
                    .findAny()
                    .orElse(null);
        }

        public Integer getValue() {
            return value;
        }
    }
}