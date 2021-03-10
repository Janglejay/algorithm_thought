package dynamic_programming2.longest_ascending_subsequence_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ChorusFormation {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        int[] leftToRightDp = new int[n + 1];
        int[] rightToLeftDp = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = in.nextInt();
        for (int i = 1; i <= n; i++) {
            leftToRightDp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    leftToRightDp[i] = Math.max(leftToRightDp[i], leftToRightDp[j] + 1);
                }
            }
        }
        for (int i = n; i >= 1; i--) {
            rightToLeftDp[i] = 1;
            for (int j = n; j > i; j--) {
                if (arr[j] < arr[i]) {
                    rightToLeftDp[i] = Math.max(rightToLeftDp[i], rightToLeftDp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n + 1; i++) res = Math.max(res, leftToRightDp[i] + rightToLeftDp[i] - 1);
        out.println(n - res);
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
