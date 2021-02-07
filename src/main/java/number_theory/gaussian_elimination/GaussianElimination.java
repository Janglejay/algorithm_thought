package number_theory.gaussian_elimination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GaussianElimination {


    private static class Gauss {
        private final double[][] det;

        public Gauss(double[][] det) {
            this.det = det;
        }
        public int gauss() {
            int c,r;
            int n = det.length;
            double EPS = 1e-6;
            for (c = 0, r = 0; c < n; c++) {
                int t = r;
                for (int i = r; i < n; i++) {
                    if (Math.abs(det[i][c]) > Math.abs(det[t][c])) {
                        t = i;
                    }
                }
                //当前这一列为0
                if (Math.abs(det[t][c]) <= EPS) continue;
                for (int i = c; i <= n; i++) {
                    double temp = det[t][i];
                    det[t][i] = det[r][i];
                    det[r][i] = temp;
                }
                for (int i = n; i >= c; i--) {
                    det[r][i] /= det[r][c];
                }
                for (int i = r + 1; i < n; i++) {
                    if (Math.abs(det[i][c]) > EPS) {
                        for (int j = n; j >= c; j--) {
                            det[i][j] -= det[r][j] * det[i][c];
                        }
                    }
                }
                r++;
            }
            if (r < n) {
                for (int i = r; i < n; i++) {
                    //出现 0 = 非0
                    if (Math.abs(det[i][n]) > EPS) {
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
                    det[i][n] -= det[i][j] * det[j][n];
                }
            }
            //唯一解
            return 0;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        double[][] a = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                a[i][j] = in.nextDouble();
            }
        }
        Gauss gauss = new Gauss(a);
        int res = gauss.gauss();
        if (res == 0) {
            for (int i = 0; i < n; i++) {
                out.printf("%.2f\n", a[i][n]);
            }
        } else if (res == 1) {
            out.println("Infinite group solutions");
        } else if (res == 2) {
            out.println("No solution");
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
