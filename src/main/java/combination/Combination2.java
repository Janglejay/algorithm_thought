package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Combination2 {
    private static class Combination {
        private long[] fact;
        private long[] inFact;
        private final int n;
        private final int MOD = (int) (1e9 + 7);
        private int fastPower(long a, long b, long q) {
            long res = 1L;
            while (b > 0) {
                if ((b & 1) == 1) {
                    res = res * a % q;
                }
                b >>= 1;
                a = a * a % q;
            }
            return (int) res;
        }

        public Combination(int n) {
            this.n = n;
            init();
        }
        private void init() {
            fact = new long[n];
            inFact = new long[n];
            fact[0] = inFact[0] = 1;
            for (int i = 1; i < n; i++) {
                fact[i] = fact[i - 1] * i % MOD;
                inFact[i] = inFact[i - 1] * fastPower(i, MOD - 2, MOD) % MOD;
            }
        }
        public int com(int a, int b) {
            return (int)(fact[a] * inFact[b] % MOD * inFact[a - b] % MOD);
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        Combination c = new Combination((int) (1e5 + 10));
        while (n-- > 0) {
            out.println(c.com(in.nextInt(), in.nextInt()));
        }
        out.flush();
        out.close();
    }
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

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
