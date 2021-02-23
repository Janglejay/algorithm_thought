package basic_algorithm2.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class InexplicableSwitch {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static String[] ss = new String[5];
    static char[][] base = new char[5][5];
    static char[][] bak = new char[5][5];
    static int[] dx = new int[]{-1, 0, 1, 0, 0};
    static int[] dy = new int[]{0, 1, 0, -1, 0};
    private static void turn(int x, int y) {
        for (int i = 0; i < 5; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
            if (a < 0 || a > 4 || b < 0 || b > 4) continue;
            bak[a][b] ^= 1;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        ss = new String[5];
        base = new char[5][5];
        bak = new char[5][5];
        while (n-- > 0) {
            for (int i = 0; i < 5; i++) ss[i] = in.next();
            for (int i = 0; i < 5; i++) base[i] = ss[i].toCharArray();
            int res = Integer.MAX_VALUE;
            // 枚举所有第一行所有状态
            for (int op = 0; op < 32; op++) {
                int count = 0;
                for (int i = 0; i < 5; i++)
                    System.arraycopy(base[i], 0, bak[i], 0, 5);
                for (int i = 0; i < 5; i++) {
                    if ((op >> i & 1) == 1) {
                        turn(0, i);
                        count++;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (bak[i][j] == '0') {
                            //按下他的下一个开关
                            turn(i + 1, j);
                            count++;
                        }
                    }
                }
                // 检查最后一行是否全亮
                boolean success = true;
                for (int i = 0; i < 5; i++) {
                    if (bak[4][i] == '0') {
                        success = false;
                    }
                }
                if (success && res > count) res = count;
            }
            if (res <= 6) out.println(res);
            else out.println(-1);
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
