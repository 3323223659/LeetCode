package HOT100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        int  []nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = Solution.threeSum(nums);
        System.out.println(result);
    }

    static class Solution {

        public static List<List<Integer>> threeSum(int[] nums) {

        }

    }

}
