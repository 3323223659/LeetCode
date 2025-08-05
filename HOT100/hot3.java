package HOT100;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/23/12:24
 * @Description: 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */

public class hot3 {

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        int result = Solution.longestConsecutive1(nums);
        System.out.println(result);
    }

    static class Solution {

        /**
         * (自实现)排序法实现 - 通过排序数组后遍历查找最长连续序列
         *
         * 方法思路：
         * 1. 先对数组进行排序
         * 2. 遍历排序后的数组，统计连续序列长度
         * 3. 跳过重复数字
         * 4. 记录遇到的最长序列长度
         *
         * 时间复杂度: O(nlogn) - 主要来自排序操作
         * 空间复杂度: O(1) - 只使用了固定大小的额外空间
         *
         * 优点：实现直观，空间效率高
         * 缺点：排序操作增加了时间复杂度
         *
         * @param nums 输入数组
         * @return 最长连续序列的长度
         */
        public static int longestConsecutive1(int[] nums) {
            // 处理空数组情况
            if (nums.length == 0) {
                return 0;
            }

            // 先对数组进行排序
            Arrays.sort(nums);

            int results = 1;  // 记录最终结果
            int temp = 1;     // 记录当前连续序列长度

            for (int i = 0; i < nums.length - 1; i++) {
                // 检查连续数字
                while (i + 1 < nums.length) {
                    if (nums[i] + 1 == nums[i + 1]) {  // 找到连续数字
                        temp++;
                        i++;
                        continue;
                    }
                    if (nums[i] == nums[i + 1]) {  // 跳过重复数字
                        i++;
                        continue;
                    }
                    break;  // 序列中断
                }

                // 更新最长序列长度
                if (temp > results) {
                    results = temp;
                }
                temp = 1;  // 重置当前序列长度
            }

            return results;
        }

        /**
         * 官方哈希集合法 - 利用HashSet实现高效查找
         *
         * 方法思路：
         * 1. 将所有数字存入HashSet实现O(1)时间查找
         * 2. 遍历每个数字，只从序列的起始点开始查找
         * 3. 对于每个起始点，向后查找连续数字
         * 4. 记录遇到的最长序列长度
         *
         * 时间复杂度: O(n) - 每个数字最多被访问两次
         * 空间复杂度: O(n) - 需要存储HashSet
         *
         * 优点：时间效率高
         * 缺点：需要额外空间存储HashSet
         *
         * @param nums 输入数组
         * @return 最长连续序列的长度
         */
        public int longestConsecutive2(int[] nums) {
            // 创建HashSet存储所有数字
            Set<Integer> num_set = new HashSet<>();
            for (int num : nums) {
                num_set.add(num);
            }

            int longestStreak = 0;  // 记录最长序列长度

            // 遍历HashSet中的每个数字
            for (int num : num_set) {
                // 只从序列的起始点开始查找（即前一个数字不存在，如果有前一个数字代表不是第一个直接跳过）
                if (!num_set.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;  // 当前序列长度

                    // 向后查找连续数字
                    while (num_set.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    // 更新最长序列长度
                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }

    }
}
