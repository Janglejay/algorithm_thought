package number_theory.sysconvert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class AConvertB {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static void function1() {
        int n = in.nextInt();
        while (n-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            String str = in.next();
            String base10 = base10(str, a);
            String baseB = base(base10, b);
            out.println(a + " " + str);
            out.println(b + " " + baseB);
            out.println();
        }
        out.flush();
        out.close();
    }

    private static int uget(char c) {
        if (c <= '9') return c - '0';
        if (c <= 'Z') return c - 'A' + 10;
        return c - 'a' + 36;
    }

    private static String base10(String num, int b) {
        BigInteger res = new BigInteger("0");
        for (char c : num.toCharArray()) {
            res = res.multiply(BigInteger.valueOf(b)).add(BigInteger.valueOf(uget(c)));
        }
        return res.toString();
    }

    private static char get(int x) {
        if (x <= 9) return (char) (x + '0');
        if (x <= 35) return (char) (x - 10 + 'A');
        return (char) (x - 36 + 'a');
    }

    public static String base(String n, int b) {
        StringBuilder num = new StringBuilder();
        BigInteger bn = new BigInteger(n);
        BigInteger bb = BigInteger.valueOf(b);
        while (bn.compareTo(BigInteger.valueOf(0)) > 0) {
            num.append(get(bn.mod(bb).intValue()));
            bn = bn.divide(bb);
        }
        num.reverse();
        if (num.length() == 0) num.append(0);
        return num.toString();
    }


    private static void function2() {
        int n = in.nextInt();
        List<Integer> number = new ArrayList<>();
        while (n-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            String baseA = in.next();
            for (char c : baseA.toCharArray()) {
                if (c >= '0' && c <= '9') number.add(c - '0');
                if (c >= 'A' && c <= 'Z') number.add(c - 'A' + 10);
                if (c >= 'a' && c <= 'z') number.add(c - 'a' + 36);
            }
            List<Integer> res = new ArrayList<>();
            while (number.size() > 0) {
                int r = 0;
                for (int i = 0; i < number.size(); i++) {
                    number.set(i, number.get(i) + r * a);
                    r = number.get(i) % b;
                    number.set(i, number.get(i) / b);
                }
                res.add(r);
                while (number.size() > 0 && number.get(0) == 0) number.remove(0);
            }
            Collections.reverse(res);
            StringBuilder sb = new StringBuilder();
            for (int x : res) {
                if (x <= 9) sb.append(x);
                if (x >= 10 && x <= 35) sb.append((char) (x - 10 + 'A'));
                if (x >= 36) sb.append((char) (x - 36 + 'a'));
            }
            out.println(a + " " + baseA);
            out.println(b + " " + sb.toString());
            out.println();
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
//        function1();
        function2();
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
