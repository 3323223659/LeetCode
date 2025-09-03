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
         * 方法一：暴力解法
         * 通过双层循环实现元素移除，发现目标值时将后续元素前移
         *
         * 算法步骤：
         * 1. 遍历数组，查找等于val的元素
         * 2. 找到后，将后续所有元素前移一位
         * 3. 调整索引和长度计数器
         *
         * 时间复杂度: O(n²) - 最坏情况下需要n次移动，每次移动n个元素
         * 空间复杂度: O(1) - 原地修改，不需要额外空间
         *
         * 优点：逻辑简单，易于理解
         * 缺点：效率较低，移动操作频繁
         *
         * @param nums 输入数组
         * @param val 要移除的目标值
         * @return 新数组的有效长度
         */
        public static int removeElement(int[] nums, int val) {
            // 记录当前有效数组的长度
            int n = nums.length;
            // 遍历数组中的每个元素
            for (int i = 0; i < n; i++) {
                // 如果当前元素等于目标值
                if (nums[i] == val) {
                    // 将后续所有元素前移一位（覆盖当前元素）
                    for (int j = i + 1; j < n; j++) {
                        nums[j - 1] = nums[j];
                    }
                    // 因为元素前移，需要再次检查当前位置的新元素
                    i--;
                    // 有效长度减1（移除了一个元素）
                    n--;
                }
            }
            return n;
        }

        /**
         * 方法二：双指针解法（快慢指针法）- 推荐
         * 使用两个指针，快指针遍历数组，慢指针记录有效元素位置
         *
         * 算法原理：
         * 快指针：遍历整个数组，寻找有效元素（不等于val的元素）
         * 慢指针：指向下一个有效元素应该存放的位置
         *
         * 时间复杂度: O(n) - 只需一次遍历
         * 空间复杂度: O(1) - 原地修改，不需要额外空间
         *
         * 优点：效率高，只需一次遍历
         * 缺点：改变了元素的相对顺序（但题目允许）
         *
         * @param nums 输入数组
         * @param val 要移除的目标值
         * @return 新数组的有效长度
         */
        public static int removeElement2(int[] nums, int val) {
            // 慢指针：记录下一个有效元素应该存放的位置
            // 也代表了当前有效数组的长度
            int slow = 0;
            // 快指针：遍历整个数组，寻找有效元素
            for (int fast = 0; fast < nums.length; fast++) {
                // 如果当前元素不是要移除的值
                if (nums[fast] != val) {
                    // 将有效元素复制到慢指针位置
                    nums[slow] = nums[fast];
                    // 慢指针前进，扩大有效区域
                    slow++;
                }
                // 如果是要移除的值：快指针继续前进，慢指针保持不动
                // 这样相当于跳过了无效元素
            }
            // 慢指针的最终位置就是有效数组的长度
            return slow;
        }
    }
}
