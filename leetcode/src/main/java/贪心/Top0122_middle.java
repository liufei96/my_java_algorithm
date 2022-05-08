package 贪心;

public class Top0122_middle {

    public static void main(String[] args) {
        Top0122_middle top0122_middle = new Top0122_middle();
        int[] prices = { 7,1,5,3,6,4 };
        int ans = top0122_middle.maxProfit(prices);
        System.out.println(ans);
        System.out.println(top0122_middle.maxProfit2(prices));
        System.out.println(top0122_middle.maxProfit3(prices));
    }


    /**
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] <= prices[i - 1]) {
                dp[i] = Math.max(dp[i -  1], 0);
            } else {
                dp[i] = prices[i] - prices[i - 1] + dp[i - 1];
            }
        }
        return dp[n - 1];
    }

    /**
     * 动态规划优化
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int dp_0 = 0, dp_1 = -prices[0];
        for (int i = 1; i < n; i++) {
            dp_0 = Math.max(dp_1 + prices[i], dp_0);
            dp_1 = Math.max(dp_0 - prices[i], dp_1);
        }
        return dp_0;
    }

    /**
     * 贪心算法
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
           ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
