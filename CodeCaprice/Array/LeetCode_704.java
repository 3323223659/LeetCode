package CodeCaprice.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/17/22:47
 * @Description: 704. 二分查找
 * LeetCode 链接: https://leetcode.cn/problems/binary-search/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
 * 你必须编写一个具有 O(log n) 时间复杂度的算法。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */

public class LeetCode_704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int search = Solution.search(nums, target);
        System.out.println(search == -1 ? "该target不存在" : "查找的target下标是：" + search);
    }

    static class Solution {

        /**
         * 二分查找 - 左闭右闭区间写法
         * 在有序数组中查找目标值，返回其索引，未找到返回-1
         *
         * @param nums 有序数组
         * @param target 要查找的目标值
         * @return 目标值索引，未找到返回-1
         */
        public static int search(int[] nums, int target) {
            // 初始化左右边界（闭区间）
            int left = 0, right = nums.length - 1;
            // 循环条件：左边界 <= 右边界（区间有效）
            while (left <= right){
                // 计算中间位置，防止溢出
                int middle = left + ((right - left) >> 2);
                // 目标值在右半部分
                if (nums[middle] < target){
                    left = middle + 1;  // 调整左边界
                }
                // 目标值在左半部分
                else if (nums[middle] > target) {
                    right = middle - 1;  // 调整右边界
                }
                // 找到目标值
                else if (nums[middle] == target) {
                    return middle;  // 返回索引
                }
            }
            return -1; // 未找到目标值
        }

        /**
         * 二分查找 - 左闭右开区间写法
         * 在有序数组中查找目标值，返回其索引，未找到返回-1
         *
         * @param nums 有序数组
         * @param target 要查找的目标值
         * @return 目标值索引，未找到返回-1
         */
        public static int search2(int[] nums, int target) {
            // 初始化左右边界（右开区间）
            int left = 0, right = nums.length;
            // 循环条件：左边界 < 右边界（区间有效）
            while (left < right){
                // 计算中间位置，防止溢出
                int middle = left + ((right - left) >> 2);
                // 目标值在右半部分
                if (nums[middle] < target){
                    left = middle + 1;  // 调整左边界
                }
                // 目标值在左半部分
                else if (nums[middle] > target) {
                    right = middle;  // 调整右边界（右开区间）
                }
                // 找到目标值
                else if (nums[middle] == target) {
                    return middle;  // 返回索引
                }
            }
            return -1; // 未找到目标值
        }
    }
}
