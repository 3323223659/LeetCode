package CodeCaprice.Hash;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/09/01/13:10
 * @Description: 1. 两数之和
 * LeetCode链接: https://leetcode.cn/problems/two-sum/description/
 * 笔记链接：https://www.programmercarl.com
 */

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class LeetCode_1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    /**
     * 哈希表法（版本1） - 查找两数之和等于目标值的索引
     *
     * 方法思路：
     * 1. 使用哈希表存储<数值, 索引>键值对
     * 2. 遍历数组时计算目标补数（target - nums[i]）
     * 3. 检查补数是否存在于哈希表中
     * 4. 存在则返回结果，不存在则记录当前数值和索引
     *
     * 时间复杂度: O(n) - 只需一次遍历
     * 空间复杂度: O(n) - 最坏情况下需要存储所有元素
     *
     * 关键点：
     * - 利用哈希表实现O(1)时间复杂度的查找
     * - 补数思想：a + b = target => b = target - a
     * - 先检查后存入，避免使用相同元素
     *
     * 边界条件：
     * - 处理空数组或null输入
     * - 处理大数计算（题目保证在int范围内）
     * - 保证唯一解（题目说明）
     *
     * @param nums 输入数组
     * @param target 目标和
     * @return 两个数的索引数组
     */
    private static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i]; // 计算需要的补数
            if(map.containsKey(complement)){   // 检查补数是否存在
                res[1] = i;                   // 当前索引作为第二个数
                res[0] = map.get(complement); // 补数的索引作为第一个数
                break;
            }
            map.put(nums[i], i); // 将当前数和索引存入哈希表
        }
        return res;
    }

    /**
     * 哈希表法（版本2） - 优化版实现
     *
     * 方法改进：
     * 1. 直接返回结果而非break循环
     * 2. 更简洁的变量命名
     * 3. 去除冗余的res数组
     *
     * 保持与版本1相同的时空复杂度
     * 核心算法思想不变，优化代码结构
     */
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(indexMap.containsKey(complement)){
                return new int[]{i, indexMap.get(complement)}; // 直接返回结果
            }
            indexMap.put(nums[i], i);
        }
        return null;
    }

    /**
     * 双指针法 - 适用于已排序数组
     *
     * 方法思路：
     * 1. 备份原始数组（保留索引信息）
     * 2. 排序数组使双指针生效
     * 3. 使用左右指针收缩查找目标对
     * 4. 在原始数组中找回对应索引
     *
     * 时间复杂度: O(nlogn) - 主要由排序决定
     * 空间复杂度: O(n) - 需要数组备份
     *
     * 关键点：
     * - 必须先排序才能使用双指针
     * - 需要额外空间备份原始数组
     * - 处理重复元素的情况（如[3,3]）
     *
     * 适用场景：
     * - 当数组已排序时效率更高
     * - 需要扩展到三数之和等问题的基础
     */
    private static int[] twoSum3(int[] nums, int target) {
        // 备份原始数组（为了保留原始索引）
        int[] tmp = Arrays.copyOf(nums, nums.length);
        // 排序数组（双指针需要有序数组）
        Arrays.sort(nums);
        // 双指针查找（一前一后进行收缩）
        int left = 0, right = nums.length - 1;
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum < target){
                left++;
            } else if(sum > target){
                right--;
            } else {
                // 在原始数组中查找这两个数值对应的索引然后放到res中
                int[] res = new int[2];
                for(int i = 0; i < tmp.length; i++){
                    if(tmp[i] == nums[left]){
                        res[0] = i;
                        break;
                    }
                }
                for(int i = 0; i < tmp.length; i++){
                    if(tmp[i] == nums[right] && i != res[0]){
                        res[1] = i;
                        break;
                    }
                }
                return res;
            }
        }
        return null;
    }
}