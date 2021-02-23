import java.util.Arrays;

class Solution {
    public int longestPalindrome(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (i == 1 || a.charAt(n - i + 1) == b.charAt(j - 1))
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                if (j == 1 || a.charAt(n - i) == b.charAt(j - 2))
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1] + 1);
                if ((i == 1 && j == 1) || a.charAt(n - i) == b.charAt(j - 1))
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 2);
            }
        }
        return dp[n][m] == 1 ? 0 : dp[n][m];
    }
}