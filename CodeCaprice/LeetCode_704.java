package CodeCaprice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/17/22:47
 * @Description: 704. 二分查找
 * LeetCode 链接: https://leetcode.cn/problems/binary-search/description/
 * 链接：https://www.programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE
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

        // 左闭右闭写法
        public static int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right){
                int middle = left + ((right - left) >> 2);
                if (nums[middle] < target){
                    left = middle + 1;
                } else if (nums[middle] > target) {
                    right = middle - 1;
                } else if (nums[middle] == target) {
                    return middle;
                }
            }
            return -1;
        }

        // 左闭右开写法
        public static int search2(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right){
                int middle = left + ((right - left) >> 2);
                if (nums[middle] < target){
                    left = middle + 1;
                } else if (nums[middle] > target) {
                    right = middle;
                } else if (nums[middle] == target) {
                    return middle;
                }
            }
            return -1;
        }
    }
}
