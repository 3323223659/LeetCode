package CodeCaprice.Hash;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/11:10
 * @Description: 349. 两个数组的交集
 * LeetCode链接: https://leetcode.cn/problems/intersection-of-two-arrays/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给定两个数组 nums1 和 nums2 ，返回 它们的 交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class LeetCode_349 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1}, nums2 = {2, 2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }

    /**
     * 方法一：使用HashSet求交集（推荐方法）
     *
     * 方法思路：
     * 1. 使用HashSet存储第一个数组的所有元素（自动去重）
     * 2. 遍历第二个数组，检查元素是否存在于第一个集合中
     * 3. 将存在的元素存入结果集合（保证结果唯一）
     * 4. 将结果集合转为数组返回
     *
     * 时间复杂度: O(m+n) - 需要遍历两个数组各一次
     * 空间复杂度: O(m) - 最坏情况下需要存储第一个数组的所有元素
     *
     * 优点：
     * - 代码简洁易懂
     * - 自动处理重复元素
     * - 适用于任意大小的数值范围
     *
     * @param nums1 第一个整数数组
     * @param nums2 第二个整数数组
     * @return 两个数组的交集（不含重复元素）
     */
    private static int[] intersection(int[] nums1, int[] nums2) {
        // 处理空数组特殊情况
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        // 使用HashSet存储第一个数组的元素（自动去重）
        Set<Integer> set1 = new HashSet<>();
        // 存储结果的Set（保证结果不重复）
        Set<Integer> resSet = new HashSet<>();
        // 遍历数组1，将所有元素存入set1
        for (int num : nums1) {
            set1.add(num);
        }
        // 遍历数组2，检查元素是否存在于set1中
        for (int num : nums2) {
            if (set1.contains(num)) {
                resSet.add(num); // 如果是共同元素则加入结果集
            }
        }
        // stream流将结果Set转为int数组（Java 8+ 优雅写法）
        return resSet.stream().mapToInt(Integer::intValue).toArray();

        /* 传统写法：
        int[] arr = new int[resSet.size()];
        int i = 0;
        for (int num : resSet) {
            arr[i++] = num;
        }
        return arr;
        */
    }

    /**
     * 方法二：使用哈希数组（适用于数值范围已知且较小的情况）
     *
     * 方法思路：
     * 1. 创建两个计数数组（根据题目提示，数值范围0-1000）
     * 2. 遍历两个数组，标记存在的元素
     * 3. 收集在两个数组中都被标记过的元素
     * 4. 将结果转为数组返回
     *
     * 时间复杂度: O(m+n) - 需要遍历两个数组各一次
     * 空间复杂度: O(1) - 使用固定大小的计数数组（1002个元素）
     *
     * 适用场景：
     * - 当元素值范围已知且较小时效率高
     * - 比HashSet方法更节省空间
     *
     * @param nums1 第一个整数数组
     * @param nums2 第二个整数数组
     * @return 两个数组的交集
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        // 题目说明元素值范围0-1000
        int[] hash1 = new int[1002]; // 计数数组1
        int[] hash2 = new int[1002]; // 计数数组2
        // 统计数组1中各元素出现次数
        for (int num : nums1) {
            hash1[num] = 1;
        }
        // 统计数组2中各元素出现次数
        for (int num : nums2) {
            hash2[num] = 1;
        }
        // 收集在两个数组中均出现过的元素
        List<Integer> resList = new ArrayList<>();
        for (int i = 0; i < 1002; i++) {
            if (hash1[i] > 0 && hash2[i] > 0) {
                resList.add(i);
            }
        }
        // 将List转为int数组
        int[] res = new int[resList.size()];
        int index = 0;
        for (int num : resList) {
            res[index++] = num;
        }
        return res;
    }

    /**
     * 方法三：哈希数组 + HashSet组合方法
     *
     * 方法思路：
     * 1. 使用哈希数组标记第一个数组的元素
     * 2. 使用HashSet存储结果（自动去重）
     * 3. 遍历第二个数组，检查元素是否被标记过
     * 4. 将结果转为数组返回
     *
     * 时间复杂度: O(m+n) - 需要遍历两个数组各一次
     * 空间复杂度: O(m+n) - 最坏情况下需要存储所有不重复的交集元素
     *
     * 特点：
     * - 结合了哈希数组的快速查找和HashSet的自动去重
     * - 比纯HashSet方法节省部分空间
     * - 比纯哈希数组方法更灵活
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        int[] hash1 = new int[1002];
        Set<Integer> resSet = new HashSet<>();
        // 标记nums1中的元素
        for(int i : nums1) {
            hash1[i] = 1;
        }
        // 收集交集元素
        for(int i : nums2) {
            if (hash1[i] == 1) {
                resSet.add(i);
            }
        }
        // 转换为数组
        int[] res = new int[resSet.size()];
        int index = 0;
        for(int num : resSet) {
            res[index++] = num;
        }
        return res;
    }
}
