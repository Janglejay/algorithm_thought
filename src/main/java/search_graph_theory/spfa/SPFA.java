package search_graph_theory.spfa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class SPFA {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n;
    static int m;
    static int[] dist;
    static boolean[] inQueue;
    static List<List<Pair>> graph = new ArrayList<>();
    private static void add(int a, int b, int v) {
        graph.get(a).add(new Pair(b, v));
    }
    private static class Pair{
        private int position;
        private int weight;

        public Pair(int position, int weight) {
            this.position = position;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        dist = new int[n + 1];
        inQueue = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            add(in.nextInt(), in.nextInt(), in.nextInt());
        }
        int res = spfa();
        if (res == -1) {
            out.println("impossible");
        }else {
            out.println(res);
        }
        out.flush();
        out.close();
    }
    private static int spfa() {
        dist[1] = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(1, 0));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            inQueue[p.position] = false;
            List<Pair> list = graph.get(p.position);
            for (Pair pp : list) {
                if (dist[pp.position] > dist[p.position] + pp.weight) {
                    dist[pp.position] = dist[p.position] + pp.weight;
                    queue.add(pp);
                    inQueue[pp.position] = true;
                }
            }
        }
        if (dist[n] >= Integer.MAX_VALUE / 4) return -1;
        return dist[n];
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
