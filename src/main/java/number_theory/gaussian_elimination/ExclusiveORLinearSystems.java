package number_theory.gaussian_elimination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExclusiveORLinearSystems {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Gauss {
        private final int[][] det;

        public Gauss(int[][] det) {
            this.det = det;
        }
        public int gauss() {
            int c,r;
            int n = det.length;
            for (c = 0, r = 0; c < n; c++) {
                int t = r;
                for (int i = r; i < n; i++) {
                    if (det[i][c] > 0) {
                        t = i;
                        break;
                    }
                }
                if (det[t][c] == 0) continue;
                for (int i = c; i <= n; i++) {
                    int temp = det[t][i];
                    det[t][i] = det[r][i];
                    det[r][i] = temp;
                }
                for (int i = r + 1; i < n; i++) {
                    if (det[i][c] != 0) {
                        for (int j = n; j >= c; j--) {
                            det[i][j] ^= det[r][j];
                        }
                    }
                }
                r++;
            }
            if (r < n) {
                for (int i = r; i < n; i++) {
                    //出现 0 = 非0
                    if (det[i][n] != 0) {
                        //无解
                        return 2;
                    }
                }
                //无穷多组解
                return 1;
            }
            //倒着把解求出来
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    det[i][n] ^= det[i][j] & det[j][n];
                }
            }
            //唯一解
            return 0;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int[][] a = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                a[i][j] = in.nextInt();
            }
        }
        Gauss gauss = new Gauss(a);
        int res = gauss.gauss();
        if (res == 0) {
            for (int i = 0; i < n; i++) {
                out.println(a[i][n]);
            }
        } else if (res == 1) {
            out.println("Multiple sets of solutions");
        } else if (res == 2) {
            out.println("No solution");
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
