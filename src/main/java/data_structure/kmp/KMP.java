package data_structure.kmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class KMP {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        function1();
        function2();
    }
    private static void function1() {
        int n = in.nextInt();
        String pattern = in.next();
        char[] cp = new char[n + 1];
        for (int i = 1; i < cp.length; i++) {
            cp[i] = pattern.charAt(i - 1);
        }
        int m = in.nextInt();
        String str = in.next();
        char[] cs = new char[m + 1];
        for (int i = 1; i < cs.length; i++) {
            cs[i] = str.charAt(i - 1);
        }
        int[] nextArray = getNextArray1(cp);
        for (int i = 1, j = 0; i < cs.length; i++) {
            while (j != 0 && cs[i] != cp[j + 1]) {
                j = nextArray[j];
            }
            if (cs[i] == cp[j + 1]) {
                j++;
            }
            if (j == n) {
                out.print(i - n + " ");
                j = nextArray[j];
            }
        }
        out.flush();
        out.close();
    }

    private static int[] getNextArray1(char[] cs) {
        int[] next = new int[cs.length];
        for (int i = 2, j = 0; i < cs.length; i++) {
            while (j != 0 && cs[i] != cs[j + 1]) {
                j = next[j];
            }
            if (cs[i] == cs[j + 1]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    public static void function2() {
        int n = in.nextInt();
        String pattern = in.next();
        char[] cp = pattern.toCharArray();
        int m = in.nextInt();
        String str = in.next();
        char[] cs = str.toCharArray();
        int[] nextArray = getNextArray2(cp);
        for (int i = 0, j = -1; i < m; i++) {
            while (j != -1 && cs[i] != cp[j + 1]) {
                j = nextArray[j];
            }
            if (cs[i] == cp[j + 1]) {
                j++;
            }
            if (j == n - 1) {
                out.print(i - n + 1 + " ");
                j = nextArray[j];
            }
        }
        out.flush();
        out.close();
    }
    private static int[] getNextArray2(char[] cs) {
        int[] next = new int[cs.length];
        next[0] = -1;
        for (int i = 1, j = -1; i < cs.length; i++) {
            while (j != -1 && cs[i] != cs[j + 1]) {
                j = next[j];
            }
            if (cs[i] == cs[j + 1]) {
                j++;
            }
            next[i] = j;
        }
        return next;
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
    private static class MyOptions {
        private static double doubleAdd(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.add(bigDecimal2).doubleValue();
        }

        private static double subtractDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.subtract(bigDecimal2).doubleValue();
        }

        private static double divideDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.divide(bigDecimal2).doubleValue();
        }

        private static double multiplyDouble(double x, double y) {
            BigDecimal bigDecimal = new BigDecimal(x);
            BigDecimal bigDecimal2 = new BigDecimal(y);
            return bigDecimal.multiply(bigDecimal2).doubleValue();
        }

        private static int[] findMax(int[] arr) {
            int max = Integer.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (max < arr[i]) {
                    index = i;
                    max = arr[i];
                }
            }
            return new int[]{max, index};
        }

        private static int[] findMin(int[] arr) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (min > arr[i]) {
                    index = i;
                    min = arr[i];
                }
            }
            return new int[]{min, index};
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
