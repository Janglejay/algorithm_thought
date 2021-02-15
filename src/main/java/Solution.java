class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] arr = new int[n >> 1];
        for (int i = 0; i < arr.length; i++) {
            int first = row[(i << 1)];
            int second = row[(i << 1) + 1];
            if (((first & 1) == 0 && first == second - 1) || ((first & 1) == 1 && first == second + 1)) {
                arr[i] = 1;
            }
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                int base = arr[(i << 1)];
                int need;
                if ((base & 1) == 0) {
                    need = base + 1;
                }else {
                    need = base - 1;
                }
                if (arr[(i << 1) + 1] == need) continue;
                for (int j = 0; j < n; j++) {
                    if (row[j] == need) {
                        row[j] = need;
                        arr[(i << 1) + 1] = row[j];
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }
}