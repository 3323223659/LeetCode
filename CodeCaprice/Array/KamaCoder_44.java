package CodeCaprice.Array;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/18/23:12
 * @Description:@Description: 44. 开发商购买土地
 * 链接：https://kamacoder.com/problempage.php?pid=1044
 */

/**
 * 在一个城市区域内，被划分成了n * m个连续的区块，每个区块都拥有不同的权值，代表着其土地价值。目前，有两家开发公司，A 公司和 B 公司，希望购买这个城市区域的土地。
 * 现在，需要将这个城市区域的所有区块分配给 A 公司和 B 公司。
 * TODO:然而，由于城市规划的限制，只允许将区域按横向或纵向划分成两个子区域，而且每个子区域都必须包含一个或多个区块。
 * 为了确保公平竞争，你需要找到一种分配方式，使得 A 公司和 B 公司各自的子区域内的土地总价值之差最小。
 * 注意：区块不可再分。
 * 输入描述
 * 第一行输入两个正整数，代表 n 和 m。
 * 接下来的 n 行，每行输出 m 个正整数。
 * 输出描述
 * 请输出一个整数，代表两个子区域内土地总价值之间的最小差距。
 * 输入示例
 * 3 3
 * 1 2 3
 * 2 1 3
 * 1 2 3
 * 输出示例
 * 0
 * 提示信息
 * 如果将区域按照如下方式划分：
 * 1 2 | 3
 * 2 1 | 3
 * 1 2 | 3
 * 两个子区域内土地总价值之间的最小差距可以达到 0。
 * 数据范围：
 * 1 <= n, m <= 100；
 * n 和 m 不同时为 1。
 */
public class KamaCoder_44 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入土地行数：");
        int n = sc.nextInt();
        System.out.println("请输入土地列数：");
        int m = sc.nextInt();
        int[][] land = new int[n][m];  // 土地价值矩阵
        int totalValue = 0;           // 土地总价值
        // 读取土地价值并计算总价值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println("请输入第" + (i + 1) + "行第" + (j + 1) + "列的土地价值：");
                land[i][j] = sc.nextInt();
                totalValue += land[i][j];
            }
        }
        sc.close();
        // 打印土地矩阵
        printLandMatrix(land, n, m);
        // 计算并输出最小价值差
        System.out.println("规划A、B公司最小价值差为(只能横向或纵向切割)：" + minCost(land, n, m, totalValue));
    }

    /**
     * 使用前缀和优化计算最小价值差
     * 算法思路：
     * 1. 预先计算每行和每列的和
     * 2. 遍历所有可能的横向和纵向切割方案
     * 3. 计算每种切割方案下的价值差，保留最小值
     *
     * @param land 土地价值矩阵
     * @param n 行数
     * @param m 列数
     * @param totalValue 土地总价值
     * @return 最小价值差
     */
    private static int minCost(int[][] land, int n, int m, int totalValue) {
        // 计算每行的和（横向切割时的累计值）
        int[] horizontal = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                horizontal[i] += land[i][j];
            }
        }
        System.out.println("横向统计：");
        for (int i = 0; i < n; i++) {
            System.out.print(horizontal[i] + " ");
        }
        System.out.println();
        // 计算每列的和（纵向切割时的累计值）
        int[] vertical = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                vertical[j] += land[i][j];
            }
        }
        System.out.println("纵向统计：");
        for (int j = 0; j < n; j++) {
            System.out.print(vertical[j] + " ");
        }
        System.out.println();
        int result = Integer.MAX_VALUE;  // 初始化结果为最大值
        int currentSum = 0;             // 当前累计的和
        // 检查所有横向切割方案
        for (int i = 0; i < n; i++) {
            currentSum += horizontal[i];
            result = Math.min(result, Math.abs((totalValue - currentSum) - currentSum));
            // 更新result。其中，horizontalCut表示前i行的和，sum - horizontalCut表示剩下的和，作差、取绝对值，得到题目需要的“A和B各自的子区域内的土地总价值之差”。下同。
        }
        int verticalCut = 0;
        // 检查所有纵向切割方案
        for (int j = 0; j < m; j++) {
            verticalCut += vertical[j];
            result = Math.min(result, Math.abs((totalValue - verticalCut) - verticalCut));
        }
        return 0;
    }

    /**
     * 暴力解法计算最小价值差
     * 时间复杂度：O(n*m) - 需要遍历所有可能的切割方案
     *
     * @param land       土地价值矩阵
     * @param n          行数
     * @param m          列数
     * @param totalValue 土地总价值
     * @return 最小价值差
     */
    private static int minCost2(int[][] land, int n, int m, int totalValue) {
        // 初始化结果为最大整数，便于后续比较
        int result = Integer.MAX_VALUE;
        int count = 0; // 用于累计当前切割方案的部分和
        // 遍历每一行作为切割点
        for (int i = 0; i < n; i++) {
            // 遍历当前行的所有列
            for (int j = 0; j < m; j++) {
                // 累计当前行的土地价值
                count += land[i][j];
                // 当到达行末尾时（即完成一行的累计）
                if (j == m - 1) {
                    // 计算当前切割方案的差值：|total - 2*count|
                    // 其中count是前i+1行的总和（因为i从0开始）
                    result = Math.min(result, Math.abs(totalValue - 2 * count));
                }
            }
        }
        // 重置累计值
        count = 0;
        // 遍历每一列作为切割点
        for (int j = 0; j < m; j++) {
            // 遍历当前列的所有行
            for (int i = 0; i < n; i++) {
                // 累计当前列的土地价值
                count += land[i][j];
                // 当到达列末尾时（即完成一列的累计）
                if (i == n - 1) {
                    // 计算当前切割方案的差值：|total - 2*count|
                    // 其中count是前j+1列的总和（因为j从0开始）
                    result = Math.min(result, Math.abs(totalValue - 2 * count));
                }
            }
        }
        return result; // 返回所有切割方案中的最小差值
    }

    // 打印土地矩阵
    private static void printLandMatrix(int[][] land, int n, int m) {
        System.out.println("土地规模与价值为：");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(land[i][j] + " ");
            }
            System.out.println();
        }
    }
}
