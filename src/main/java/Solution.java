class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }

    public String minWindow(String s, String t) {
        int need = 0;
        int[] t1 = new int['z' - 'A' + 1];
        int[] t2 = new int['z' - 'A' + 1];
        for (char c : t.toCharArray()) {
            if (t1[c - 'A'] == 0) need++;
            t1[c - 'A']++;
        }
        String res = "";
        for (int i = 0, j = 0; j < s.length(); j++) {
            int idx = s.charAt(j) - 'A';
            t2[idx]++;
            if (t2[idx] == t1[idx]) need--;
            while (need == 0) {
                if ("".equals(res) || j - i + 1 < res.length()) {
                    res = s.substring(i, j + 1);
                }
                int index = s.charAt(i) - 'A';
                if (t1[index] == t2[index]) need++;
                t2[index]--;
                i++;
            }
        }
        return res;
    }
}