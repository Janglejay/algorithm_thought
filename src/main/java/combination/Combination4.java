package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Combination4 {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        Combination c = new Combination();
        String res = c.com(in.nextInt(), in.nextInt());
        out.println(res);
        out.flush();
        out.close();
    }
    private static class Combination{
        private int[] primes;
        private int[] sum;
        private int count;
        private boolean[] st;

        private String com(int a, int b) {
            initPrimes(a);
            initSum(a, b);
            BigInteger res = new BigInteger("1");
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < sum[i]; j++) {
                    res = res.multiply(BigInteger.valueOf(primes[i]));
                }
            }
            return res.toString();
        }
        private void initPrimes(int n){
            primes = new int[n + 1];
            st = new boolean[n + 1];
            for (int i = 2; i <= n; i++) {
                if (!st[i]) primes[count++] = i;
                for (int j = 0; primes[j] <= n / i; j++) {
                    st[primes[j] * i] = true;
                    if (i % primes[j] == 0) break;
                }
            }
        }
        private void initSum(int a, int b) {
            sum = new int[count];
            for (int i = 0; i < count; i++) {
                int p = primes[i];
                sum[i] = getNumber(a, p) - getNumber(b, p) - getNumber(a - b, p);
            }
        }
        //得到n中质因子p的个数
        private int getNumber(int n, int p) {
            int res = 0;
            while (n > 0) {
                res += n / p;
                n /= p;
            }
            return res;
        }
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
