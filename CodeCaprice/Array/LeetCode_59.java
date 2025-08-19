package CodeCaprice.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/18/15:49
 * @Description: 59. 螺旋矩阵 II
 * LeetCode链接: https://leetcode.cn/problems/spiral-matrix-ii/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 *  |  1  →  2  →  3  |
 *  |  ↑           ↓  |
 *  |  8  →  9     4  |
 *  |  ↑           ↓  |
 *  |  7  ←  6  ←  5  |
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 * 提示：
 * 1 <= n <= 20
 */
public class LeetCode_59 {
    public static void main(String[] args) {
        int n = 3;
        int[][] matrix = Solution.generateMatrix2(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Solution {
        /**
         * 生成螺旋矩阵（n x n） 标准答案
         * 算法核心：按层循环填充，每层分为上、右、下、左四条边，采用"左闭右开"区间原则
         * 时间复杂度：O(n²) - 需要填充n²个元素
         * 空间复杂度：O(n²) - 返回n x n的结果矩阵
         *
         * @param n 矩阵的阶数（行数和列数）
         * @return 螺旋排列的n x n矩阵
         */
        public static int[][] generateMatrix(int n) {
            int[][] nums = new int[n][n];  // 结果矩阵
            int startX = 0, startY = 0;    // 每一圈的起始坐标 (x行, y列)
            int offset = 1;                // 边界偏移量，控制每条边的长度（每圈缩减1）
            int count = 1;                 // 填充的数字（从1开始）
            int loop = 1;                  // 当前循环圈数（从1开始计数）
            int i, j;                      // 临时变量：i表示行，j表示列

            // 循环条件：圈数 <= n/2（例如n=3时只需循环1圈，中心单独处理）
            while (loop <= n / 2) {
                // 上层：从左到右（行不变，列递增）
                // 左闭右开：j从startY开始，到n-offset结束（不包含n-offset）
                for (j = startY; j < n - offset; j++) {
                    nums[startX][j] = count++;  // 填充当前行、当前列
                }
                // 右层：从上到下（列不变，行递增）
                // 左闭右开：i从startX开始，到n-offset结束（不包含n-offset）
                for (i = startX; i < n - offset; i++) {
                    nums[i][j] = count++;  // j此时已到达上层终点列
                }
                // 下层：从右到左（行不变，列递减）
                // 左闭右开：j从当前列开始，到startY结束（不包含startY）
                for (; j > startY; j--) {
                    nums[i][j] = count++;  // i此时已到达右层终点行
                }
                // 左层：从下到上（列不变，行递减）
                // 左闭右开：i从当前行开始，到startX结束（不包含startX）
                for (; i > startX; i--) {
                    nums[i][j] = count++;  // j此时已到达下层终点列（即startY）
                }
                // 准备下一圈：起始点内移，偏移量+1，圈数+1
                startX++;  // 起始行+1
                startY++;  // 起始列+1
                offset++;  // 边界偏移+1（每圈边界向内缩1）
                loop++;    // 圈数+1
            }
            // 若n为奇数，中心位置（startX, startY）未被填充，需单独处理
            if (n % 2 == 1) {
                nums[startX][startY] = count;  // count此时为n²
            }
            return nums;
        }

        /**
         * 生成螺旋矩阵（n x n） 自编写
         * 算法核心：按层循环填充，每层分为上、右、下、左四条边，采用"左闭右开"区间原则
         * 时间复杂度：O(n²) - 需要填充n²个元素
         * 空间复杂度：O(n²) - 返回n x n的结果矩阵
         *
         * @param n 矩阵的阶数（行数和列数）
         * @return 螺旋排列的n x n矩阵
         */
        public static int[][] generateMatrix2(int n) {
            int[][] nums = new int[n][n];
            int startX = 0, startY = 0;    // 当前圈的起始坐标（行，列）
            int offset = 1;                // 边界偏移量（控制每圈边界收缩）
            int count = 1;                 // 当前填充的数字（从1开始递增）
            int loop = 1;                  // 当前循环的圈数
            int i, j;                      // 临时遍历变量（i行，j列）

            // 主循环：处理每一圈，直到完成n/2圈
            while (loop <= n / 2) {
                // 上层：从左到右填充
                // 遍历列：从startY到n-offset-1（左闭右开）
                for (j = startY; j < n - offset; j++) {
                    nums[startX][j] = count++;  // 固定行startX，列j递增
                }
                // 右层：从上到下填充
                // 遍历行：从startX到n-offset-1（左闭右开）
                for (i = startX; i < n - offset; i++) {
                    nums[i][n - offset] = count++;  // 固定列n-offset，行i递增
                }
                // 下层：从右到左填充
                // 遍历列：从n-offset-1到startY+1（反向，左闭右开）
                for (j = n - offset; j > startY; j--) {
                    nums[n - offset][j] = count++;  // 固定行n-offset，列j递减
                }
                // 左层：从下到上填充
                // 遍历行：从n-offset-1到startX+1（反向，左闭右开）
                for (i = n - offset; i > startX; i--) {
                    nums[i][startY] = count++;  // 固定列startY，行i递减
                }
                // 更新下一圈的参数
                startX++;  // 起始行内移
                startY++;  // 起始列内移
                offset++;  // 边界收缩
                loop++;    // 圈数增加
            }
            // 处理n为奇数时的中心点
            if (n % 2 == 1) {
                nums[startX][startY] = count;  // 填充中心位置（此时startX=startY=n/2）
            }
            return nums;
        }
    }
}
