class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;
        long[] dp = new long[n];

        // Инициализация dp значениями первой строки
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }

        // Проходим по строкам, начиная со второй
        for (int i = 1; i < m; i++) {
            long[] left = new long[n];
            long[] right = new long[n];
            long[] newDp = new long[n];

            // Обработка слева направо
            left[0] = dp[0];
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1] - 1, dp[j]);
            }

            // Обработка справа налево
            right[n - 1] = dp[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, dp[j]);
            }

            // Обновление dp для текущей строки
            for (int j = 0; j < n; j++) {
                newDp[j] = points[i][j] + Math.max(left[j], right[j]);
            }

            dp = newDp;  // Обновление dp на основе новой строки
        }

        // Максимальный результат среди последней строки
        long maxPoints = 0;
        for (long val : dp) {
            maxPoints = Math.max(maxPoints, val);
        }

        return maxPoints;
    }
}