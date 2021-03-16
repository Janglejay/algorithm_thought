class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateMatrix(3);
    }
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int cur = 0;
        int x = 0;
        int y = 0;
        int top = 0;
        int right = n - 1;
        int bottom = n - 1;
        int left = 0;
        for (int i = 1; i <= n * n; i++) {
            res[x][y] = i;
            x += dx[cur];
            y += dy[cur];
            if (x >= right) {
                top++;
                cur = (cur + 1) % 4;
            }else if (y >= bottom) {
                right--;
                cur = (cur + 1) % 4;
            }else if (x <= left) {
                bottom--;
                cur = (cur + 1) % 4;
            }else if (y <= top) {
                left++;
                cur = (cur + 1) % 4;
            }
        }
        return res;
    }
}