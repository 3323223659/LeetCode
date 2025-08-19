package CodeCaprice.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/18/13:54
 * @Description: 209. 长度最小的子数组
 * LeetCode链接: https://leetcode.cn/problems/minimum-size-subarray-sum/description/
 * 笔记链接: https://www.programmercarl.com
 */

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */
public class LeetCode_209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int res = Solution.minSubArrayLen2(target, nums);
        System.out.println(res);
    }

    static class Solution {

        /**
         * 暴力解法 - 寻找和≥target的最短连续子数组
         * 时间复杂度：O(n²) - 双重循环遍历所有可能的子数组
         * 空间复杂度：O(1) - 只使用常数空间
         *
         * @param target 目标值
         * @param nums 输入数组
         * @return 最短子数组长度（未找到返回0）
         */
        public static int minSubArrayLen(int target, int[] nums) {
            int res = Integer.MAX_VALUE; // 初始化结果为最大整数
            // 外层循环：子数组起始位置
            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i]; // 当前子数组和
                // 单个元素就满足条件
                if (sum >= target) {
                    return 1; // 最短长度为1
                }
                // 内层循环：扩展子数组结束位置
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j]; // 累加元素值
                    // 满足条件时更新结果
                    if (sum >= target) {
                        res = Math.min(res, j - i + 1); // 计算当前子数组长度
                        break; // 跳出内层循环（已找到以i开头的最短子数组）
                    }
                }
            }
            // 处理未找到的情况
            return res == Integer.MAX_VALUE ? 0 : res;
        }

        /**
         * 滑动窗口解法 - 优化版寻找最短子数组
         * 时间复杂度：O(n) - 每个元素最多被访问两次
         * 空间复杂度：O(1) - 只使用常数空间
         *
         * @param target 目标值
         * @param nums 输入数组
         * @return 最短子数组长度（未找到返回0）
         */
        public static int minSubArrayLen2(int target, int[] nums) {
            int res = Integer.MAX_VALUE; // 初始化结果为最大整数
            int l = 0; // 窗口左边界
            int sum = 0; // 窗口内元素和
            // 外层循环：窗口右边界扩展
            for (int k = 0; k < nums.length; k++) {
                sum += nums[k]; // 扩展右边界
                // 当窗口和满足条件时，尝试收缩左边界
                while (sum >= target) {
                    res = Math.min(res, k - l + 1); // 更新最小长度
                    sum -= nums[l]; // 移除左边界元素
                    l++; // 移动左边界
                }
            }
            // 处理未找到的情况
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
}
