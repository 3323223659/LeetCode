package CodeCaprice.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/18/12:54
 * @Description: 977. 有序数组的平方
 * LeetCode链接: https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 * 笔记链接: https://www.programmercarl.com
 */

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 示例 1：
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]
 * 排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 提示：
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 已按 非递减顺序 排序
 */

public class LeetCode_977 {
    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] res = Solution.sortedSquares2(nums);
        System.out.println(Arrays.toString(res));
    }

    static class Solution {

        /**
         * 暴力解法：平方后排序
         * 1. 先计算每个元素的平方
         * 2. 然后对结果数组进行排序
         * 时间复杂度：O(n log n) - 主要来自排序操作
         * 空间复杂度：O(n) - 需要额外空间存储结果
         *
         * @param nums 输入数组（已排序）
         * @return 平方后的有序数组
         */
        public static int[] sortedSquares(int[] nums) {
            // 创建结果数组
            int[] res = new int[nums.length];
            // 计算每个元素的平方
            for (int i = 0; i < nums.length; i++) {
                res[i] = nums[i] * nums[i];
            }
            // 对结果数组排序
            Arrays.sort(res);
            return res;
        }

        /**
         * 双指针解法（优化版）
         * 利用输入数组已排序的特性：
         * 1. 数组两端的平方值较大，中间的平方值较小
         * 2. 使用双指针从两端向中间遍历
         * 3. 每次取较大的平方值放入结果数组末尾
         * 时间复杂度：O(n) - 只需一次遍历
         * 空间复杂度：O(n) - 需要额外空间存储结果
         *
         * @param nums 输入数组（已排序）
         * @return 平方后的有序数组
         */
        public static int[] sortedSquares2(int[] nums) {
            // 创建结果数组
            int[] res = new int[nums.length];
            // k指针指向结果数组当前填充位置（从末尾开始）
            int k = nums.length - 1;
            // i指针从左开始，j指针从右开始
            for (int i = 0, j = nums.length - 1; i <= j;) {
                // 比较左右指针指向元素的平方值
                if (nums[i] * nums[i] < nums[j] * nums[j]) {
                    // 右指针平方值较大，放入结果数组末尾
                    res[k--] = nums[j] * nums[j];
                    j--;  // 右指针左移
                } else {
                    // 左指针平方值较大或相等，放入结果数组末尾
                    res[k--] = nums[i] * nums[i];
                    i++;  // 左指针右移
                }
            }
            return res;
        }
    }
}
