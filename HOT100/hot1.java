package HOT100;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/23/12:24
 * @Description: 两数之和
 * 两数之和给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 */

public class hot1 {

    public static void main(String[] args) {
        Solution.twoSum2(new int[]{2, 7, 11, 15}, 9);
    }

    static class Solution {

        /**
         * 自实现暴力求解法 - 通过双重循环检查所有可能的组合
         * 时间复杂度: O(n²) - 对于每个元素都要遍历其余元素
         * 空间复杂度: O(1) - 只使用了固定大小的额外空间
         *
         * @param nums   输入数组
         * @param target 目标和
         * @return 两个数的下标数组，如果不存在则返回空数组
         */
        public static int[] twoSum1(int[] nums, int target) {
            int n = nums.length;
            // 外层循环遍历每个元素
            for (int i = 0; i < n; ++i) {
                // 内层循环遍历当前元素之后的所有元素
                for (int j = i + 1; j < n; ++j) {
                    // 检查两数之和是否等于目标值
                    if (nums[i] + nums[j] == target) {
                        // 找到解，立即返回下标对
                        return new int[]{i, j};
                    }
                }
            }
            // 遍历结束未找到解，返回空数组
            return new int[0];
        }

        /**
         * 官方哈希表法 - 使用哈希表存储已访问元素实现快速查找
         * 时间复杂度: O(n) - 只需遍历数组一次
         * 空间复杂度: O(n) - 需要存储元素到索引的映射
         *
         * @param nums   输入数组
         * @param target 目标和
         * @return 两个数的下标数组，如果不存在则返回空数组
         */
        public static int[] twoSum2(int[] nums, int target) {
            // 创建哈希表存储元素值到索引的映射
            Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();

            // 遍历数组中的每个元素
            for (int i = 0; i < nums.length; ++i) {
                // 计算当前元素需要的补数
                int complement = target - nums[i];

                // 检查补数是否已存在于哈希表中
                if (hashtable.containsKey(complement)) {
                    // 找到解，返回补数的索引和当前索引
                    return new int[]{hashtable.get(complement), i};
                }

                // 将当前元素及其索引存入哈希表
                hashtable.put(nums[i], i);
            }

            // 遍历结束未找到解，返回空数组
            return new int[0];
        }
    }
}
