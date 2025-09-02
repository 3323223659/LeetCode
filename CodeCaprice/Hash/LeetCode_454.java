package CodeCaprice.Hash;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/13:11
 * @Description: 454. 四数相加 II
 * LeetCode链接: https://leetcode.cn/problems/4sum-ii/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 示例 1：
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * 示例 2：
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 * 提示：
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class LeetCode_454 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
    }

    /**
     * 哈希表法 - 计算四数相加为零的元组数量
     * 方法思路：
     * 1. 使用哈希表记录前两个数组所有两数之和的出现次数
     * 2. 遍历后两个数组，计算两数之和的相反数
     * 3. 查找该相反数在哈希表中的出现次数并累加
     *
     * 时间复杂度: O(n²) - 两个嵌套的n级别循环
     * 空间复杂度: O(n²) - 最坏情况下需要存储n²个不同的和
     *
     * 关键点：
     * - 将四数之和问题转化为两个两数之和问题
     * - 使用哈希表存储中间结果避免重复计算
     * - 通过补数思想（相反数）匹配满足条件的组合
     *
     * 边界条件：
     * - 处理空数组输入情况
     * - 处理大数相加的溢出问题（题目保证数值在合理范围内）
     *
     * @param nums1 第一个整数数组
     * @param nums2 第二个整数数组
     * @param nums3 第三个整数数组
     * @param nums4 第四个整数数组
     * @return 满足条件的四元组数量
     */
    private static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 处理空数组边界情况
        if (nums1 == null || nums1.length == 0 ||
                nums2 == null || nums2.length == 0 ||
                nums3 == null || nums3.length == 0 ||
                nums4 == null || nums4.length == 0) {
            return 0;
        }
        // key: nums1[i]+nums2[j]的值，value: 该值出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 遍历nums1和nums2，计算所有两数之和
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int temp = nums1[i] + nums2[j];
                // 如果map中没有这个和，则放入1；否则次数+1
                if (!map.containsKey(temp)) {
                    map.put(temp, 1);
                } else {
                    map.put(temp, map.get(temp) + 1);
                }
            }
        }
        int res = 0;
        // 遍历nums3和nums4，计算需要的补数
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int temp = -(nums3[i] + nums4[j]); // 需要的补数
                // 如果补数存在于map中，则增加对应的次数
                if (map.containsKey(temp)) {
                    res += map.get(temp);
                }
            }
        }
        return res;
    }

    /**
     * 方法二：优化后的哈希表法（代码更简洁）
     * 时间复杂度：O(n²) 空间复杂度：O(n²)
     */
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        // 统计两个数组中的元素之和，同时统计出现的次数，放入map
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                sumMap.merge(num1 + num2, 1, Integer::sum);
            }
        }
        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
        int result = 0;
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                // 直接累加相反数的出现次数
                result += sumMap.getOrDefault(-(num3 + num4), 0);
            }
        }
        return result;
    }
}
