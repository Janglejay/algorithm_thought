package number_theory.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SieveMethod {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static List<Integer> primes = new ArrayList<>();
    static boolean[] st;
    static int[] phi;
    private static void euler(int x) {
        phi[1] = 1;
        for (int i = 2; i <= x; i++) {
            if (!st[i]) {
                primes.add(i);
                phi[i] = i - 1;
            }
            for (int pj = 0; primes.get(pj) <= x / i; pj++) {
                st[primes.get(pj) * i] = true;
                if (i % primes.get(pj) == 0) {
                    phi[primes.get(pj) * i] = phi[i] * primes.get(pj);
                    break;
                }else {
                    phi[primes.get(pj) * i] = phi[i] * (primes.get(pj) - 1);
                }
            }
        }
    }
    public static void main(String[] args) {
        int n = in.nextInt();
        st = new boolean[n + 1];
        phi = new int[n + 1];
        euler(n);
        long res = 0L;
        for (int i = 1; i <= n; i++) {
            res += phi[i];
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
