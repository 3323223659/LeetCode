package CodeCaprice.Hash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/13:46
 * @Description: 18. 四数之和
 * LeetCode链接: https://leetcode.cn/problems/4sum/description/
 * 笔记链接：https://www.programmercarl.com
 */


/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * 提示：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class LeetCode_18 {
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(fourSum(nums, target));
    }

    /**
     * 四数之和 - 排序 + 双指针法
     *
     * 方法思路：
     * 1. 首先对数组进行排序，这是使用双指针的前提
     * 2. 使用两层循环固定前两个数，然后在剩余部分使用双指针查找后两个数
     * 3. 使用左右指针收缩查找，同时处理重复元素
     * 4. 注意处理大数相加的溢出问题（使用long类型）
     *
     * 时间复杂度: O(n³) - 两层循环O(n²)，双指针O(n)
     * 空间复杂度: O(1) - 除了结果集外，只使用了常数空间
     *
     * 关键点：
     * - 排序后可以方便地跳过重复元素
     * - 双指针技巧将O(n⁴)暴力解优化为O(n³)
     * - 去重处理需要小心，要在找到有效四元组后再去重
     * - 剪枝优化可以提前终止不必要的循环
     * - 注意处理大数相加可能导致的溢出问题
     *
     * @param nums 输入数组
     * @param target 目标和
     * @return 所有不重复的四元组列表
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);  // 关键步骤：先排序
        List<List<Integer>> result = new ArrayList<>();  // 存储结果
        // 第一层循环，固定第一个数nums[k]
        for (int k = 0; k < nums.length; k++) {
            // 剪枝处理1：如果nums[k]已经大于target且为正数，后面不可能找到有效组合
            if (nums[k] > target && nums[k] >= 0) {
                break; // 可以直接return result，但用break更安全（避免遗漏某些情况）
            }
            // 去重1：跳过相同的nums[k]值（第一个数去重）
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            // 第二层循环，固定第二个数nums[i]
            for (int i = k + 1; i < nums.length; i++) {
                // 剪枝处理2：如果前两数之和已经大于target且为正数，后面不可能找到有效组合
                if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) {
                    break; // 注意这里是break到上一级循环，不是return
                }
                // 去重2：跳过相同的nums[i]值（第二个数去重）
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 双指针查找剩余两个数
                int left = i + 1;      // 左指针从i+1开始
                int right = nums.length - 1; // 右指针从末尾开始
                while (right > left) {
                    // 使用long防止大数相加溢出
                    long sum = (long) nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        right--; // 和太大，右指针左移
                    } else if (sum < target) {
                        left++;  // 和太小，左指针右移
                    } else {
                        // 找到有效四元组
                        result.add(Arrays.asList(nums[k], nums[i], nums[left], nums[right]));
                        // 去重3：跳过相同的nums[left]和nums[right]值
                        while (right > left && nums[right] == nums[right - 1]) right--;
                        while (right > left && nums[left] == nums[left + 1]) left++;
                        // 移动指针寻找下一个可能的四元组
                        right--;
                        left++;
                    }
                }
            }
        }
        return result;
    }
}
