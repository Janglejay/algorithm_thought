package dynamic_programming2.longest_ascending_subsequence_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MissileDefenseSystem {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static List<Integer> upSystem = new ArrayList<>();
    private static List<Integer> downSystem = new ArrayList<>();
    private static int res;
    private static int[] arr;
    private static void dfs(int total, int upNumber, int downNumber, int cur) {
        if (res <= upNumber + downNumber) return;
        if (total == cur) {
            res = upNumber + downNumber;
            return;
        }
        int k = -1;
        for (int i = 0; i < upSystem.size(); i++) {
            if (arr[upSystem.get(i)] > arr[cur]) {
                k = i;
                break;
            }
        }
        //搜放在up system的情况
        if (k == -1) {
            upSystem.add(cur);
            dfs(total, upNumber + 1, downNumber, cur + 1);
            upSystem.remove(upSystem.size() - 1);
        }else {
            int temp = upSystem.get(k);
            upSystem.set(k, cur);
            dfs(total, upNumber, downNumber, cur + 1);
            upSystem.set(k, temp);
        }
        k = -1;
        for (int i = 0; i < downSystem.size(); i++) {
            if (arr[downSystem.get(i)] < arr[cur]) {
                k = i;
                break;
            }
        }
        //搜放在down system的情况
        if (k == -1) {
            downSystem.add(cur);
            dfs(total, upNumber, downNumber + 1, cur + 1);
            downSystem.remove(downSystem.size() - 1);
        }else {
            int temp = downSystem.get(k);
            downSystem.set(k, cur);
            dfs(total, upNumber, downNumber, cur + 1);
            downSystem.set(k, temp);
        }
    }

    public static void main(String[] args) {
        int n;
        while ((n = in.nextInt()) != 0) {
            arr = new int[n];
            in.nextIntegerArray(arr);
            res = Integer.MAX_VALUE;
            dfs(n, 0, 0, 0);
            out.println(res);
        }
        out.flush();
        out.close();
    }

    private static class MyWriter {

        private final PrintWriter out;

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
        private final BufferedReader br;
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
