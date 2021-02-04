package search_graph_theory.staining_method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Hungary {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int n1;
    static int n2;
    static int m;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] match;
    static boolean[] st;

    private static void add(int a, int b) {
        graph.get(a).add(b);
    }

    private static boolean hungary(int x) {
        List<Integer> list = graph.get(x);
        for (int ii : list) {
            // 对于每个单次匹配这个元素已经考虑过了，就不要重复考虑
            if (!st[ii]) {
                st[ii] = true;
                // ii未匹配，或者 它匹配的元素 可以做出退让
                if (match[ii] == 0 || hungary(match[ii])) {
                    match[ii] = x;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        n1 = in.nextInt();
        n2 = in.nextInt();
        m = in.nextInt();
        match = new int[n2 + 1];
        st = new boolean[n2 + 1];
        for (int i = 0; i < n1 + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) add(in.nextInt(), in.nextInt());
        int res = 0;
        //选择任意一个点集进行枚举
        for (int i = 1; i <= n1; i++) {
            Arrays.fill(st, false);
            if (hungary(i)) res++;
        }
        out.println(res);
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
