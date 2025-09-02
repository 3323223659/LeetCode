package CodeCaprice.Hash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/13:45
 * @Description: 15. 三数之和
 * LeetCode链接: https://leetcode.cn/problems/3sum/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class LeetCode_15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    /**
     * 方法一：排序 + 双指针法（最优解）
     *
     * 方法思路：
     * 1. 首先对数组进行排序，这是使用双指针的前提
     * 2. 固定一个数nums[i]，然后在剩余部分使用双指针查找两数之和等于-nums[i]
     * 3. 使用左右指针收缩查找，同时处理重复元素
     *
     * 时间复杂度: O(n²) - 外层循环O(n)，内层双指针O(n)
     * 空间复杂度: O(1) - 除了结果集外，只使用了常数空间
     *
     * 关键点：
     * - 排序后可以方便地跳过重复元素
     * - 双指针技巧将O(n³)暴力解优化为O(n²)
     * - 去重处理需要小心，要在找到有效三元组后再去重
     *
     * @param nums 输入数组
     * @return 所有不重复的三元组列表
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 关键步骤：先排序
        for (int i = 0; i < nums.length; i++) {
            // 剪枝：如果第一个数已经大于0，后面不可能组成和为0的三元组
            if (nums[i] > 0) {
                return result;
            }
            // 去重：跳过相同的a值（nums[i]）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;      // 左指针从i+1开始
            int right = nums.length - 1; // 右指针从末尾开始
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--; // 和太大，右指针左移
                } else if (sum < 0) {
                    left++;  // 和太小，左指针右移
                } else {
                    // 找到有效三元组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重：跳过相同的b和c值
                    while (right > left && nums[right] == nums[right - 1]){
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]){
                        left++;
                    }
                    // 移动指针寻找下一个可能的三元组
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 方法二：排序 + 哈希表法
     *
     * 方法思路：
     * 1. 先对数组排序以便去重
     * 2. 固定一个数nums[i]，然后在剩余部分使用哈希表查找两数之和等于-nums[i]
     * 3. 使用哈希表记录已经遍历过的数，避免重复计算
     *
     * 时间复杂度: O(n²) - 外层循环O(n)，内层循环O(n)
     * 空间复杂度: O(n) - 哈希表需要额外空间
     *
     * 特点：
     * - 相比双指针法更直观但效率稍低
     * - 去重逻辑比双指针法复杂
     * - 适用于不便于使用双指针的场景
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 关键步骤：先排序
        for (int i = 0; i < nums.length; i++) {
            // 剪枝：如果第一个数已经大于0，后面不可能组成和为0的三元组
            if (nums[i] > 0) {
                return result;
            }
            // 去重：跳过相同的a值（nums[i]）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                // 去重：跳过连续的三个相同b值（防止重复）
                if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
                    continue;
                }
                int c = -nums[i] - nums[j]; // 计算需要的c值
                if (set.contains(c)) {
                    result.add(Arrays.asList(nums[i], nums[j], c));
                    set.remove(c); // 去重：避免重复使用相同的c值
                } else {
                    set.add(nums[j]); // 将当前数加入哈希表
                }
            }
        }
        return result;
    }
}
