package HOT100;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/08/01/17:16
 * @Description:三数之和
 * 给你一个整数数组 nums
 * 判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 */

public class hot6 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = Solution.threeSum2(nums);
        System.out.println(result);
    }

    static class Solution {

        /**
         * 官方题解 - 双指针法
         *
         * 方法思路：
         * 1. 首先对数组进行排序，这是双指针法的基础
         * 2. 外层循环固定第一个数nums[first]
         * 3. 内层使用双指针寻找另外两个数：
         *    - second指针从first+1开始
         *    - third指针从数组末尾开始
         * 4. 根据三数之和调整指针位置：
         *    - 和小于0：second右移
         *    - 和大于0：third左移
         *    - 和等于0：记录结果
         * 5. 关键去重处理：
         *    - 外层循环跳过重复的nums[first]
         *    - 内层循环跳过重复的nums[second]和nums[third]
         *
         * 时间复杂度: O(n²) - 排序O(nlogn) + 双指针O(n²)
         * 空间复杂度: O(1) - 不考虑结果存储空间
         *
         * 关键点：
         * - 排序是双指针法的基础
         * - 去重处理是本题难点
         * - 指针移动策略保证不遗漏任何可能解
         *
         * @param nums 输入的整数数组
         * @return 所有满足条件且不重复的三元组集合
         */
        public static List<List<Integer>> threeSum1(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            // 枚举 a
            for (int first = 0; first < n; ++first) {
                // 需要和上一次枚举的数不相同
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                // c 对应的指针初始指向数组的最右端
                int third = n - 1;
                int target = -nums[first];
                // 枚举 b
                for (int second = first + 1; second < n; ++second) {
                    // 需要和上一次枚举的数不相同
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    // 需要保证 b 的指针在 c 的指针的左侧
                    while (second < third && nums[second] + nums[third] > target) {
                        --third;
                    }
                    // 如果指针重合，随着 b 后续的增加
                    // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                    if (second == third) {
                        break;
                    }
                    if (nums[second] + nums[third] == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        ans.add(list);
                    }
                }
            }
            return ans;
        }

        /**
         * 网友简化版双指针法
         *
         * 方法思路：
         * 1. 同样先对数组进行排序
         * 2. 外层循环固定第一个数nums[i]
         * 3. 内层使用双指针技术：
         *    - left指针从i+1开始
         *    - right指针从数组末尾开始
         * 4. 优化点：
         *    - 更简洁的指针移动逻辑
         *    - 找到解后立即跳过所有重复元素
         *    - 使用Arrays.asList简化列表创建
         * 时间复杂度: O(n²)
         * 空间复杂度: O(1)
         * 关键点：
         * - 去重处理更加直观
         * - 代码结构更简洁
         * - 数学证明保证正确性
         *
         * @param nums 输入的整数数组
         * @return 所有满足条件且不重复的三元组集合
         */
        public static List<List<Integer>> threeSum2(int[] nums) {
            Arrays.sort(nums); // 先排序
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                // 跳过重复元素
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                // 双指针，目标是找到 nums[l] + nums[r] = -nums[i]
                int l = i + 1, r = nums.length - 1;
                int target = -nums[i];

                while (l < r) {
                    int sum = nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        l++;
                        r--;
                        // 跳过重复元素
                        while (l < r && nums[l] == nums[l - 1]) l++;
                        while (l < r && nums[r] == nums[r + 1]) r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
            return res;
        }
    }

}
