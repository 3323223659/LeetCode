package CodeCaprice.Array;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/17/23:20
 * @Description: 27. 移除元素
 * LeetCode链接: https://leetcode.cn/problems/remove-element/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2,_,_]
 * 解释：你的函数函数应该返回 k = 2, 并且 nums 中的前两个元素均为 2,你在返回的 k 个元素之外留下了什么并不重要。
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3,_,_,_]
 * 解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4,你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
 * 注意这五个元素可以任意顺序返回。
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 */

public class LeetCode_27 {
    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        int result = Solution.removeElement2(nums, val);
        System.out.println(result);
    }

    static class Solution {

        /**
         * 暴力解法 - 原地移除指定元素
         * 时间复杂度：O(n²) - 最坏情况下需要双层循环
         * 空间复杂度：O(1) - 原地修改数组
         *
         * @param nums 输入数组
         * @param val 要移除的值
         * @return 新数组的长度
         */
        public static int removeElement(int[] nums, int val) {
            // 记录有效数组的长度（初始为数组全长）
            int n = nums.length;
            // 外层循环遍历数组元素
            for (int i = 0; i < n; i++) {
                // 如果当前元素等于目标值
                if (nums[i] == val) {
                    // 内层循环：将后续元素前移一位（覆盖当前元素）
                    for (int j = i + 1; j < n; j++) {
                        nums[j - 1] = nums[j];
                    }
                    i--; // 因元素前移，需再次检查当前位置的新元素
                    n--; // 每移除一个元素，有效长度减1
                }
            }
            return n;
        }

        /**
         * 双指针解法（快慢指针）- 优化版移除元素
         * 时间复杂度：O(n) - 只需单次遍历
         * 空间复杂度：O(1) - 原地修改数组
         *
         * @param nums 输入数组
         * @param val 要移除的值
         * @return 新数组的长度
         */
        public static int removeElement2(int[] nums, int val) {
            // 慢指针：记录下一个有效元素的位置
            int slow = 0;
            // 快指针：遍历整个数组
            for (int fast = 0; fast < nums.length; fast++) {
                // 当前元素不是要移除的值
                if (nums[fast] != val) {
                    // 将有效元素复制到慢指针位置
                    nums[slow] = nums[fast];
                    // 慢指针前进（扩展有效区域）
                    slow++;
                }
                // 如果是要移除的值，快指针继续前进，慢指针保持不动
            }
            // 慢指针的最终位置就是新数组的长度
            return slow;
        }
    }
}
