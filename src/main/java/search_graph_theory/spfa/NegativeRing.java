package search_graph_theory.spfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class NegativeRing {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int[] dist;
    static int[] count;
    static boolean[] isInQueue;
    static List<List<Pair>> graph = new ArrayList<>();

    static class Pair {
        int position;
        int weight;

        public Pair(int position, int weight) {
            this.position = position;
            this.weight = weight;
        }
    }

    private static void add(int a, int b, int c) {
        graph.get(a).add(new Pair(b, c));
    }

    private static boolean spfa() {
        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++)
            queue.add(new Pair(i, 0));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            isInQueue[p.position] = false;
            List<Pair> list = graph.get(p.position);
            for (Pair pp : list) {
                if (dist[pp.position] > p.weight + pp.weight) {
                    dist[pp.position] = p.weight + pp.weight;
                    count[pp.position] = count[p.position] + 1;
                    if (count[pp.position] >= n) {
                        return true;
                    }
                    if (!isInQueue[pp.position])
                        queue.add(new Pair(pp.position, dist[pp.position]));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        dist = new int[n + 1];
        count = new int[n + 1];
        isInQueue = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        while (m-- > 0) {
            add(in.nextInt(), in.nextInt(), in.nextInt());
        }
        boolean res = spfa();
        if (res) {
            out.println("Yes");
        } else
            out.println("No");
        out.flush();
        out.close();
    }
    private static class MyWriter {

        private PrintWriter out;

        private MyWriter() {
            out = new PrintWriter(System.out);
        }

        private void printlnArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i]);
            }
        }

        private void printArrayJoinSpace(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i] + " ");
            }
        }

        private void printlnArray(int[] arr) {
            for (int x : arr) {
                out.println(x);
            }
        }

        private void printArrayJoinSpace(int[] arr) {
            for (int i : arr) {
                out.print(i + " ");
            }
        }

    }

    private static class MyScanner {
        private BufferedReader br;
        private StringTokenizer st;

        private MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }

        private long nextLong() {
            return Long.parseLong(next());
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        private String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        private void nextIntegerArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                arr[i] = nextInt();
            }
        }

        private void nextIntegerArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextInt();
            }
        }
    }

}
